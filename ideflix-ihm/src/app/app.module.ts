import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { RegisterComponent } from './auth/register/register.component';
import { AccueilComponent } from './accueil/accueil.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './auth/login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MaListeComponent } from './MaListe/ma-liste/ma-liste.component';
import { PrintImgPipe } from './shared/pipes/print-img.pipe';
import { MediaListComponent } from './media-list/media-list.component';
import { SearchbarComponent } from './searchbar/searchbar.component';
import { FilterMaListePipe } from './shared/pipes/filter-ma-liste.pipe';
import { ValuesFromKeyPipe } from './shared/pipes/values-from-key.pipe';
import { MaListeFiltreComponent } from './MaListe/ma-liste-filtre/ma-liste-filtre.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccueilComponent,
    NavbarComponent,
    MaListeComponent,
    PrintImgPipe,
    MediaListComponent,
    SearchbarComponent,
    FilterMaListePipe,
    ValuesFromKeyPipe,
    MaListeFiltreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
