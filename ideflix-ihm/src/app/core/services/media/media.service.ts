import { Injectable } from '@angular/core';
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
  constructor(private http: HttpClient) { }

  // getMoviesFromApi(): void {
  //
  //   let endpoint = '/discover/movie';
  //   let options = new HttpParams()
  //     .set('api_key', this.TMDB_APIKEY )
  //     .set('language', 'fr')
  //     .set('page', '1');
  //
  //   this.http.get<MediaModel[]>(this.TMDB_API + endpoint, { params:options })
  //     .pipe(
  //       //extraction avec map des propriétés qui nous intéressent  - dans results de la réponse API =  20 movies
  //       //map est l'opérateur de RxJs
  //       map( (listMoviesApi: any) => {
  //         return listMoviesApi.results
  //           // map de JavaScript
  //           .map((movieFromApi: any) => new MediaModel(movieFromApi))
  //       })
  //     )
  //     //on envoie la valeur suivante du flux de donnée (Observable est un flux)
  //     .subscribe((data: MediaModel[]) => this._movies$.next(data));
  // }

  // getValueOfMovies$(): MediaModel[] {
  //   return this._movies$.getValue();
  // }
  //
  // /**getter setter  */
  // get movies$():Observable<MediaModel[]> {
  //   //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
  //   return this._movies$.asObservable();
  // }
  //
  // setMovies$(data: MediaModel[]) {
  //   this._movies$.next(data);
  // }

  getMoviesFromApi2(page: number): void {


    let endpoint = '/suggestionsFilm/' + page;

    this.http.get<MediaDatabaseModel[]>(this.MOVIEDATABASE_API + endpoint)
      .pipe(
        map( (listMoviesApi: any) => {
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
  get medias$():Observable<MediaDatabaseModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._medias$.asObservable();
  }

  setMedias$(data: MediaDatabaseModel[]) {
    this._medias$.next(data);
  }

 //  searchMovies(userInput: string): Observable<MediaModel[]> {
 //    /*
 //   searchMovies()
 //   rôle :> faire une request HTTP[GET] à l'API theMovieDB
 //         url API : https://api.themoviedb.org/3
 //         endpoint : /search/movie
 //         queryString : query:string api_key:string, language:string
 //         (le paramètre nommé query a pour valeur la saisie de l'utilisateur)
 // */
 //    /* api.themoviedb.org/3/search/movie?query=fast&api_key=5f871496b04d6b713429ccba8a599149&language=fr */
 //    // let endpoint = '/search/movie';
 //    // let endpoint = '/search/multi';
 //    let endpoint = '/search/movie';
 //    let options = new HttpParams()
 //      .set('query', userInput)
 //      .set('api_key', this.TMDB_APIKEY )
 //      .set('language', 'fr');
 //
 //    return this.http.get<MediaModel[]>(this.TMDB_API + endpoint, { params:options })
 //      .pipe(
 //        map( (listMoviesApi: any) => {
 //          return listMoviesApi.results
 //            .map((movieFromApi: any) => new MediaModel(movieFromApi))
 //        })
 //      )
 //  }

  searchMovies2(userInput: string): Observable<MediaDatabaseModel[]> {
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
        map( (listMoviesApi: any) => {
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
        map( (listMoviesApi: any) => {
          return listMoviesApi
            .map((movieFromApi: any) => new MediaDatabaseModel(movieFromApi))
        })
      )
  }

 //  searchSeries(userInput: string): Observable<SerieModel[]> {
 //    /*
 //   searchMovies()
 //   rôle :> faire une request HTTP[GET] à l'API theMovieDB
 //         url API : https://api.themoviedb.org/3
 //         endpoint : /search/tv
 //         queryString : query:string api_key:string, language:string
 //         (le paramètre nommé query a pour valeur la saisie de l'utilisateur)
 // */
 //    /* api.themoviedb.org/3/search/tv?query=mi&api_key=5f871496b04d6b713429ccba8a599149&language=fr */
 //    // let endpoint = '/search/movie';
 //    // let endpoint = '/search/multi';
 //    let endpoint = '/search/tv';
 //    let options = new HttpParams()
 //      .set('query', userInput)
 //      .set('api_key', this.TMDB_APIKEY )
 //      .set('language', 'fr');
 //
 //    return this.http.get<SerieModel[]>(this.TMDB_API + endpoint, { params:options })
 //      .pipe(
 //        map( (listSeriesApi: any) => {
 //          return listSeriesApi.results
 //            .map((serieFromApi: any) => new SerieModel(serieFromApi))
 //        })
 //      )
 //  }

  /**
   *
   * @param movieId
   * @returns
   */
  // getDetails(movieId: number): Observable<MediaModel> {
  //
  //   /* https://api.themoviedb.org/3/movie/385687?api_key=5f871496b04d6b713429ccba8a599149&language=en-FR */
  //   let endpoint = '/movie/' + movieId;
  //   let options = new HttpParams()
  //     .set('api_key', this.TMDB_APIKEY )
  //     .set('language', 'fr');
  //
  //   return this.http.get<MediaModel>(this.TMDB_API + endpoint, { params:options })
  //     .pipe(map ((movieFromApi: any) =>
  //       new MediaModel(movieFromApi))
  //     )
  // }

  getDetailsMovie(movieId: number): Observable<MediaDatabaseModel> {

    let endpoint = '/detailFilm/' + movieId;

    return this.http.get<MediaDatabaseModel>(this.MOVIEDATABASE_API + endpoint)
      .pipe(map ((movieFromApi: any) =>
        new MediaDatabaseModel(movieFromApi))
      )
  }


  getDetailsSerie(movieId: number): Observable<MediaDatabaseModel> {

    let endpoint = '/detailSerie/' + movieId;

    return this.http.get<MediaDatabaseModel>(this.MOVIEDATABASE_API + endpoint)
      .pipe(map ((movieFromApi: any) =>
        new MediaDatabaseModel(movieFromApi))
      )
  }


  /**
   *
   * @param movieId
   * @returns
   */
  // getVideos(movieId : number): Observable<any> {
  //   /* https://api.themoviedb.org/3/movie/385687/videos?api_key=5f871496b04d6b713429ccba8a599149&language=en-FR*/
  //   let endpoint = '/movie/' + movieId + '/videos';
  //   let options = new HttpParams()
  //     .set('api_key', this.TMDB_APIKEY )
  //     .set('language', 'fr');
  //
  //   return this.http.get<any>(this.TMDB_API + endpoint, { params:options })
  //     .pipe(map ((idVideoUrl: any) => idVideoUrl.results[0].key))
  // }

}
