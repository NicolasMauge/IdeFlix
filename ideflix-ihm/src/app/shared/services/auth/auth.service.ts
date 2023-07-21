import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {MessageService} from "../common/message.service";
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

  constructor(private http: HttpClient,
              private messageSvc: MessageService) {}

  login(credentials : Credentials): Observable<any>{
    let endpoint = '/login';
    return this.http.post<any>(this.USER_API + endpoint, credentials)
      .pipe(
        tap( {
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
          next : () => {
            this._isAuthenticated = true;
          }
        })
      )
  }

  registerUser(data:any):Observable<any>{
    let endpoint = '/utilisateur';
    return this.http.post<any>(this.USER_API + endpoint, data)
      .pipe(
        tap({
          next : () => {
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
}
