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
import {adminGuard} from "./core/guards/admin.guard";
import {LogoutComponent} from "./auth/components/logout/logout.component";
import {IndisponibleComponent} from "./core/components/indisponible/indisponible.component";

// tableau des routes
const routes: Routes = [
  {path: '', component: AccueilComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'maListe', component: MaListeComponent, canActivate: [authGuard]},
  {path: 'search', component: MediaListComponent, canActivate: [authGuard]},
  {path: 'adminUtilisateurs', component: AdminUtilisateursComponent, canActivate: [adminGuard]},
  {path: 'mesPreferences', component: MesPreferencesComponent, canActivate: [authGuard]},
  {path: 'selection/:typeMedia/:movieId', component: DetailMediaComponent, canActivate: [authGuard]},
  {path: 'indisponible', component: IndisponibleComponent, canActivate: [authGuard]},
  {path: '**', component: IndisponibleComponent}
];

@NgModule({

  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
