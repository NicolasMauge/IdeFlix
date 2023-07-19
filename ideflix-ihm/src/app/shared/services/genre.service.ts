import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {GenreModel} from "../models/genre.model";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;
  private genres: GenreModel[] = [];

  constructor(private http: HttpClient) { }

  // Méthode pour récupérer le tableau des genres
  getGenres(): GenreModel[] {
    return this.genres;
  }

  // Méthode pour charger les genres depuis l'API au démarrage de l'application
  loadGenres(): Observable<GenreModel[]> {
    let endpoint = '/genre';
    return this.http.get<GenreModel[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map( (listGenresApi: any) => {
          return listGenresApi.map((genreFromApi: any) => new GenreModel(genreFromApi))
        })
      );
  }


  // Méthode pour ajouter un genre au tableau
  addGenre(genre: GenreModel): void {
    this.genres.push(genre);
  }

  // Méthode pour supprimer un genre du tableau
  removeGenre(genre: GenreModel): void {
    const index = this.genres.indexOf(genre);
    if (index !== -1) {
      this.genres.splice(index, 1);
    }
  }
}
