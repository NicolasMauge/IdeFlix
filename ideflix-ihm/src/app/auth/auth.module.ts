import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ReactiveFormsModule} from "@angular/forms";
import {AccueilComponent} from "./components/accueil/accueil.component";
import {RouterModule} from "@angular/router";
import { LogoutComponent } from './components/logout/logout.component';



@NgModule({
  declarations: [
    AccueilComponent,
    LoginComponent,
    RegisterComponent,
    LogoutComponent
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
