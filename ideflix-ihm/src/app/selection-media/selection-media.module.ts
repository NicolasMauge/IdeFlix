import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DetailMediaComponent} from "./components/detail-media/detail-media.component";
import {SharedModule} from "../shared/shared.module";
import { PrintDurationPipe } from './shared/pipe/print-duration.pipe';
import { StarComponent } from './components/star/star.component';
import { AjoutMediaComponent } from './components/ajout-media/ajout-media.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatMenuModule} from "@angular/material/menu";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import { DialogEtiquettesComponent } from './components/dialog-etiquettes/dialog-etiquettes.component';
import {MatDialogModule} from "@angular/material/dialog";
import { ChoixSaisonEpisodeComponent } from './components/choix-saison-episode/choix-saison-episode.component';
import {MatButtonModule} from "@angular/material/button";


@NgModule({
  declarations: [
    DetailMediaComponent,
    PrintDurationPipe,
    StarComponent,
    AjoutMediaComponent,
    DialogEtiquettesComponent,
    ChoixSaisonEpisodeComponent
  ],
    imports: [
        CommonModule,
        SharedModule,
        FormsModule,
        ReactiveFormsModule,
        MatMenuModule,
        MatInputModule,
        MatSelectModule,
        MatDialogModule,
        MatButtonModule,
    ],
  exports: [
    DetailMediaComponent
  ]
})
export class SelectionMediaModule { }
