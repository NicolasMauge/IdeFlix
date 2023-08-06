import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FilterMaListePipe} from "./pipes/filter-ma-liste.pipe";
import {ValuesFromKeyPipe} from "./pipes/values-from-key.pipe";
import {PrintImgPipe} from "./pipes/print-img.pipe";
import {AddCheckedPropertyPipe} from "./pipes/add-checked-property.pipe";



@NgModule({
  declarations: [
    FilterMaListePipe,
    ValuesFromKeyPipe,
    PrintImgPipe,
    AddCheckedPropertyPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FilterMaListePipe,
    ValuesFromKeyPipe,
    PrintImgPipe,
    AddCheckedPropertyPipe
  ]
})
export class SharedModule { }
