import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {GenreModel} from "../../../core/models/genre.model";

interface Genre {
  idDatabase: number;
  nomGenre: string;
}

@Component({
  selector: 'app-detail-media',
  templateUrl: './detail-media.component.html',
  styleUrls: ['./detail-media.component.css']
})
export class DetailMediaComponent {
  movieId!: number;
  media!:MediaDatabaseModel;
  typeMedia!:string;
  image! : string;

  constructor(private route:ActivatedRoute,
              private movieService:MediaService) {}

  ngOnInit() {
    this.movieId = this.route.snapshot.params['movieId'];
    this.typeMedia = this.route.snapshot.params['typeMedia'];

    if(this.typeMedia == "FILM") {
      this.movieService.getDetailsMovie(this.movieId).subscribe(
        (data: MediaDatabaseModel) => {
          this.media = data;
          this.image = this.media.image_portrait ? this.media.image_portrait : "";
        });
    }
    else {
      this.movieService.getDetailsSerie(this.movieId).subscribe(
        (data: MediaDatabaseModel) => {
          this.media = data;
          this.image = this.media.image_portrait ? this.media.image_portrait : "";
        });
    }
    //CARO--- --- tests pour voir si GetDetail est OK et c'est bien OK !!!!-------
    // this.movieService.getDetailsSerie(this.movieId).subscribe(
    //   (data:MediaDatabaseModel) => {
    //     this.media=data;
    //     for (const saison of this.media.saisons) {
    //       console.log("Saison:", saison);
    //     }
    //   });
  }


  setAvancement(image: string) {
    this.image = image;
  }

  get imagePortrait$() {
    return this.image;
  }

  // getters
  get genreList(): Genre[] { // TODO : normalement, ce devrait être GenreModel mais il faudrait modifier MediaDatabaseModel
    if(this.media) {
      return this.media.genres;
    }
    return [];
  }

  get titre(): string|null {
    return(this.media && this.media.titre) ? this.media.titre : null;
  }

  get dateSortie(): Date|null {
    return(this.media)? this.media.dateSortie : null;
  }

  get resume(): string|null {
    return(this.media) ? this.media.resume : "";
  }

  get duree(): number|undefined {
    return(this.media) ? this.media.duree : undefined;
  }

  get scoreDataBase():number {
    return(this.media) ? this.media.scoreDataBase : 0;
  }
}
