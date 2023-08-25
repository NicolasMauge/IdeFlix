import {Component} from '@angular/core';
import {MenuService} from "../../../core/services/common/menu.service";
import {Observable} from "rxjs";
import {MediaMaListeService} from "../../services/media-ma-liste.service";
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


  mediaMaListe$!: Observable<MediaMaListeModel[]>;
  myFilter: any = {status: '', genre: ''};

  constructor(private menuService: MenuService,
              private mediaSvc: MediaMaListeService,
              private messageSvc: MessageService,
              private route: Router,
              private filtrerMaListePipe: FilterMaListePipe) {}

  ngOnInit() {
    this.menuService.hideMenu = false;

    // requête pour récupérer les préférences de l'utilisateur et charger la page
    const email = localStorage.getItem('email');
    if (email !== null) {
    // requête GET à API App-Ideflix pour récupérer la liste des films
    this.mediaSvc.getMoviesFromApi(email);

    //charger la liste de médias en utilisant le pipe async
    this.mediaMaListe$ = this.mediaSvc.mediaMaListe$

  } else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);
    }
  }


  store(filterEvent: {status: string, genre : string, etiquette : string}){

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
    const filteredList = this.filtrerMaListePipe.transform(this.mediaSvc.getValueOfMediaMaListe$(), this.myFilter);
    this.mediaSvc.updateFilteredMediaList(filteredList);
  }

  ReinitializedMediaList() {
    this.mediaSvc.reinitializedMediaList();
  }
}
