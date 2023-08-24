import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {BehaviorSubject, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {MediaMaListeModel} from "../models/media-ma-liste.model";

@Injectable({
  providedIn: 'root'
})
export class MediaMaListeService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;

  private _mediaMaListe$ = new BehaviorSubject(<MediaMaListeModel[]>([]));
  private originalMediaList : MediaMaListeModel[] = [];

  constructor(private http: HttpClient) { }

  getMoviesFromApi(email: string): void {

    let endpoint = '/mediaselectionne/utilisateur/' + email;

    this.http.get<MediaMaListeModel[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        //extraction avec map des propriétés qui nous intéressent  - dans results de la réponse API
        //map est l'opérateur de RxJs
        map( (listMoviesApi: any) => {
          return listMoviesApi
            // map de JavaScript
            .map((movieFromApi: any) => new MediaMaListeModel(movieFromApi))
        })
      )
      //on envoie la valeur suivante du flux de donnée (Observable est un flux)
      .subscribe((data: MediaMaListeModel[]) => {
        console.log("data get: ", data)
        this._mediaMaListe$.next(data);
        console.log("getMoviesFromApi: ", this.getValueOfMediaMaListe$() )
        this.originalMediaList = data});
  }

  getValueOfMediaMaListe$(): MediaMaListeModel[] {
    return this._mediaMaListe$.getValue();
  }

  /**getter setter  */
  get mediaMaListe$():Observable<MediaMaListeModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._mediaMaListe$.asObservable();
  }

  setMediaMaListe$(data: MediaMaListeModel[]) {
    this._mediaMaListe$.next(data);
  }

  updateFilteredMediaList(filteredList : MediaMaListeModel[]) {
    this._mediaMaListe$.next(filteredList);
  }

  reinitializedMediaList() {
    this._mediaMaListe$.next(this.originalMediaList);
  }

}
