import { Component } from '@angular/core';
import {GenreService} from "./shared/services/genre.service";
import {GenreModel} from "./shared/models/genre.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ideflix-ihm';
  // genre$: Observable<GenreModel[]>;

  constructor(private genreService : GenreService) {}

  ngOnInit(): void {
    this.genreService.loadGenres()
      .subscribe((genres: GenreModel[]) => {
          genres.forEach((genre) => {
            this.genreService.addGenre(genre);
          });
        },
        (error) => {
          console.error('Erreur lors du chargement des genres : ', error);
        }
      );
  }

}


