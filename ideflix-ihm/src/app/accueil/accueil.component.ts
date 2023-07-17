import {Component, OnInit} from '@angular/core';
import {MenuService} from "../shared/services/menu.service";

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit{

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
      this.menuService.hideMenu = true;
  }
}
