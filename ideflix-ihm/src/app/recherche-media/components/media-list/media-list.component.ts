import { Component } from '@angular/core';
import {MediaModel} from "../../../core/models/media.model";
import {Subscription} from "rxjs";
import {MenuService} from "../../../core/services/common/menu.service";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";

@Component({
  selector: 'app-media-list',
  templateUrl: './media-list.component.html',
  styleUrls: ['./media-list.component.css']
})
export class MediaListComponent {

  medias: MediaDatabaseModel[] = [];
  sub!: Subscription;
  page!: number;

  constructor(private menuService: MenuService,
              private mediaSvc: MediaService ) {
  }

  ngOnInit() {
    this.menuService.hideMenu = false;
    this.page = 1;

    // // requête GET à TMDB pour récupérer la liste des films
    // this.mediaSvc.getMoviesFromApi();
    //
    // //abonnement à la source service.movies$  via un subscribe
    // this.sub = this.mediaSvc.movies$.subscribe( (data: MediaModel[]) => this.medias = data);

    // requête GET à TMDB pour récupérer la liste des films
    this.mediaSvc.getMoviesFromApi2(this.page);

    //abonnement à la source service.movies$  via un subscribe
    this.sub = this.mediaSvc.medias$.subscribe( (data: MediaDatabaseModel[]) =>{
      this.medias = data,
      console.log('getMovies: ', this.medias)
    });
  }

  ngOnDestroy(){
    this.sub.unsubscribe
  }

}
