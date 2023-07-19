import { Component } from '@angular/core';
import {MediaModel} from "../../shared/models/media.model";
import {MediaService} from "../../shared/services/media.service";

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent {

  mediasResult: MediaModel[] = [];

  constructor(private mediaSvc: MediaService) {
  }

  onKeyupStringOfMovie(userInput: string): void{
    // requête GET à TMDB pour récupérer la liste des films
    console.log('userInput', userInput)
    this.mediaSvc.searchMovies(userInput)
      .subscribe((data:MediaModel[]) => {
        (this.mediasResult = data);
        console.log('keyup', this.mediasResult);
      })
  }
}
