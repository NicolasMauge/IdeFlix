import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MaListeComponent} from "./components/ma-liste/ma-liste.component";
import {MaListeFiltreComponent} from "./components/ma-liste-filtre/ma-liste-filtre.component";
import {FormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {CoreModule} from "../core/core.module";


@NgModule({
  declarations: [
    MaListeComponent,
    MaListeFiltreComponent
  ],
  exports: [
    MaListeComponent,
    MaListeFiltreComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    SharedModule,
    CoreModule
  ]
})
export class MaListeDeSelectionModule {
}
