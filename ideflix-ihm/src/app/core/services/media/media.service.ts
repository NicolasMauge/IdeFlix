import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {BehaviorSubject, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {MediaDatabaseModel} from "../../models/media-database.model";

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  // TMDB_API = 'https://api.themoviedb.org/3';
  // TMDB_APIKEY = environment.APIKEY_TMDB;
  MOVIEDATABASE_API = environment.MOVIEDATABASE_SERVER;

  // private _movies$ = new BehaviorSubject(<MediaModel[]>([]));
  private _medias$ = new BehaviorSubject(<MediaDatabaseModel[]>([]));

  constructor(private http: HttpClient) {
  }

  getMoviesFromApi(email: string, page: number): void {


    let endpoint = '/suggestionsFilmEtSerie/' + email + '/' + page;

    this.http.get<MediaDatabaseModel[]>(this.MOVIEDATABASE_API + endpoint)
      .pipe(
        map((listMoviesApi: any) => {
          return listMoviesApi
            // map de JavaScript
            .map((movieFromApi: any) => new MediaDatabaseModel(movieFromApi))
        })
      )
      //on envoie la valeur suivante du flux de donnée (Observable est un flux)
      .subscribe((data: MediaDatabaseModel[]) => this._medias$.next(data));
  }

  getValueOfMedias$(): MediaDatabaseModel[] {
    return this._medias$.getValue();
  }

  /**getter setter  */
  get medias$(): Observable<MediaDatabaseModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._medias$.asObservable();
  }

  setMedias$(data: MediaDatabaseModel[]) {
    this._medias$.next(data);
  }

  searchMovies(userInput: string): Observable<MediaDatabaseModel[]> {
    /*
   searchMovies()
   rôle :> faire une request HTTP[GET] à l'API de app d'accès à Tmdb
         url API : https://v1/api/MovieDataBase
         endpoint : /rechercheFilm/
         queryString : query:string
         (le paramètre nommé query a pour valeur la saisie de l'utilisateur)
 */
    let endpoint = '/rechercheFilm/' + userInput;

    return this.http.get<MediaDatabaseModel[]>(this.MOVIEDATABASE_API + endpoint)
      .pipe(
        map((listMoviesApi: any) => {
          return listMoviesApi
            .map((movieFromApi: any) => new MediaDatabaseModel(movieFromApi))
        })
      )
  }

  searchSeries(userInput: string): Observable<MediaDatabaseModel[]> {
    /*
   searchSeries()
   rôle :> faire une request HTTP[GET] à l'API de app d'accès à Tmdb
         url API : https://v1/api/MovieDataBase
         endpoint : /rechercheSerie/
         queryString : query:string
         (le paramètre nommé query a pour valeur la saisie de l'utilisateur)
 */
    let endpoint = '/rechercheSerie/' + userInput;

    return this.http.get<MediaDatabaseModel[]>(this.MOVIEDATABASE_API + endpoint)
      .pipe(
        map((listMoviesApi: any) => {
          return listMoviesApi
            .map((movieFromApi: any) => new MediaDatabaseModel(movieFromApi))
        })
      )
  }

  getDetailsMovie(movieId: number): Observable<MediaDatabaseModel> {

    let endpoint = '/detailFilm/' + movieId;

    return this.http.get<MediaDatabaseModel>(this.MOVIEDATABASE_API + endpoint)
      .pipe(map((movieFromApi: any) =>
        new MediaDatabaseModel(movieFromApi))
      )
  }

  getDetailsSerie(movieId: number): Observable<MediaDatabaseModel> {

    let endpoint = '/detailSerie/' + movieId;

    return this.http.get<MediaDatabaseModel>(this.MOVIEDATABASE_API + endpoint)
      .pipe(map((movieFromApi: any) =>
        new MediaDatabaseModel(movieFromApi))
      )
  }

}
