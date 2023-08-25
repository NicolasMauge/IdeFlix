import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {PreferenceModel} from "../../../mes-preferences/models/preference.model";
import {MessageService} from "../common/message.service";

@Injectable({
  providedIn: 'root'
})
export class MesPreferencesService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;
  private _preferences$ = new BehaviorSubject<PreferenceModel>({email:'toto',pseudo:'',genreList:[]} );


  constructor(private http: HttpClient,
              private messageSvc : MessageService) { }

  registerPreferences(data:any):Observable<any>{
    let endpoint = '/preferences';
    return this.http.post<any>(this.IDEFLIX_API + endpoint, data);
  }

  getPreferencesFromApi(email: string): void {

    let endpoint = '/preferences/utilisateur/' + email;

    this.http.get<PreferenceModel>(this.IDEFLIX_API + endpoint)
      .pipe(
        map((preferencesFromApi: any) =>
          new PreferenceModel(preferencesFromApi))
      )
      .subscribe((data: PreferenceModel)=> this._preferences$.next(data),
         (err: unknown)=> {
        if (err instanceof HttpErrorResponse) {
          if (err.status == 404) {
            this.messageSvc.show('Vous n\'avez pas de préférences', 'info')
        }}
    });
  }

  /**getter setter  */
  get preferences$():Observable<PreferenceModel> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._preferences$.asObservable();
  }

  // Méthode pour récupérer le tableau des genres
  getPreferences(): PreferenceModel {
    return this._preferences$.getValue();
  }

}
