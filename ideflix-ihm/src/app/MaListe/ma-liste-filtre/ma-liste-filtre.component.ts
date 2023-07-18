import {Component, EventEmitter, Output} from '@angular/core';
import {Status} from "../../shared/models/status";
import {filter} from "rxjs";

@Component({
  selector: 'app-ma-liste-filtre',
  templateUrl: './ma-liste-filtre.component.html',
  styleUrls: ['./ma-liste-filtre.component.css']
})
export class MaListeFiltreComponent {

  myFilter: any = {status: '', genre: ''};
  statusEnum = Status;
  genres: string[] = ['Action', 'Drame', 'Comédie', 'Aventure', 'Science-Fiction']; //TODO en attendant d'avoir la table des genres via API

  @Output() filterEvent = new EventEmitter<{status : string, genre : string}>();
  // filterEvent: EventEmitter<{ status: string, genre: string }> = new EventEmitter<{ status: string, genre: string }>();
  onSubmitFilter(statusInput: string, genreInput: string) {
    this.filterEvent.emit({status: statusInput, genre: genreInput});
  }

  protected readonly Status = Status;
  protected readonly filter = filter;
}
