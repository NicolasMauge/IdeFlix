import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import { map, Observable} from "rxjs";
import {PreferenceModel} from "../models/preference.model";

@Injectable({
  providedIn: 'root'
})
export class MesPreferencesService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;

  constructor(private http: HttpClient) { }

  registerPreferences(data:any):Observable<any>{
    let endpoint = '/preferences';
    return this.http.post<any>(this.IDEFLIX_API + endpoint, data);
  }

  getPreferencesFromApi(email: string): Observable<PreferenceModel> {

    let endpoint = '/preferences/utilisateur/' + email;

    return this.http.get<any>(this.IDEFLIX_API + endpoint)
      .pipe(
        map((preferencesFromApi: any) =>
          new PreferenceModel(preferencesFromApi))
      )
  }

}
