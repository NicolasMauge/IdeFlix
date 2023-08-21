import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {MediaSelectionneModel} from "../model/MediaSelectionneModel";
import {GenreAppModel} from "../model/GenreAppModel";
import {firstValueFrom, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GenreToAppService {
  IDEFLIX_API = environment.IDEFLIX_SERVER;

  constructor(private http: HttpClient) { }

  saveToApp(genres:GenreAppModel[]): Observable<any> {
    let endpoint = '/genre/masse';

    return this.http
      .post(this.IDEFLIX_API + endpoint, genres, {responseType: 'text'});
  }
}
