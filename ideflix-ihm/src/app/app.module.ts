import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RegisterComponent} from './auth/components/register/register.component';
import {AccueilComponent} from './auth/components/accueil/accueil.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LoginComponent} from './auth/components/login/login.component';
import {NavbarComponent} from './core/components/navbar/navbar.component';
import {MaListeComponent} from './ma-liste-de-selection/components/ma-liste/ma-liste.component';
import {PrintImgPipe} from './shared/pipes/print-img.pipe';
import {MediaListComponent} from './recherche-media/components/media-list/media-list.component';
import {SearchbarComponent} from './recherche-media/components/searchbar/searchbar.component';
import {FilterMaListePipe} from './shared/pipes/filter-ma-liste.pipe';
import {ValuesFromKeyPipe} from './shared/pipes/values-from-key.pipe';
import {MaListeFiltreComponent} from './ma-liste-de-selection/components/ma-liste-filtre/ma-liste-filtre.component';
import {TokenInterceptor} from "./core/interceptors/token.interceptor";
import {ErrorInterceptor} from "./core/interceptors/error.interceptor";
import {MesPreferencesComponent} from './mes-preferences/components/mes-preferences/mes-preferences.component';
import {DetailMediaComponent} from './selection-media/components/detail-media/detail-media.component';
import {AddCheckedPropertyPipe} from './shared/pipes/add-checked-property.pipe';
import {CoreModule} from "./core/core.module";
import {AuthModule} from "./auth/auth.module";
import {MaListeDeSelectionModule} from "./ma-liste-de-selection/ma-liste-de-selection.module";
import {SharedModule} from "./shared/shared.module";
import {MesPreferencesModule} from "./mes-preferences/mes-preferences.module";
import {SelectionMediaModule} from "./selection-media/selection-media.module";
import {RechercheMediaModule} from "./recherche-media/recherche-media.module";
import {AdminUtilisateursComponent} from './admin/components/admin-utilisateurs/admin-utilisateurs.component';
import {AdminModule} from "./admin/admin.module";

@NgModule({
  declarations: [
    AppComponent,
    // AdminUtilisateursComponent,
    // LoginComponent,
    // RegisterComponent,
    // AccueilComponent,
    // NavbarComponent,
    // MaListeComponent,
    // PrintImgPipe,
    // MediaListComponent,
    // SearchbarComponent,
    // FilterMaListePipe,
    // ValuesFromKeyPipe,
    // MaListeFiltreComponent,
    // MesPreferencesComponent,
    // DetailMediaComponent,
    // AddCheckedPropertyPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    // MatSnackBarModule,
    FormsModule,
    CoreModule,
    SharedModule,
    AuthModule,
    AdminModule,
    MaListeDeSelectionModule,
    MesPreferencesModule,
    SelectionMediaModule,
    RechercheMediaModule
  ],
  // providers: [
  //   { provide : HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  //   { provide : HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  // ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
