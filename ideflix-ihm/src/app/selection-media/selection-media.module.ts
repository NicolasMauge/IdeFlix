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
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import { DialogEtiquettesComponent } from './components/dialog-etiquettes/dialog-etiquettes.component';
import {MatDialogModule} from "@angular/material/dialog";
import { ChoixSaisonEpisodeComponent } from './components/choix-saison-episode/choix-saison-episode.component';


@NgModule({
  declarations: [
    DetailMediaComponent,
    PrintDurationPipe,
    StarComponent,
    AjoutMediaComponent,
    EtiquetteComponent,
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
    // TODO : à supprimer dès que possible
  ],
  exports: [
    DetailMediaComponent
  ]
})
export class SelectionMediaModule { }
