import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth.service";
import {MessageService} from "../../shared/services/message.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm!: FormGroup;
  isFormSubmitted: boolean =  false;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private messageSvc: MessageService,
              private route: Router) {}

  ngOnInit() {
    // construire mon instance loginForm
    this.registerForm = this.fb.group({
      // nom:['', [Validators.required]],
      // prenom:['', [Validators.required]],
      email:['', [Validators.required, Validators.email]],
      /* Au moins 8 caractères
         Au moins une lettre majuscule
         Au moins une lettre minuscule
         Au moins un chiffre*/
      password:['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/)]]
    });
  }

  register(event:Event) {
    event.preventDefault();
    console.log(this.registerForm);
    this.isFormSubmitted = true;
    if ('registerForm.value:' + this.registerForm.valid) {
      console.log(this.registerForm.value)
      this.authService.registerUser(this.registerForm.value)
        .subscribe({next : response => {
          console.log(response)
          this.messageSvc.show('compte crée', 'success')
           this.route.navigate(['']);
          }})
    }
  }

  // onSubmitForm(event:Event) {
  //   event.preventDefault();
  //   console.log(this.loginForm);
  //   this.isFormSubmitted = true;
  //   // vérifier à la soumission si le formulaire est valide-
  //   if (this.loginForm.valid) {
  //     //si valide, on execute la request de login à API Auth
  //     console.log(this.loginForm.value)
  //     this.authService.login(this.loginForm.value)
  //       .subscribe ( {
  //           next: response => {
  //             localStorage.setItem('token', response.token);
  //             localStorage.setItem('userId', response.user.id)
  //             localStorage.setItem('userName', response.user.email)
  //             // afficher un message de succès ('vous êtes connecté(e)!')
  //             this.messageSvc.show('Connexion réussie !', 'success')
  //             //rediriger l'utilisateur vers la page list
  //             this.route.navigate(['']);
  //           },
  //           // error: error => {
  //           //   console.log('Erreur lors de la requête :', error);
  //           //   if (error instanceof HttpErrorResponse && error.status === 400) {
  //           //      console.log(error.error)}
  //         }
  //       )
  //
  //     // pour remettre le formulaire à blanc - nettoyer les champs
  //     this.isFormSubmitted= false;
  //     this.loginForm.reset();
  //     // si non valide, afficher les erreurs
  //   } else {
  //
  //   }


  }
