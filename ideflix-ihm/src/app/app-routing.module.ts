import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {AccueilComponent} from "./accueil/accueil.component";
import {MaListeComponent} from "./MaListe/ma-liste/ma-liste.component";
import {MediaListComponent} from "./Rechercher/media-list/media-list.component";
import {authGuard} from "./shared/guards/auth.guard";
import {MesPreferencesComponent} from "./mes-preferences/mes-preferences.component";
import {DetailMediaComponent} from "./selection-media/detail-media/detail-media.component";

// tableau des routes
const routes: Routes = [
  {path:'', component: AccueilComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'maListe',component:MaListeComponent, canActivate : [authGuard]},
  {path:'search', component:MediaListComponent, canActivate : [authGuard]},
  {path:'mesPreferences', component:MesPreferencesComponent, canActivate : [authGuard]},
  {path:'selection', component: DetailMediaComponent, canActivate: [authGuard] }
];

@NgModule({

  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
