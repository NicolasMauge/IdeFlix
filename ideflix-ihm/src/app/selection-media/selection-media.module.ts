import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DetailMediaComponent} from "./components/detail-media/detail-media.component";
import {SharedModule} from "../shared/shared.module";
import { PrintDurationPipe } from './shared/pipe/print-duration.pipe';
import { StarComponent } from './components/star/star.component';
import { AjoutMediaComponent } from './components/ajout-media/ajout-media.component';



@NgModule({
  declarations: [
    DetailMediaComponent,
    PrintDurationPipe,
    StarComponent,
    AjoutMediaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    DetailMediaComponent
  ]
})
export class SelectionMediaModule { }
