import {Component} from '@angular/core';
import {MenuService} from "../../shared/services/common/menu.service";
import {filter, Subscription} from "rxjs";
import {MediaMaListeService} from "../../shared/services/maListe/media-ma-liste.service";
import {MediaMaListeModel} from "../../shared/models/media-ma-liste.model";

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

  store(filterEvent: {status: string, genre: string}){
    console.log('status: ' + filterEvent.status);
    console.log('genre: ' + filterEvent.genre);
    this.myFilter.status = filterEvent.status;
    this.myFilter.genre = filterEvent.genre;
  }

  protected readonly filter = filter;
}
