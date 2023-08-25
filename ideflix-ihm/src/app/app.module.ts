import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CoreModule} from "./core/core.module";
import {AuthModule} from "./auth/auth.module";
import {MaListeDeSelectionModule} from "./ma-liste-de-selection/ma-liste-de-selection.module";
import {SharedModule} from "./shared/shared.module";
import {MesPreferencesModule} from "./mes-preferences/mes-preferences.module";
import {SelectionMediaModule} from "./selection-media/selection-media.module";
import {RechercheMediaModule} from "./recherche-media/recherche-media.module";
import {AdminModule} from "./admin/admin.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
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
  bootstrap: [AppComponent]
})
export class AppModule {
}
