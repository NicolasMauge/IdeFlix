import { Component } from '@angular/core';
import {Status} from "../../shared/models/status";

@Component({
  selector: 'app-ma-liste-filtre',
  templateUrl: './ma-liste-filtre.component.html',
  styleUrls: ['./ma-liste-filtre.component.css']
})
export class MaListeFiltreComponent {

  myFilter: any = {status: '', genre: ''};
  statusEnum = Status;
  genres: string[] = ['Action', 'Drame', 'Comédie', 'Aventure', 'Science-Fiction']; //TODO en attendant d'avoir la table des genres via API

    protected readonly Status = Status;
}
