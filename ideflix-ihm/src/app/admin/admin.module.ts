import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminUtilisateursComponent} from "./components/admin-utilisateurs/admin-utilisateurs.component";
import {CoreModule} from "../core/core.module";


@NgModule({
  declarations: [
    AdminUtilisateursComponent
  ],
  imports: [
    CommonModule,
    CoreModule
  ],
  exports: [
    AdminUtilisateursComponent
  ]
})
export class AdminModule {
}
