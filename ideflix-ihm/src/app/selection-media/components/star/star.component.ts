import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.css']
})
export class StarComponent {
  @Input() score!: number;
  scoreArray: Array<number> = [];

  ngOnInit() {
    this.score = Math.round(this.score/2);
    this.scoreArray = new Array(this.score).fill(0);
  }
}
