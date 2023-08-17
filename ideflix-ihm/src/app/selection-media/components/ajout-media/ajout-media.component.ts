import { Component } from '@angular/core';
import {Status} from "../../../core/models/status";
import {Subscription} from "rxjs";
import {EtiquettesService} from "../../shared/services/etiquettes.service";
import {EtiquetteModel} from "../../shared/model/EtiquetteModel";

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

  constructor(private etiquetteService:EtiquettesService) {
  }

  ngOnInit() {
    const email = localStorage.getItem('email');

    if (email !== null) {
      this.etiquetteService.loadEtiquettes(email);
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

    console.log(this.nouveauMedia);

  }
}
