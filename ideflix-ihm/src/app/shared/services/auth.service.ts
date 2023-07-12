import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {MessageService} from "./message.service";
import {Observable, tap} from "rxjs";


interface Credentials {
  email: string;
  password: string;
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  USER_API = environment.USER_SERVER;

  token!: string;

  constructor(private http: HttpClient,
              private messageSvc: MessageService) {}

  login(credentials : Credentials): Observable<any>{
    let endpoint = '/users/login';
    return this.http.post<any>(this.USER_API + endpoint, credentials)
      .pipe(
        tap( {
          error: (err: unknown) => {
            if (err instanceof HttpErrorResponse) {
              if (err.status == 400) {
                //alert('Mauvais Email ou MotdePasse');
                this.messageSvc.show('Mauvais Email ou Mot de passe', 'error')
                // par sécurité, je supprime le token éventuel existant si erreur connexion
                localStorage.removeItem('token');
              }
            }
          },
        })
      )
  }

  registerUser(credentials : Credentials):Observable<any>{
    let endpoint = '/users/register';
    return this.http.post<any>(this.USER_API + endpoint, credentials)
  }

}
