import {NgModule} from '@angular/core';
import {CommonModule, Location} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./interceptors/token.interceptor";
import {ErrorInterceptor} from "./interceptors/error.interceptor";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {RouterModule} from "@angular/router";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {StarComponent} from "./components/star/star.component";
import {ChargementEnCoursComponent} from './components/chargement-en-cours/chargement-en-cours.component';
import {ChargementSuiteComponent} from './components/chargement-suite/chargement-suite.component';
import { IndisponibleComponent } from './components/indisponible/indisponible.component';
import {PageNonTrouveeComponent} from "./components/page-non-trouvee/page-non-trouvee.component";

@NgModule({
  declarations: [
    NavbarComponent,
    StarComponent,
    ChargementEnCoursComponent,
    ChargementSuiteComponent,
    IndisponibleComponent,
    PageNonTrouveeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    MatSnackBarModule
  ],
  exports: [
    NavbarComponent,
    StarComponent,
    ChargementEnCoursComponent,
    ChargementSuiteComponent,
    IndisponibleComponent,
    PageNonTrouveeComponent
  ],

  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    Location
  ]
})
export class CoreModule {
}
