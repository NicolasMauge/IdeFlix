import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {MessageService} from "../../core/services/common/message.service";
import {Observable, tap} from "rxjs";


interface Credentials {
  email: string;
  motDePasse: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  USER_API = environment.USER_SERVER;
  token!: string;
  private _isAuthenticated: boolean = false;
  private _isAdmin: boolean = false;

  constructor(private http: HttpClient,
              private messageSvc: MessageService) {
  }

  login(credentials: Credentials): Observable<any> {
    let endpoint = '/login';
    return this.http.post<any>(this.USER_API + endpoint, credentials)
      .pipe(
        tap({
          error: (err: unknown) => {
            if (err instanceof HttpErrorResponse) {
              if (err.status == 403) {
                //alert('Mauvais Email ou MotdePasse');
                this.messageSvc.show('Mauvais Email ou Mot de passe', 'error')
                // par sécurité, je supprime le token éventuel existant si erreur connexion
                localStorage.removeItem('token');
              }
            }
          },
          next: () => {
            this._isAuthenticated = true;
          }
        })
      )
  }

  registerUser(data: any): Observable<any> {
    let endpoint = '/utilisateur';
    return this.http.post<any>(this.USER_API + endpoint, data)
      .pipe(
        tap({
          next: () => {
            this._isAuthenticated = true;
          }
        })
      )
  }


  logout() {
    let endpoint: string = '/logout';
    console.log("Déconnexion en cours")
    return this.http.post<any>(this.USER_API + endpoint, "{}")
      .pipe(
        tap({
          error: (err: unknown) => {
            console.log("Erreur lors de la déconnexion :");
            if (err instanceof HttpErrorResponse) {
              this.messageSvc.show('Erreur lors de la déconnexion', 'error')
              console.log("ERREUR " + err.status + " - " + err.name + " - " + err.message);
              // par sécurité, déconnexion côté IHM même en cas d'erreur
              this.terminerLaConnexion();
            }
          },
          next: () => {
            console.log("Déconnexion de l'APP effectuée.")
            this.terminerLaConnexion();
          }
        })
      )
  }

  terminerLaConnexion() {
    let email: string | null = localStorage.getItem("email");
    if (email)
      console.log("Déconnexion de " + email);
    else
      console.log("Déconnexion");

    localStorage.clear();
    sessionStorage.clear();
    this._isAuthenticated = false;
    this._isAdmin = false;

  }

  isAuthenticatedUser(): boolean {
    return this._isAuthenticated;
  }

  isAdmin(): boolean {
    //récupération des rôles :
    let jwt = localStorage.getItem('token');
    if (jwt) {
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      if (decodedJwtData.roles.includes('ROLE_ADMIN')) {
        this._isAdmin = true;
      } else {
        this._isAdmin = false;
      }
    } else {
      this._isAdmin = false;
    }

    return this._isAdmin;
  }
}
