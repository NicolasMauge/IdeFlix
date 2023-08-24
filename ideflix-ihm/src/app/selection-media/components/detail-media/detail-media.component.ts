import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {GenreModel} from "../../../core/models/genre.model";

interface Genre {
  idDatabase: number;
  nomGenre: string;
}

export interface SaisonEpisode {
  saison: number,
  episode: number
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
  image : string = "";
  resume: string = "";
  dateSortie: Date = new Date();
  typeResume: string = "Resumé";
  typeDateSortie: string = "Date de sortie";

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
          this.resume = this.media.resume;
          this.dateSortie = this.media.dateSortie;
        });
    }
    else {
      this.movieService.getDetailsSerie(this.movieId).subscribe(
        (data: MediaDatabaseModel) => {
          this.media = data;
          this.image = this.media.image_portrait ? this.media.image_portrait : "";
          this.resume = this.media.resume;
          this.dateSortie = this.media.dateSortie;
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


  setSaison(numeroSaison: number) {
    this.image = this.media.saisons[numeroSaison].image_portraitSaison;

    this.typeResume = "Résumé de la saison "+numeroSaison;
    this.resume = this.media.saisons[numeroSaison].resumeSaison;

    this.typeDateSortie = "Date de sortie de la saison "+numeroSaison;
    this.dateSortie = this.media.saisons[numeroSaison].dateSortieSaison;
  }

  setResume(resume: string) {
    this.resume = resume;
  }

  get imagePortrait$() {
    return this.image;
  }

  // getters
  get genreList(): Genre[] { // TODO : normalement, ce devrait être GenreModel mais il faudrait modifier MediaDatabaseModel
    return (this.media)? this.media.genres : [];
  }

  get titre(): string|null {
    return(this.media && this.media.titre) ? this.media.titre : null;
  }

  get dateSortie$(): Date|null {
    return this.dateSortie;
  }

  get resume$(): string|null {
    return this.resume;
  }

  get duree(): number|undefined {
    return(this.media) ? this.media.duree : undefined;
  }

  get scoreDataBase():number {
    return(this.media) ? this.media.scoreDataBase : 0;
  }
}
