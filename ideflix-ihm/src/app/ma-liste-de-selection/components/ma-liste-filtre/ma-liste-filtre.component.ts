import {Component, EventEmitter, Output} from '@angular/core';
import {Status} from "../../../core/models/status";


@Component({
  selector: 'app-ma-liste-filtre',
  templateUrl: './ma-liste-filtre.component.html',
  styleUrls: ['./ma-liste-filtre.component.css']
})
export class MaListeFiltreComponent {

  myFilter: any = {status: '', genre: '', etiquette: ''};
  statusEnum = Status;
  genres: string[] = ['Action', 'Drame', 'Comédie', 'Aventure', 'Science-Fiction']; //TODO en attendant d'avoir la table des genres via API
  etiquettes: string[] = ['tag1', 'tag2', 'tag3', 'tag4', 'super bien!' ];

  @Output() filterEvent = new EventEmitter<{status : string, genre : string, etiquette : string}>();
  // filterEvent: EventEmitter<{ status: string, genre: string }> = new EventEmitter<{ status: string, genre: string }>();
  onSubmitFilter(statusInput: string, genreInput: string, etiquetteInput: string) {
    this.filterEvent.emit({status: statusInput, genre: genreInput, etiquette: etiquetteInput});
  }

  // protected readonly Status = Status;
  // protected readonly filter = filter;
}
