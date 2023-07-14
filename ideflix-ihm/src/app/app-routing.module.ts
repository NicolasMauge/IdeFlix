import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {AccueilComponent} from "./accueil/accueil.component";
import {MaListeComponent} from "./MaListe/ma-liste/ma-liste.component";

// tableau des routes
const routes: Routes = [
  {path:'', component: AccueilComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'maListe',component:MaListeComponent}
];

@NgModule({

  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
