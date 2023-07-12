import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {AccueilComponent} from "./accueil/accueil.component";

// tableau des routes
const routes: Routes = [
  {path:'', component: AccueilComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent}
];

@NgModule({

  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
