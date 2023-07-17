import { Component } from '@angular/core';
import {MenuService} from "../shared/services/menu.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

constructor(private menuService : MenuService) {
}
 // par défaut le menu s'affiche dans la barre de navigation
get hideMenu():boolean {
  return this.menuService.hideMenu;
}
}

