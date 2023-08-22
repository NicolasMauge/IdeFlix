import {Component} from '@angular/core';
import {MenuService} from "../../services/common/menu.service";
import {AuthService} from "../../../auth/services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private menuService: MenuService,
              private authService: AuthService) {
  }

  // par défaut le menu s'affiche dans la barre de navigation
  get hideMenu(): boolean {
    return this.menuService.hideMenu;
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

}

