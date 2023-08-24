import {Component} from '@angular/core';
import {MenuService} from "../../../core/services/common/menu.service";
import {Subscription} from "rxjs";
import {MediaMaListeService} from "../../../core/services/maListe/media-ma-liste.service";
import {MediaMaListeModel} from "../../models/media-ma-liste.model";
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";
import {FilterMaListePipe} from "../../../shared/pipes/filter-ma-liste.pipe";


@Component({
  selector: 'app-ma-liste',
  templateUrl: './ma-liste.component.html',
  styleUrls: ['./ma-liste.component.css'],
  providers: [FilterMaListePipe]
})
export class MaListeComponent {

  medias: MediaMaListeModel[] = [];
  sub!: Subscription;
  myFilter: any = {status: '', genre: ''};
  // myFilter: any = {status: ''};


  constructor(private menuService: MenuService,
              private mediaSvc: MediaMaListeService,
              private messageSvc: MessageService,
              private route: Router,
              private filtrerMaListePipe: FilterMaListePipe) {}

  ngOnInit() {
    this.menuService.hideMenu = false;

  //   // requête pour récupérer les préférences de l'utilisateur et charger la page
  //   const email = localStorage.getItem('email');
  //   if (email !== null) {
  //   // requête GET à TMDB pour récupérer la liste des films
  //   this.mediaSvc.getMoviesFromApi(email);
  //
  //   //abonnement à la source mediaMaListe$ contenant la liste de tous les médias sélectionnés   via un subscribe
  //   this.sub = this.mediaSvc.mediaMaListe$.subscribe( (data: MediaMaListeModel[]) => this.medias = data);
  // } else {
  //     console.log('email non présent dans le localstorage');
  //     this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
  //     this.route.navigate(['/login']);
  //   }
    this.chargementMaListe();
  }

  ngOnDestroy(){
    this.sub.unsubscribe
  }

  store(filterEvent: {status: string, genre : string, etiquette : string}){
    console.log('status: ' + filterEvent.status);
    console.log('genre: ' + filterEvent.genre);
    console.log('etiquette: ' + filterEvent.etiquette);

    this.myFilter.status = filterEvent.status;
    this.myFilter.genre = filterEvent.genre;
    this.myFilter.etiquette = filterEvent.etiquette;
    if (this.myFilter.status === '' && this.myFilter.genre === '' && this.myFilter.etiquette === '') {
      this.ReinitializedMediaList();
    } else {
      this.updateFilteredMediaList();
    }
  }


  updateFilteredMediaList() {
    //repartir de la liste initiale avant d'appliquer le filtre
    this.mediaSvc.reinitializedMediaList();
    // Utilisez le pipe de filtrage pour obtenir la liste filtrée
    const filteredList = this.filtrerMaListePipe.transform(this.medias, this.myFilter);
    this.mediaSvc.updateFilteredMediaList(filteredList);
  }

  ReinitializedMediaList() {
    this.mediaSvc.reinitializedMediaList();
  }

  chargementMaListe() : void {
    // requête pour récupérer les préférences de l'utilisateur et charger la page
    const email = localStorage.getItem('email');
    if (email !== null) {
      // requête GET à TMDB pour récupérer la liste des films
      this.mediaSvc.getMoviesFromApi(email);

      //abonnement à la source mediaMaListe$ contenant la liste de tous les médias sélectionnés   via un subscribe
      this.sub = this.mediaSvc.mediaMaListe$.subscribe( (data: MediaMaListeModel[]) => this.medias = data);
    } else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);
    }
  }

}
