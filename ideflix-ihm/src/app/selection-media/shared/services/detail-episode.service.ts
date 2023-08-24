import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";
import {EtiquetteModel} from "../../../core/models/etiquette.model";

@Injectable({
  providedIn: 'root'
})
export class DetailEpisodeService {

  constructor(private http: HttpClient) { }
/*
  loadDetailEpisode(numeroSaison: number): void {
    let endpoint = '/MovieDataBase'+email;

    this.http.get<String[]>(this.IDEFLIX_API + endpoint)
        .pipe(
            map(
                (listEtiquettesApi: any) => listEtiquettesApi.map(
                    (etiquetteFromApi: any) => new EtiquetteModel(etiquetteFromApi))
            )
        )
        .subscribe((data: EtiquetteModel[])=> this._etiquettes$.next(data)
        );
  }*/
}
