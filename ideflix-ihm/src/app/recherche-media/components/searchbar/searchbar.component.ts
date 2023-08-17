import { Component } from '@angular/core';
import {MediaModel} from "../../../core/models/media.model";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {debounceTime, Subject} from "rxjs";

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent {

  // mediasResult: MediaModel[] = [];

  moviesDataBaseResult: MediaDatabaseModel[] = [];
  seriesDataBaseResult: MediaDatabaseModel[] = [];
  private debounceSubjectForMovies = new Subject<string>();
  private debounceSubjectForSeries = new Subject<string>();

  constructor(private mediaSvc: MediaService) {
    this.debounceSubjectForMovies.pipe(debounceTime(300))  // délai de 300ms
      // Chaque fois que l'utilisateur entre une saisie, la méthode onKeyupWithDebounce est appelée,
      // qui envoie la valeur saisie au Subject pour déclencher l'appel des méthodes onKeyUpStringxx.
      .subscribe(value => {
        if (value.trim() !== '') {    // vérifier que la valeur à rechercher n'est pas vide
          this.onKeyupStringOfMovie(value);
        } else {
          this.moviesDataBaseResult = [];
        }
    })

    this.debounceSubjectForSeries.pipe(debounceTime(300))  // délai de 300ms
      // Chaque fois que l'utilisateur entre une saisie, la méthode onKeyupWithDebounce est appelée,
      // qui envoie la valeur saisie au Subject pour déclencher l'appel des méthodes onKeyUpStringxx.
      .subscribe(value => {
        if (value.trim() !== '') {    // vérifier que la valeur à rechercher n'est pas vide
          this.onKeyupStringOfSerie(value);
        } else {
          this.seriesDataBaseResult = [];
        }
      })
  }

  // onKeyupStringOfMovie(userInput: string): void{
  //   // requête GET à TMDB pour récupérer la liste des films
  //   console.log('userInputMovie', userInput)
  //   this.mediaSvc.searchMovies(userInput)
  //     .subscribe((data:MediaModel[]) => {
  //       (this.mediasResult = data);
  //       console.log('keyup', this.mediasResult);
  //     })
  // }

  onKeyupStringOfMovie(userInput: string): void{
      // requête GET à TMDB pour récupérer la liste des films
      console.log('userInputMovie', userInput)
      this.mediaSvc.searchMovies2(userInput)
        .subscribe((data:MediaDatabaseModel[]) => {
          (this.moviesDataBaseResult = data);
          console.log('keyup', this.moviesDataBaseResult);
        })
  }

  onKeyupStringOfSerie(userInput: string): void{
    // requête GET à TMDB pour récupérer la liste des films
    console.log('userInputSerie', userInput)
    this.mediaSvc.searchSeries(userInput)
      .subscribe((data:MediaDatabaseModel[]) => {
        (this.seriesDataBaseResult = data);
        console.log('keyup', this.seriesDataBaseResult);
      })
  }

  onKeyupStringOfMovieWithDebounce(value: string) {
    this.debounceSubjectForMovies.next(value);
  }

  onKeyupStringOfSerieWithDebounce(value: string) {
    this.debounceSubjectForSeries.next(value);
  }

}
