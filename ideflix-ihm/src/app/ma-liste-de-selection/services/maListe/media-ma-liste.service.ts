import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {BehaviorSubject, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {MediaMaListeModel} from "../../models/media-ma-liste.model";

@Injectable({
  providedIn: 'root'
})
export class MediaMaListeService {

  TMDB_API = 'https://api.themoviedb.org/3';
  IDEFLIX_API = environment.IDEFLIX_SERVER;
  TMDB_APIKEY = environment.APIKEY_TMDB;

  private _mediaMaListe$ = new BehaviorSubject(<MediaMaListeModel[]>([]));
  private originalMediaList : MediaMaListeModel[] = [];

  constructor(private http: HttpClient) { }

  // getMoviesFromApi(): void {
  //
  //   let endpoint = '/discover/movie';
  //   let options = new HttpParams()
  //     .set('api_key', this.TMDB_APIKEY )
  //     .set('language', 'fr')
  //     .set('page', '1');
  //
  //   this.http.get<MediaMaListeModel[]>(this.TMDB_API + endpoint, { params:options })
  //     .pipe(
  //       //extraction avec map des propriétés qui nous intéressent  - dans results de la réponse API =  20 movies
  //       //map est l'opérateur de RxJs
  //       map( (listMoviesApi: any) => {
  //         return listMoviesApi.results
  //           // map de JavaScript
  //           .map((movieFromApi: any) => new MediaMaListeModel(movieFromApi))
  //       })
  //     )
  //     //on envoie la valeur suivante du flux de donnée (Observable est un flux)
  //     .subscribe((data: MediaMaListeModel[]) => this._mediaMaListe$.next(data));
  // }

  getMoviesFromApi(email: string): void {

    let endpoint = '/mediaselectionne/utilisateur/' + email;

    this.http.get<MediaMaListeModel[]>(this.IDEFLIX_API + endpoint)
      .pipe(
        //extraction avec map des propriétés qui nous intéressent  - dans results de la réponse API =  20 movies
        //map est l'opérateur de RxJs
        map( (listMoviesApi: any) => {
          return listMoviesApi
            // map de JavaScript
            .map((movieFromApi: any) => new MediaMaListeModel(movieFromApi))
        })
      )
      //on envoie la valeur suivante du flux de donnée (Observable est un flux)
      .subscribe((data: MediaMaListeModel[]) => {
        this._mediaMaListe$.next(data);
        this.originalMediaList = data});
  }


  getValueOfMediaMaListe$(): MediaMaListeModel[] {
    return this._mediaMaListe$.getValue();
  }

  /**getter setter  */
  get mediaMaListe$():Observable<MediaMaListeModel[]> {
    //asObservable pour que personne ne puisse faire un next là-dessus. Avec un observable, on ne peut que souscrire.
    return this._mediaMaListe$.asObservable();
  }

  setMediaMaListe$(data: MediaMaListeModel[]) {
    this._mediaMaListe$.next(data);
  }

  updateFilteredMediaList(filteredList : MediaMaListeModel[]) {
    this._mediaMaListe$.next(filteredList);
  }

  reinitializedMediaList() {
    this._mediaMaListe$.next(this.originalMediaList);
  }

  //  searchMediaMaListe(userInput: string): Observable<MediaMaListeModel[]> {
 //    /*
 //   searchMovies()
 //   rôle :> faire une request HTTP[GET] à l'API theMovieDB
 //         url API : https://api.themoviedb.org/3
 //         endpoint : /search/movie
 //         queryString : query:string api_key:string, language:string
 //         (le paramètre nommé query a pour valeur la saisie de l'utilisateur)
 // */
 //    /* api.themoviedb.org/3/search/movie?query=fast&api_key=5f871496b04d6b713429ccba8a599149&language=fr */
 //    let endpoint = '/search/movie';
 //    let options = new HttpParams()
 //      .set('query', userInput)
 //      .set('api_key', this.TMDB_APIKEY )
 //      .set('language', 'fr');
 //
 //    return this.http.get<MediaMaListeModel[]>(this.TMDB_API + endpoint, { params:options })
 //      .pipe(
 //        map( (listMoviesApi: any) => {
 //          return listMoviesApi.results
 //            .map((movieFromApi: any) => new MediaMaListeModel(movieFromApi))
 //        })
 //      )
 //  }
 //
 //  /**
 //   *
 //   * @param movieId
 //   * @returns
 //   */
 //  getDetails(movieId: number): Observable<MediaMaListeModel> {
 //
 //    /* https://api.themoviedb.org/3/movie/385687?api_key=5f871496b04d6b713429ccba8a599149&language=en-FR */
 //    let endpoint = '/movie/' + movieId;
 //    let options = new HttpParams()
 //      .set('api_key', this.TMDB_APIKEY )
 //      .set('language', 'fr');
 //
 //    return this.http.get<MediaMaListeModel>(this.TMDB_API + endpoint, { params:options })
 //      .pipe(map ((movieFromApi: any) =>
 //        new MediaMaListeModel(movieFromApi))
 //      )
 //  }
 //
 //  /**
 //   *
 //   * @param movieId
 //   * @returns
 //   */
 //  getVideos(movieId : number): Observable<any> {
 //    /* https://api.themoviedb.org/3/movie/385687/videos?api_key=5f871496b04d6b713429ccba8a599149&language=en-FR*/
 //    let endpoint = '/movie/' + movieId + '/videos';
 //    let options = new HttpParams()
 //      .set('api_key', this.TMDB_APIKEY )
 //      .set('language', 'fr');
 //
 //    return this.http.get<any>(this.TMDB_API + endpoint, { params:options })
 //      .pipe(map ((idVideoUrl: any) => idVideoUrl.results[0].key))
 //  }

}
