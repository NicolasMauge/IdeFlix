import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

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

}
