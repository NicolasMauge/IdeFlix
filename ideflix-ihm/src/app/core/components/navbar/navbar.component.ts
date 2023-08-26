import {Component, OnDestroy, OnInit} from '@angular/core';
import {MenuService} from "../../services/common/menu.service";
import {AuthService} from "../../../auth/services/auth.service";
import {MesPreferencesService} from "../../services/preferences/mes-preferences.service";
import {PreferenceModel} from "../../models/preference.model";
import {Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {

  // preferences: PreferenceModel | undefined;
  preferences$!: Observable<PreferenceModel>;
  souscription!: Subscription;

  constructor(private menuService: MenuService,
              private authService: AuthService,
              private mesPreferencesService: MesPreferencesService,
              private router: Router) {
  }

  get hideMenu(): boolean {
    return this.menuService.hideMenu;
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.preferences$ = this.mesPreferencesService.preferences$;
    const email = localStorage.getItem('email');
    if (email !== null) {
      this.mesPreferencesService.getPreferencesFromApi(email);
      this.souscription = this.mesPreferencesService.preferences$.subscribe(data => {
      });
    }


  }

  ngOnDestroy(): void {
    this.souscription.unsubscribe();
  }

  afficherPseudo(): boolean {
    //this.mesPreferencesService.getPreferences();
    let result: boolean = !(['/', '/register', '/login', '/logout'].includes(this.router.url));
    //console.log("URL : " + this.router.url + " - affichage = " + result);


    return result;
  }

}

