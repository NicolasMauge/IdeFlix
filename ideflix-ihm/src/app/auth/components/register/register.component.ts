import { Component } from '@angular/core';
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";
import {MenuService} from "../../../core/services/common/menu.service";
// import {MenuService} from "../../shared/services/menu.service";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent  {

  registerForm!: FormGroup;
  isFormSubmitted: boolean =  false;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private messageSvc: MessageService,
              private route: Router,
              private menuService: MenuService) {}

  ngOnInit() {
    // pas de MENU sur page de création de compte
    this.menuService.hideMenu = true;
    // construire mon instance loginForm
    this.registerForm = this.fb.group({
      nom:['', [Validators.required]],
      prenom:['', [Validators.required]],
      email:['', [Validators.required, Validators.email]],
    //   (?=.*[a-z]) : Au moins une lettre minuscule.
    // (?=.*[A-Z]) : Au moins une lettre majuscule.
    // (?=.*\d) : Au moins un chiffre.
    // (?=.*[@$!%*?&]) : Au moins un caractère spécial parmi ceux spécifiés (@, $, !, %, *, ?, &).
    // [A-Za-z\d@$!%*?&]+ : Correspond à la combinaison des lettres, chiffres et caractères spéciaux autorisés.
      motDePasse:['', [Validators.required,
                     Validators.minLength(8),
                     Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/)]],
      confirmMotDePasse:['', [Validators.required]]
    }, { validator: this.passwordMatchValidator } as AbstractControlOptions);
  }


  passwordMatchValidator(registerForm: FormGroup) {
    const motDePasse = registerForm.get('motDePasse')?.value;
    const confirmMotDePasse = registerForm.get('confirmMotDePasse')?.value;

    console.log('confirmMotDePasse: ' + confirmMotDePasse);

      if (  motDePasse !== confirmMotDePasse) {
      registerForm.get('confirmMotDePasse')?.setErrors({ mismatch: true });
    } else {
      registerForm.get('confirmMotDePasse')?.setErrors(null);
    }
    console.log(registerForm.get('confirmMotDePasse'));
  }

  register(event:Event) {
    event.preventDefault();

    // console.log(this.registerForm);
    console.log('isFormSubmitted: ' + this.isFormSubmitted);
    console.log('valid:' + this.registerForm.valid)

    this.isFormSubmitted = true;
    if (this.registerForm.valid) {
      // console.log('registerForm' + this.registerForm.value)
      this.authService.registerUser(this.registerForm.value)
        .subscribe({next : response => {
            console.log('reponse register' + response)
            this.messageSvc.show('Compte créé', 'success')
            this.route.navigate(['/login']); //TODO redirigé vers la page des préférences ou LOGIN
          }})
      // pour remettre le formulaire à blanc - nettoyer les champs
      this.isFormSubmitted= false;
      this.registerForm.reset();
    }
  }
  }
