import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {MediaModel} from "../../../core/models/media.model";
import {GenreAppModel} from "../model/GenreAppModel";
import {MediaAppOutModel} from "../model/MediaAppOutModel";

@Injectable({
  providedIn: 'root'
})
export class MediaToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;

  constructor(private http: HttpClient) { }

  saveToApp(media: MediaModel, typeMedia: boolean) {
    let mediaApp = new MediaAppOutModel({
      idTmdb: media.idTmdb,
      typeMedia: typeMedia ? "FILM" : "SERIE",
      titre: media.titre,
      dateSortie: media.date,
      duree: media.duration,
      resume: media.resume,
      cheminAffichePortrait: media.image_portrait,
      cheminAffichePaysage: media.image_landscape,
      noteTmdb: media.score,
      genreList: media.genres.map((genre:any) => new GenreAppModel(genre))
    });

    let endpoint= typeMedia ? '/film':'/serie';

    this.http.post(this.IDEFLIX_API + endpoint, mediaApp, {responseType: 'text'}).subscribe();
  }
}
