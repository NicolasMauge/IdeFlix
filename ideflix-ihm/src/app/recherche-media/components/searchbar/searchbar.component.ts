import { Component } from '@angular/core';
import {MediaModel} from "../../../core/models/media.model";
import {MediaService} from "../../../core/services/media/media.service";
import {SerieModel} from "../../../core/models/serie.model";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {debounceTime, Subject} from "rxjs";

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent {

  mediasResult: MediaModel[] = [];

  mediasDataBaseResult: MediaDatabaseModel[] = [];
  private debounceSubjectForMovies = new Subject<string>();

  constructor(private mediaSvc: MediaService) {
    this.debounceSubjectForMovies.pipe(debounceTime(300))  // délai de 300ms
      // Chaque fois que l'utilisateur entre une saisie, la méthode onKeyupWithDebounce est appelée,
      // qui envoie la valeur saisie au Subject pour déclencher l'appel des méthodes onKeyUpStringxx.
      .subscribe(value => {
        if (value.trim() !== '') {    // vérifier que la valeur à rechercher n'est pas vide
          this.onKeyupStringOfMovie(value);
        } else {
          this.mediasDataBaseResult = [];
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
          (this.mediasDataBaseResult = data);
          console.log('keyup', this.mediasDataBaseResult);
        })
  }

  onKeyupStringOfSerie(userInput: string): void{
    // requête GET à TMDB pour récupérer la liste des films
    console.log('userInputSerie', userInput)
    this.mediaSvc.searchSeries(userInput)
      .subscribe((data:SerieModel[]) => {
        (this.mediasResult = data);
        console.log('keyup', this.mediasResult);
      })
  }

  onKeyupStringOfMovieWithDebounce(value: string) {
    this.debounceSubjectForMovies.next(value);
  }

}
