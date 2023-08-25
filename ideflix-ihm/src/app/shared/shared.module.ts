import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ValuesFromKeyPipe} from "./pipes/values-from-key.pipe";
import {PrintImgPipe} from "./pipes/print-img.pipe";
import {AddCheckedPropertyPipe} from "./pipes/add-checked-property.pipe";
import {FilterMaListePipe} from "./pipes/filter-ma-liste.pipe";
import { MapIhmStatutToBackendPipe } from './pipes/map-ihm-statut-to-backend.pipe';
import { MapBackendStatutToIhmPipe } from './pipes/map-backend-statut-to-ihm.pipe';
import {PrintDurationPipe} from "./pipes/print-duration.pipe";




@NgModule({
  declarations: [
    FilterMaListePipe,
    ValuesFromKeyPipe,
    PrintImgPipe,
    AddCheckedPropertyPipe,
    MapIhmStatutToBackendPipe,
    MapBackendStatutToIhmPipe,
    PrintDurationPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FilterMaListePipe,
    ValuesFromKeyPipe,
    PrintImgPipe,
    AddCheckedPropertyPipe,
    MapBackendStatutToIhmPipe,
    PrintDurationPipe
  ]
})
export class SharedModule { }
