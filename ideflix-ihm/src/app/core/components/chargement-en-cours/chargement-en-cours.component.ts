import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-chargement-en-cours',
  templateUrl: './chargement-en-cours.component.html',
  styleUrls: ['./chargement-en-cours.component.css']
})
export class ChargementEnCoursComponent {
  @Input() message!: string;
}
