import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MediaAppModel} from "../../../core/models/media-app.model";
import {MediaSelectionneModel} from "../model/MediaSelectionneModel";
import {map} from "rxjs";
import {EtiquetteModel} from "../model/EtiquetteModel";
import {MediaService} from "../../../core/services/media/media.service";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MediaToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;

  constructor(private http: HttpClient) { }

  saveToApp(media:MediaSelectionneModel) {
    let endpoint = '/mediaselectionne';

    this.http.post<MediaAppModel>(this.IDEFLIX_API + endpoint, media);
  }
}
