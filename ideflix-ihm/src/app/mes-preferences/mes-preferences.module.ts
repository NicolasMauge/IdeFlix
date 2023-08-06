import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MesPreferencesComponent} from "./components/mes-preferences/mes-preferences.component";
import { ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    MesPreferencesComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    MesPreferencesComponent
  ]
})
export class MesPreferencesModule { }
