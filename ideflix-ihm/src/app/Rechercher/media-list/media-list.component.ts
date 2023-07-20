import { Component } from '@angular/core';
import {MediaModel} from "../../shared/models/media.model";
import {Subscription} from "rxjs";
import {MenuService} from "../../shared/services/menu.service";
import {MediaService} from "../../shared/services/media.service";

@Component({
  selector: 'app-media-list',
  templateUrl: './media-list.component.html',
  styleUrls: ['./media-list.component.css']
})
export class MediaListComponent {

  medias: MediaModel[] = [];
  sub!: Subscription;

  constructor(private menuService: MenuService,
              private mediaSvc: MediaService ) {
  }

  ngOnInit() {
    this.menuService.hideMenu = false;

    // requête GET à TMDB pour récupérer la liste des films
    this.mediaSvc.getMoviesFromApi();

    //abonnement à la source service.movies$  via un subscribe
    this.sub = this.mediaSvc.movies$.subscribe( (data: MediaModel[]) => this.medias = data);
  }

  ngOnDestroy(){
    this.sub.unsubscribe
  }

}