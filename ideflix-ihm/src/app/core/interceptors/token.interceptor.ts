import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  USER_API = environment.USER_SERVER;
  IDEFLIX_API = environment.IDEFLIX_SERVER;
  MOVIEDATABASE_API = environment.MOVIEDATABASE_SERVER;

  constructor() {}

  // la methode intercept est appelé pour chaque requête et reçoit la requête comme argument en plus de l'objet next
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let cloneReq = request;
    const token: string | null = localStorage.getItem('token');


// token doit être ajouté lors des appels vers API IAM  (sauf login et création de compte)
    if (token && cloneReq.url.includes(this.USER_API)
      && !cloneReq.url.includes('/iam/login')
      && !cloneReq.url.includes('/iam/utilisateur')) {
      cloneReq = cloneReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    //token doit être ajouté lors des appels vers API Ideflix
    if (token && cloneReq.url.includes(this.IDEFLIX_API)) {
      cloneReq = cloneReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    //token doit être ajouté lors des appels vers API pour acces base des  films et series
    if (token && cloneReq.url.includes(this.MOVIEDATABASE_API)) {
      cloneReq = cloneReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(cloneReq);
  }}

