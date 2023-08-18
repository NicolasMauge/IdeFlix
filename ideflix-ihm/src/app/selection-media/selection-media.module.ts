import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DetailMediaComponent} from "./components/detail-media/detail-media.component";
import {SharedModule} from "../shared/shared.module";
import { PrintDurationPipe } from './shared/pipe/print-duration.pipe';
import { StarComponent } from './components/star/star.component';
import { AjoutMediaComponent } from './components/ajout-media/ajout-media.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { EtiquetteComponent } from './components/etiquette/etiquette.component';
import {MatMenuModule} from "@angular/material/menu";



@NgModule({
  declarations: [
    DetailMediaComponent,
    PrintDurationPipe,
    StarComponent,
    AjoutMediaComponent,
    EtiquetteComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    MatMenuModule
  ],
  exports: [
    DetailMediaComponent
  ]
})
export class SelectionMediaModule { }