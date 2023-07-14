import { Component } from '@angular/core';
import {MenuService} from "../../shared/services/menu.service";

@Component({
  selector: 'app-ma-liste',
  templateUrl: './ma-liste.component.html',
  styleUrls: ['./ma-liste.component.css']
})
export class MaListeComponent {

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.menuService.hideMenu = false;
  }

}
