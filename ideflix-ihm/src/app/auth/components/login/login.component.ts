import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";
import {MenuService} from "../../../core/services/common/menu.service";
import {MesPreferencesService} from "../../../core/services/preferences/mes-preferences.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm!: FormGroup;
  isFormSubmitted: boolean = false;
  isConnexionEnCours: boolean = false;

  token!: string;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private messageSvc: MessageService,
              private route: Router,
              private menuService: MenuService,
              private mesPreferencesService: MesPreferencesService
  ) {
  }


  ngOnInit() {
    // pas de MENU sur page de login
    this.menuService.hideMenu = true;
    this.isConnexionEnCours = false;
    // construire mon instance loginForm
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      /* Au moins 6 caractères */
      motDePasse: ['', [Validators.required]]
    });
  }

  //methode d'envoi de l'evt
  onSubmitForm(event: Event) {
    event.preventDefault();

    this.isFormSubmitted = true;
    this.isConnexionEnCours = true;
    // vérifier à la soumission si le formulaire est valide-
    if (this.loginForm.valid) {
      //si valide, on execute la request de login à API Auth
      console.log("Connexion de ", this.loginForm.value.email)
      this.authService.login(this.loginForm.value)
        .subscribe({
            next: response => {
              localStorage.setItem('token', response.jwt)
              localStorage.setItem('email', response.email)
              // afficher un message de succès ('vous êtes connecté(e)!')
              this.messageSvc.show('Connexion réussie !', 'success')
              //rediriger l'utilisateur vers la page list
              this.route.navigate(['/maListe']);

              // pour rafraichir le pseudo dans la barre de navigation :
              this.mesPreferencesService.getPreferencesFromApi(response.email);
            },
          }
        )

      // pour remettre le formulaire à blanc - nettoyer les champs
      this.isFormSubmitted = false;
      this.loginForm.reset();
    }

  }

}
