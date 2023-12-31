import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {BehaviorSubject, map, Observable} from "rxjs";
import {EtiquetteModel} from "../../../selection-media/shared/model/Etiquette.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EtiquetteCoreService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;
  // private genres: GenreModel[] = [];
  private _etiquettes$ = new BehaviorSubject(<EtiquetteModel[]>([]));

  constructor(private http: HttpClient) {}


  getEtiquettes(): EtiquetteModel[] {
    return this._etiquettes$.getValue();
  }

  // getter setter
  get etiquettes$():Observable<EtiquetteModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._etiquettes$.asObservable();
  }

  loadEtiquettes(email: string): void {
    let endpoint = '/etiquette/utilisateur/'+email;

    this.http.get<String[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map(
          (listEtiquettesApi: any) => listEtiquettesApi.map(
            (etiquetteFromApi: any) => new EtiquetteModel(etiquetteFromApi))
        )
      )
      .subscribe((data: EtiquetteModel[])=> this._etiquettes$.next(data)
      );
  }

  saveToApp(etiquette: EtiquetteModel, email: string): Observable<string> {
    let endpoint = '/etiquette/utilisateur/'+email;

    return this.http.post(this.IDEFLIX_API + endpoint, etiquette, {responseType: 'text'});
  }
}
