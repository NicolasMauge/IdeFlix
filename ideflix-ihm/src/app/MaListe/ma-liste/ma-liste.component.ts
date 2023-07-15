import { Component } from '@angular/core';
import {MenuService} from "../../shared/services/menu.service";
import {Subscription} from "rxjs";
import {MediaModel} from "../../shared/models/media.model";
import {MediaMaListeService} from "../../shared/services/media-ma-liste.service";

@Component({
  selector: 'app-ma-liste',
  templateUrl: './ma-liste.component.html',
  styleUrls: ['./ma-liste.component.css']
})
export class MaListeComponent {

  medias: MediaModel[] = [];
  sub!: Subscription;

  constructor(private menuService: MenuService,
              private mediaSvc: MediaMaListeService ) {
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
