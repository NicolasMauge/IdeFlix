import { Component } from '@angular/core';
import { Location } from  '@angular/common';

@Component({
  selector: 'app-page-non-trouvee',
  templateUrl: './page-non-trouvee.component.html',
  styleUrls: ['./page-non-trouvee.component.css']
})
export class PageNonTrouveeComponent {

  currentUrl: string;
  constructor(private location: Location) {
    this.currentUrl = this.location.path();
  }

}
