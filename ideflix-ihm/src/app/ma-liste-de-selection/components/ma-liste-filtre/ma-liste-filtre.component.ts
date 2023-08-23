import {Component, EventEmitter, Output} from '@angular/core';
import {Status} from "../../../core/models/status";
import {EtiquetteService} from "../../../core/services/etiquettes/etiquette.service";
import {EtiquetteModel} from "../../../core/models/etiquette.model";


@Component({
  selector: 'app-ma-liste-filtre',
  templateUrl: './ma-liste-filtre.component.html',
  styleUrls: ['./ma-liste-filtre.component.css']
})
export class MaListeFiltreComponent {

  myFilter: any = {status: '', genre: '', etiquette: ''};
  statusEnum = Status;
  genres: string[] = ['Action', 'Drame', 'Comédie', 'Aventure', 'Science-Fiction']; //TODO en attendant d'avoir la table des genres via API
  etiquettes: EtiquetteModel[] = [];

  constructor(private etiquetteService: EtiquetteService) {}

  ngOnInit() {
    // Chargez les étiquettes en utilisant le service
    const email = localStorage.getItem('email');
    if (email !== null) {
    this.etiquetteService.loadEtiquettes(email);

    // Souscrivez aux changements de l'observable etiquettes$ pour mettre à jour la liste d'étiquettes dans le composant
    this.etiquetteService.etiquettes$.subscribe((etiquettes: EtiquetteModel[]) => {
      this.etiquettes = etiquettes;
    });
  }}

  @Output() filterEvent = new EventEmitter<{status : string, genre : string, etiquette : string}>();
  // filterEvent: EventEmitter<{ status: string, genre: string }> = new EventEmitter<{ status: string, genre: string }>();
  onSubmitFilter(statusInput: string, genreInput: string, etiquetteInput: string) {
    this.filterEvent.emit({status: statusInput, genre: genreInput, etiquette: etiquetteInput});
  }

  // protected readonly Status = Status;
  // protected readonly filter = filter;
}
