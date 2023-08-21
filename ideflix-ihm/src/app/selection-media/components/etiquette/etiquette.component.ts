import { Component } from '@angular/core';
import {Status} from "../../../core/models/status";
import {MediaMaListeModel} from "../../../ma-liste-de-selection/models/media-ma-liste.model";
import {EtiquetteModel} from "../../shared/model/EtiquetteModel";
import {EtiquettesService} from "../../shared/services/etiquettes.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-etiquette',
  templateUrl: './etiquette.component.html',
  styleUrls: ['./etiquette.component.css']
})
export class EtiquetteComponent {
    protected readonly Status = Status;
    etiquettes: String[] = [];
    sub!: Subscription;

    constructor(private etiquetteService:EtiquettesService) {}

    ngOnInit() {

    }
}
