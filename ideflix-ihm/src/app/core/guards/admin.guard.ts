import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../../auth/services/auth.service";

export const adminGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService)
  const router: Router = inject(Router)

  if (authService.isAdmin()) {
    return true;
  } else {
    // Rediriger l'utilisateur vers la page de connexion ou une page d'accès refusé
    router.navigate(['']);
    return false;


  }
}
