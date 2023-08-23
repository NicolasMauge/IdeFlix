import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {environment} from "../../../../environments/environment";
import {GenreModel} from "../../models/genre.model";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;
  // private genres: GenreModel[] = [];
  private _genres$ = new BehaviorSubject(<GenreModel[]>([]));

  constructor(private http: HttpClient) {}

  // Méthode pour récupérer le tableau des genres
  getGenres(): GenreModel[] {
    return this._genres$.getValue();
  }

  /**getter setter  */
  get genres$():Observable<GenreModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._genres$.asObservable();
  }

  // Méthode pour charger les genres depuis l'API au démarrage de l'application
  loadGenres(): void {
    let endpoint = '/genre';
    this.http.get<GenreModel[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map( (listGenresApi: any) => {
          return listGenresApi.map((genreFromApi: any) => new GenreModel(genreFromApi))
        })
      )
      .subscribe((data: GenreModel[])=> this._genres$.next(data)
      );
  }
}
