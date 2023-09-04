import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MediaSelectionneDtoModel} from "../model/MediaSelectionneDto.model";
import {map, Observable} from "rxjs";
import {environment} from "../../../../environments/environment";
import {MediaSelectionneCompletDtoModel} from "../model/MediaSelectionneCompletDto.model";

@Injectable({
  providedIn: 'root'
})
export class MediaSelectionneToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;

  constructor(private http: HttpClient) { }

  saveToApp(media:MediaSelectionneDtoModel): Observable<any> {
    let endpoint = '/mediaselectionne';

    return this.http.post(this.IDEFLIX_API + endpoint, media, {responseType: 'text'});
  }

  trouveMediaSelectionnePourEmailEtIdTmdb(email: string, idTmdb: string): Observable<MediaSelectionneCompletDtoModel[]> {
    let endpoint = '/mediaselectionne/utilisateur/'+email+"/idtmdb/"+idTmdb;

    return this.http.get<String[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map(
          (listMediaSelectionneApi: any) =>
            listMediaSelectionneApi.map((mediaSelectionneApi: any) => {
              return new MediaSelectionneDtoModel(mediaSelectionneApi);
            }
        ))
      );
  }

  deleteFromApp(email: string, idTmdb: string):Observable<any> {
    let endpoint = '/mediaselectionne/'+email+"/"+idTmdb;

    return this.http.delete(this.IDEFLIX_API + endpoint, {observe: "body", responseType: 'arraybuffer'});
  }
}
