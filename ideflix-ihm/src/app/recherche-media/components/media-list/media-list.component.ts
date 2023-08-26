import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {MenuService} from "../../../core/services/common/menu.service";
import {MediaService} from "../../../core/services/media/media.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-media-list',
  templateUrl: './media-list.component.html',
  styleUrls: ['./media-list.component.css']
})
export class MediaListComponent implements OnInit, OnDestroy {

  medias: MediaDatabaseModel[] = [];
  sub!: Subscription;
  page!: number;
  afficheChargementSuite: boolean = false;

  constructor(private menuService: MenuService,
              private mediaSvc: MediaService,
              private messageSvc: MessageService,
              private route: Router) {
  }

  ngOnInit() {

    this.menuService.hideMenu = false;
    this.page = 1;
    addEventListener("scrollend", () => {
      this.chargerLaSuite();
    });

    // this.menuService.hideMenu = false;
    // this.page = 1;

    // // requête GET à TMDB pour récupérer la liste des films
    // this.mediaSvc.getMoviesFromApi();
    //
    // //abonnement à la source service.movies$  via un subscribe
    // this.sub = this.mediaSvc.movies$.subscribe( (data: MediaModel[]) => this.medias = data);

    // requête GET à TMDB pour récupérer la liste des films
    const email = localStorage.getItem('email');
    if (email !== null) {
      this.mediaSvc.getMoviesFromApi(email, this.page);

    //abonnement à la source service.movies$  via un subscribe
      this.sub = this.mediaSvc.medias$.subscribe((data: MediaDatabaseModel[]) => {
      // this.medias = data; // Pour faire par page
      this.medias = [...this.medias, ...data]; // Pour cumuler les pages supplémentaires dans la même page
      this.afficheChargementSuite = false;
      //console.log('getMovies: ', this.medias)
    });
  }else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de connexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);}
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
    removeEventListener("scrollend", this.chargerLaSuite);
  }

  chargerLaSuite() {
    this.page++;
    this.afficheChargementSuite = true;
    const email = localStorage.getItem('email');
    if (email !== null) {
      this.mediaSvc.getMoviesFromApi(email,this.page);

    // timeout pour éviter d'appeler l'api en boucle pendant le scrolling
      setTimeout(() => {
        return;
      }
      , 250);
  }else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de connexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);}
  }
}
