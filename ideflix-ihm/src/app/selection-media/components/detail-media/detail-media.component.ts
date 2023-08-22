import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";

@Component({
  selector: 'app-detail-media',
  templateUrl: './detail-media.component.html',
  styleUrls: ['./detail-media.component.css']
})
export class DetailMediaComponent {
  movieId!: number;
  media!:MediaDatabaseModel;
  typeMedia!:string; // TODO : avoir le boolean film / serie

  constructor(private route:ActivatedRoute, private movieService:MediaService) {}

  ngOnInit() {
    this.movieId = this.route.snapshot.params['movieId'];

    this.movieService.getDetailsMovie(this.movieId).subscribe(
      (data:MediaDatabaseModel) =>
        this.media=data);

    //CARO--- --- tests pour voir si GetDetail est OK et c'est bien OK !!!!-------
    // this.movieService.getDetailsSerie(this.movieId).subscribe(
    //   (data:MediaDatabaseModel) => {
    //     this.media=data;
    //     for (const saison of this.media.saisons) {
    //       console.log("Saison:", saison);
    //     }
    //   });
  }

  // getters
  get titre() {
    return(this.media && this.media.titre) ? this.media.titre : null;
  }

}
