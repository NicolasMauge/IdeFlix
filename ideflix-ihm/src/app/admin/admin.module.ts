import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminUtilisateursComponent} from "./components/admin-utilisateurs/admin-utilisateurs.component";


@NgModule({
  declarations: [
    AdminUtilisateursComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    AdminUtilisateursComponent
  ]
})
export class AdminModule {
}
