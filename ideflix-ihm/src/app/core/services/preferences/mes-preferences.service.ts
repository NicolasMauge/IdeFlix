import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, map, Observable, tap} from "rxjs";
import {PreferenceModel} from "../../models/preference.model";
import {MessageService} from "../common/message.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class MesPreferencesService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;
  private _preferences$ = new BehaviorSubject<PreferenceModel>({email: 'toto', pseudo: ' ', genreList: []});
  preferencesVides = {email: 'toto', pseudo: ' ', genreList: []};

  constructor(private http: HttpClient,
              private messageSvc: MessageService,
              private route: Router) {
  }

  registerPreferences(data: any): Observable<any> {
    let endpoint = '/preferences';
//    return this.http.post<any>(this.IDEFLIX_API + endpoint, data);
    const email = localStorage.getItem('email');
    if (email !== null) {
      this.getPreferencesFromApi(email);

      return this.http.post<any>(this.IDEFLIX_API + endpoint, data).pipe(
        // Recharger les données après la sauvegarde
        tap(() => this.getPreferencesFromApi(email)));
    } else {
      return this.http.post<any>(this.IDEFLIX_API + endpoint, data);
    }


  }

  getPreferencesFromApi(email: string): void {

    let endpoint = '/preferences/utilisateur/' + email;
    console.log("Récupération des préférences de " + email);
    this.http.get<PreferenceModel>(this.IDEFLIX_API + endpoint)
      .pipe(
        map((preferencesFromApi: any) =>
          new PreferenceModel(preferencesFromApi))
      )
      .subscribe({
        next: (data: PreferenceModel) => {
          this._preferences$.next(data);
          console.log(this._preferences$);
        },
        error: (err: unknown) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status == 404) {
              // On vide les préférences précédentes (au cas où IdeFlix est utilisé par une autre personne) :
              this._preferences$.next(this.preferencesVides);
              this.messageSvc.show('Vous n\'avez pas de préférences', 'info');
              this.route.navigate(['/mesPreferences']);
            }
          }
        }
      });
  }

  /**getter setter  */
  get preferences$(): Observable<PreferenceModel> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._preferences$.asObservable();
  }

  // Méthode pour récupérer le tableau des genres
  getPreferences(): PreferenceModel {
    return this._preferences$.getValue();
  }

}
