import { Component } from '@angular/core';
import {MenuService} from "../../shared/services/menu.service";
import {Subscription} from "rxjs";
import {MediaMaListeService} from "../../shared/services/media-ma-liste.service";
import {MediaMaListeModel} from "../../shared/models/media-ma-liste.model";

@Component({
  selector: 'app-ma-liste',
  templateUrl: './ma-liste.component.html',
  styleUrls: ['./ma-liste.component.css']
})
export class MaListeComponent {

  medias: MediaMaListeModel[] = [];
  sub!: Subscription;

  constructor(private menuService: MenuService,
              private mediaSvc: MediaMaListeService ) {
  }

  ngOnInit() {
    this.menuService.hideMenu = false;

    // requête GET à TMDB pour récupérer la liste des films
    this.mediaSvc.getMoviesFromApi();

    //abonnement à la source service.movies$  via un subscribe
    this.sub = this.mediaSvc.mediaMaListe$.subscribe( (data: MediaMaListeModel[]) => this.medias = data);
  }

  ngOnDestroy(){
    this.sub.unsubscribe
  }

}
