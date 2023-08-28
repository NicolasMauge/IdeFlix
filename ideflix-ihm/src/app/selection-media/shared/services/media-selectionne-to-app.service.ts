import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MediaSelectionneDtoModel} from "../model/MediaSelectionneDto.model";
import {BehaviorSubject, map, Observable} from "rxjs";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MediaSelectionneToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;
  private _mediaSelectionne$ = new BehaviorSubject(<MediaSelectionneDtoModel[]>([]));

  constructor(private http: HttpClient) { }

  saveToApp(media:MediaSelectionneDtoModel): Observable<any> {
    let endpoint = '/mediaselectionne';

    return this.http.post(this.IDEFLIX_API + endpoint, media, {responseType: 'text'});
  }

  getMediaSelectionnePourEmailEtIdTmdb(): MediaSelectionneDtoModel[] {
    return this._mediaSelectionne$.getValue();
  }

  // getter setter
  get mediaSelectionne$():Observable<MediaSelectionneDtoModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._mediaSelectionne$.asObservable();
  }

  trouveMediaSelectionnePourEmailEtIdTmdb(email: string, idTmdb: string): Observable<MediaSelectionneDtoModel[]> {
    let endpoint = '/mediaselectionne/utilisateur/'+email+"/idtmdb/"+idTmdb;

    return this.http.get<String[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map(
          (listMediaSelectionneApi: any) =>
            listMediaSelectionneApi.map((mediaSelectionneApi: any) => {
              console.log("coucou avant l'init 2");
              console.log(new MediaSelectionneDtoModel(mediaSelectionneApi));

              return new MediaSelectionneDtoModel(mediaSelectionneApi);
            }
        ))
      );
      //.subscribe((data: MediaSelectionneDtoModel[])=> this._mediaSelectionne$.next(data));
  }

  deleteFromApp(email: string, idTmdb: string):Observable<any> {
    let endpoint = '/mediaselectionne/'+email+"/"+idTmdb;

    return this.http.delete(this.IDEFLIX_API + endpoint, {observe: "body", responseType: 'arraybuffer'});
  }
}
