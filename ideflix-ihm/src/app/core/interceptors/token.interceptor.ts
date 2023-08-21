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

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let cloneReq = request;
    const token: string | null = localStorage.getItem('token');

    if (token) {
      cloneReq = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

// token doit être ajouté lors des appels vers API IAM  (sauf login et création de compte)
    if (cloneReq.url.includes(this.USER_API)
      && !cloneReq.url.includes('/login')
      && !cloneReq.url.includes('/utilisateur')) {
      cloneReq = cloneReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    //token doit être ajouté lors des appels vers API Ideflix
    if (cloneReq.url.includes(this.IDEFLIX_API)) {
      cloneReq = cloneReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    //token doit être ajouté lors des appels vers API pour acces base des  films et series
    if (cloneReq.url.includes(this.MOVIEDATABASE_API)) {
      cloneReq = cloneReq.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(request);
  }}

//-----------------avant IDF-80--------------------------------------------------------------
//     return next.handle(request);
//
//     let token: string | null = localStorage.getItem('token');
//
//     let cloneReq: HttpRequest<unknown>;
//     // cloneReq = request.clone({
//     //   headers: request.headers.set(
//     //     'Authorization',
//     //     token ?? ''
//     //   )
//     // });
//     if (token) {
//       cloneReq = request.clone({
//         setHeaders: {
//           Authorization: `Bearer ${token}`
//         }
//       });
//     }
//
// // token doit être ajouté lors des appels vers API IAM  (sauf login et création de compte)
//     if (request.url.includes(this.USER_API)
//       && !request.url.includes('/login')
//       && !request.url.includes('/utilisateur')) {
//       request = cloneReq
//     }
//
//     //token doit être ajouté lors des appels vers API Ideflix
//     if (request.url.includes(this.IDEFLIX_API)) {
//       request = cloneReq
//     }
//
//     return next.handle(request);
//   }
// }
