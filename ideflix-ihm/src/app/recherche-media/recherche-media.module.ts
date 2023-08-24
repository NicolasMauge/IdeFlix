import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MediaListComponent} from "./components/media-list/media-list.component";
import {SearchbarComponent} from "./components/searchbar/searchbar.component";
import {SharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {CoreModule} from "../core/core.module";


@NgModule({
  declarations: [
    MediaListComponent,
    SearchbarComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    CoreModule
  ],
  exports: [
    MediaListComponent,
    SearchbarComponent
  ]
})
export class RechercheMediaModule {
}
