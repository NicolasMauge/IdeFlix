import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MediaAppModel} from "../../../core/models/media-app.model";
import {MediaSelectionneModel} from "../model/MediaSelectionneModel";
import {BehaviorSubject, map, Observable} from "rxjs";
import {EtiquetteModel} from "../model/EtiquetteModel";
import {MediaService} from "../../../core/services/media/media.service";
import {environment} from "../../../../environments/environment";
import {MediaMaListeModel} from "../../../ma-liste-de-selection/models/media-ma-liste.model";

@Injectable({
  providedIn: 'root'
})
export class MediaSelectionneToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;
  private _mediaSelectionne$ = new BehaviorSubject(<MediaMaListeModel[]>([]));


  constructor(private http: HttpClient) { }

  saveToApp(media:MediaSelectionneModel) {
    let endpoint = '/mediaselectionne';

    this.http.post(this.IDEFLIX_API + endpoint, media, {responseType: 'text'}).subscribe();
  }

  getMediaSelectionnePourEmailEtIdTmdb(): MediaMaListeModel[] {
    return this._mediaSelectionne$.getValue();
  }

  // getter setter
  get mediaSelectionne$():Observable<MediaMaListeModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._mediaSelectionne$.asObservable();
  }

  trouveMediaSelectionnePourEmailEtIdTmdb(email: string, idTmdb: string) {
    let endpoint = '/mediaselectionne/utilisateur/'+email+"/idtmdb/"+idTmdb;

    this.http.get<String[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map(
          (listMediaSelectionneApi: any) => listMediaSelectionneApi.map(
            (mediaSelectionneApi: any) => new MediaMaListeModel(mediaSelectionneApi)
        ))
      )
      .subscribe((data: MediaMaListeModel[])=> this._mediaSelectionne$.next(data));
  }

  deleteFromApp() {

  }
}
