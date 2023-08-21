import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/components/login/login.component";
import {RegisterComponent} from "./auth/components/register/register.component";
import {AccueilComponent} from "./auth/components/accueil/accueil.component";
import {MaListeComponent} from "./ma-liste-de-selection/components/ma-liste/ma-liste.component";
import {MediaListComponent} from "./recherche-media/components/media-list/media-list.component";
import {authGuard} from "./core/guards/auth.guard";
import {MesPreferencesComponent} from "./mes-preferences/components/mes-preferences/mes-preferences.component";
import {DetailMediaComponent} from "./selection-media/components/detail-media/detail-media.component";
import {AdminUtilisateursComponent} from "./admin/components/admin-utilisateurs/admin-utilisateurs.component";

// tableau des routes
const routes: Routes = [
  {path: '', component: AccueilComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'maListe', component: MaListeComponent, canActivate: [authGuard]},
  {path: 'search', component: MediaListComponent, canActivate: [authGuard]},
  {path: 'adminUtilisateurs', component: AdminUtilisateursComponent, canActivate: [authGuard]},
  {path: 'mesPreferences', component: MesPreferencesComponent, canActivate: [authGuard]},
  {path: 'selection/:movieId', component: DetailMediaComponent, canActivate: [authGuard]}
];

@NgModule({

  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
