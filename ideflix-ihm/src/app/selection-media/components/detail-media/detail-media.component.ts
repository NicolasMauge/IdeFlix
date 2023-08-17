import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaModel} from "../../../core/models/media.model";

@Component({
  selector: 'app-detail-media',
  templateUrl: './detail-media.component.html',
  styleUrls: ['./detail-media.component.css']
})
export class DetailMediaComponent {
  movieId!: number;
  movie!:MediaModel;

  constructor(private route:ActivatedRoute, private movieService:MediaService) {}

  ngOnInit() {
    this.movieId = this.route.snapshot.params['movieId'];

    this.movieService.getDetails(this.movieId).subscribe(
      (data:MediaModel) => this.movie=data);
  }
}
