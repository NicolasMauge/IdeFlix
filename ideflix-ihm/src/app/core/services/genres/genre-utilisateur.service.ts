import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {BehaviorSubject, map, Observable} from "rxjs";
import {GenreModel} from "../../models/genre.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GenreUtilisateurService {

  IDEFLIX_API = environment.IDEFLIX_SERVER;
  private _genresUtilisateur$ = new BehaviorSubject(<GenreModel[]>([]));

  constructor(private http: HttpClient) {}

  // Méthode pour récupérer le tableau des genres
  getGenresUtilisateur(): GenreModel[] {
    return this._genresUtilisateur$.getValue();
  }

  /**getter setter  */
  get genresUtilisateur$():Observable<GenreModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._genresUtilisateur$.asObservable();
  }

  // Méthode pour charger les genres détenus sur la liste de sélection d'un utilisateur depuis l'API
  loadGenresByUtilisateur(email : string): void {
    let endpoint = '/genre/utilisateur/' + email;
    this.http.get<GenreModel[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        map( (listGenresApi: any) => {
          return listGenresApi.map((genreFromApi: any) => new GenreModel(genreFromApi))
        })
      )
      .subscribe((data: GenreModel[])=> this._genresUtilisateur$.next(data)
      );
  }

}
