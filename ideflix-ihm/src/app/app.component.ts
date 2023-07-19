import { Component } from '@angular/core';
import {GenreService} from "./shared/services/genre.service";
import {GenreModel} from "./shared/models/genre.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ideflix-ihm';

  genres : GenreModel[] = [];
  sub!: Subscription;

  constructor(private genreService : GenreService) {}

  ngOnInit(): void {
    // requête pour récupérer la liste des genres
    this.genreService.loadGenres();
    // abonnement à la source service.genres$ via un subscribe
    this.sub = this.genreService.genres$.subscribe( (data: GenreModel[]) => this.genres = data);
  }

  ngOnDestroy(){
    this.sub.unsubscribe
  }
}


