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
    this._isAuthenticated = false;
  }


  isAuthenticatedUser(): boolean {
    return this._isAuthenticated;
  }

  isAdmin(): boolean {
    //récupération des rôles :
    let jwt = localStorage.getItem('token');
    if (jwt) {
      let jwtData = jwt.split('.')[1];
      console.log("jwtData : " + jwtData);
      let decodedJwtJsonData = window.atob(jwtData);
      console.log("decodedJwtJsonData : " + decodedJwtJsonData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      console.log("decodedJwtData : " + decodedJwtData);
//              let objetJwt = JSON.parse(decodedJwtJsonData);
//              console.log("objetJwt : " + objetJwt)
      if (decodedJwtData.roles.includes('ROLE_ADMIN')) {
        this._isAdmin = true;
      } else {
        this._isAdmin = false;
      }
      console.log("isAdmin " + this._isAdmin);
    } else {
      console.log("jwt non trouvé dans LocalStorage");
      this._isAdmin = false;
    }


    return this._isAdmin;
  }
}
