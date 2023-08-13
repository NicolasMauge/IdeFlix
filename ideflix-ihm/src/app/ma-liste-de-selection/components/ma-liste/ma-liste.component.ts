import {Component} from '@angular/core';
import {MenuService} from "../../../core/services/common/menu.service";
import {filter, Subscription} from "rxjs";
import {MediaMaListeService} from "../../services/maListe/media-ma-liste.service";
import {MediaMaListeModel} from "../../models/media-ma-liste.model";
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-ma-liste',
  templateUrl: './ma-liste.component.html',
  styleUrls: ['./ma-liste.component.css']
})
export class MaListeComponent {

  medias: MediaMaListeModel[] = [];
  sub!: Subscription;
  myFilter: any = {status: '', genre: ''};


  constructor(private menuService: MenuService,
              private mediaSvc: MediaMaListeService,
              private messageSvc: MessageService,
              private route: Router) {}

  ngOnInit() {
    this.menuService.hideMenu = false;

    // requête pour récupérer les préférences de l'utilisateur et charger la page
    const email = localStorage.getItem('email');
    if (email !== null) {
    // requête GET à TMDB pour récupérer la liste des films
    this.mediaSvc.getMoviesFromApi2(email);

    //abonnement à la source service.movies$  via un subscribe
    this.sub = this.mediaSvc.mediaMaListe$.subscribe( (data: MediaMaListeModel[]) => this.medias = data);
  }else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);
    }
  }

  ngOnDestroy(){
    this.sub.unsubscribe
  }

  store(filterEvent: {status: string, genre: string}){
    console.log('status: ' + filterEvent.status);
    console.log('genre: ' + filterEvent.genre);
    this.myFilter.status = filterEvent.status;
    this.myFilter.genre = filterEvent.genre;
  }

  protected readonly filter = filter;
}
