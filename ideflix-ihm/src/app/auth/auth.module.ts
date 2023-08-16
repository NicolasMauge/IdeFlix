import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ReactiveFormsModule} from "@angular/forms";
import {AccueilComponent} from "./components/accueil/accueil.component";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    AccueilComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    NgOptimizedImage
  ],
  exports: [
    AccueilComponent,
    LoginComponent,
    RegisterComponent
  ]
})
export class AuthModule { }
