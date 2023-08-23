import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {MediaModel} from "../../../core/models/media.model";
import {GenreAppModel} from "../model/GenreApp.model";
import {MediaAppOutModel} from "../model/MediaAppOut.model";
import {Observable} from "rxjs";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";

@Injectable({
  providedIn: 'root'
})
export class MediaToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;

  constructor(private http: HttpClient) { }

  saveToApp(media: MediaDatabaseModel, typeMedia: string): Observable<any>{
    let mediaApp = new MediaAppOutModel({
      idTmdb: media.idDataBase,
      typeMedia: typeMedia,
      titre: media.titre,
      dateSortie: media.dateSortie,
      duree: media.duree,
      resume: media.resume,
      cheminAffichePortrait: media.image_portrait,
      cheminAffichePaysage: media.image_paysage,
      noteTmdb: media.scoreDataBase,
      genreList: media.genres.map((genre:any) => new GenreAppModel(genre))
    });

    let endpoint= typeMedia=="FILM" ? '/film':'/serie';

    return this.http.post(this.IDEFLIX_API + endpoint, mediaApp, {responseType: 'text'});
  }
}
