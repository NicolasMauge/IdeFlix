import {Component, Input} from '@angular/core';
import {Status} from "../../../core/models/status";
import {Subscription} from "rxjs";
import {EtiquettesService} from "../../shared/services/etiquettes.service";
import {EtiquetteModel} from "../../shared/model/EtiquetteModel";
import {MediaToAppService} from "../../shared/services/media-to-app.service";
import {MediaModel} from "../../../core/models/media.model";
import {MediaSelectionneModel} from "../../shared/model/MediaSelectionneModel";
import {MediaAppModel} from "../../../core/models/media-app.model";

@Component({
  selector: 'app-ajout-media',
  templateUrl: './ajout-media.component.html',
  styleUrls: ['./ajout-media.component.css']
})
export class AjoutMediaComponent {
  nouveauMedia: any = {status: '', listTag: []};
  protected readonly Status = Status;
  statusEnum = Status;
  etiquettes: EtiquetteModel[] = [];
  @Input() media!: MediaModel;
  @Input() typeMedia!:boolean;
  email: string|null = "";

  constructor(private etiquetteService:EtiquettesService, private mediaAppService:MediaToAppService) {
  }

  ngOnInit() {
    this.email = localStorage.getItem('email');

    if (this.email !== null) {
      this.etiquetteService.loadEtiquettes(this.email);
      this.etiquetteService.etiquettes$.subscribe( (data: EtiquetteModel[]) => this.etiquettes = data);
    }
    else {
      console.log('email non présent dans le localstorage');
      //this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
      //this.route.navigate(['/login']);
    }
  }

  public createEtiquette() {
    console.log("coucou");
  }

  public addEtiquette(etiquette: EtiquetteModel) {
    let found = false;
    for(let i=0;i<this.nouveauMedia.listTag.length;i++) {
      if(this.nouveauMedia.listTag[i].nomTag == etiquette.nomTag) {
        found = true;
        break;
      }
    }
    if(!found) {
      this.nouveauMedia.listTag.push(etiquette);
    }
  }

  OnSubmitAdd(event: Event) {
    event.preventDefault();

    let mediaObject = {
      idTmdb:this.media.idTmdb,
      typeMedia:this.typeMedia ? "FILM" : "SERIE",
      titre: this.media.titre,
      dateSortie: this.media.date,
      duree: this.media.duration,
      resume: this.media.resume,
      cheminAffichePortrait: this.media.image_portrait,
      cheminAffichePaysage: this.media.image_landscape,
      noteTmdb: this.media.score,
      genreList: this.media.genres
    }

    let mediaApp = new MediaAppModel(mediaObject);

    let mediaSelectionneObject = {
      typeMedia: this.typeMedia ? "FILM" : "SERIE",
      avisPouce:false,
      dateSelection: '2023-08-17',
      etiquetteList: this.nouveauMedia.listTag,
      statutMedia: this.nouveauMedia.status,
      media: mediaApp,
      email: this.email,
      dateModification: '2023-08-17',
      numeroSaison: 0,
      idTmdbSaison: 0,
      numeroEpisode: 0,
      idTmdbEpisode: 0
    }

    let mediaSelectionne = new MediaSelectionneModel(mediaSelectionneObject);

    console.log(mediaSelectionne);

    /*
    let mediaSelectionne : MediaSelectionneModel = new MediaSelectionneModel();

    //console.log(this.nouveauMedia);
    this.mediaAppService.saveToApp(mediaSelectionne);*/
  }

  // getter setter

}
