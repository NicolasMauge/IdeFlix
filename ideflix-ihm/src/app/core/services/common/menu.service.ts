import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  // par défaut le menu s'affiche dans la barre de navigation
  hideMenu: boolean = false;
}
