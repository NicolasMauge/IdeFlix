import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ReactiveFormsModule} from "@angular/forms";
import {AccueilComponent} from "./components/accueil/accueil.component";
import {RouterModule} from "@angular/router";
import {LogoutComponent} from './components/logout/logout.component';
import {CoreModule} from "../core/core.module";

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
    NgOptimizedImage,
    CoreModule
  ],
  exports: [
    AccueilComponent,
    LoginComponent,
    RegisterComponent
  ]
})
export class AuthModule {
}
