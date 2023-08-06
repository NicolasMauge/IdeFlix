import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../../auth/services/auth.service";

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService)
  const router : Router = inject(Router)
  if (authService.isAuthenticatedUser()) {
    return true;
  } else {
    // Rediriger l'utilisateur vers la page de connexion ou une page d'accès refusé
    return router.navigate(['']);
  }
};


