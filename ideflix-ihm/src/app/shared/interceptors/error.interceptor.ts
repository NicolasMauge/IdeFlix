import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Router} from "@angular/router";
import {MessageService} from "../services/message.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private messageSvc: MessageService,
              private route: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {


    return next.handle(request)// continuer ton chemin la requête
      // > soit vers le prochain interceptor
      // > soit vers le backend
      .pipe(
        tap( {
          error: (err: unknown) => {
            if (err instanceof HttpErrorResponse) {
              switch (err.status) {
                case 401:
                  this.messageSvc.show(`Vous n'êtes pas authentifié(e)`, 'info');
                  this.route.navigate(['/login']);
                  break;
                case 403:
                  this.messageSvc.show(`Vous n'êtes pas autorisé(e)`, 'info')
                  break;
                case 404:
                  this.messageSvc.show(`La ressource n'est pas disponible`, 'error')
                  break;
                case 409:
                  this.messageSvc.show(`Le compte existe déjà, veuillez vous connecter`, 'info')
                  this.route.navigate(['/login']);
                  break;
                case 419:
                  this.messageSvc.show(`Limit rate API`, 'error')
                  break;

                default:
                  this.messageSvc.show('Erreur serveur', 'error')
              }
            }
          },
        })
      )
  }
}
