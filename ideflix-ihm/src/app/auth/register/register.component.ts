import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth.service";
import {MessageService} from "../../shared/services/message.service";
import {Router} from "@angular/router";
import {MenuService} from "../../shared/services/menu.service";

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
    // pas de MENU sur page de login
    this.menuService.hideMenu = true;
    // construire mon instance loginForm
    this.registerForm = this.fb.group({
      // nom:['', [Validators.required]],
      // prenom:['', [Validators.required]],
      email:['', [Validators.required, Validators.email]],
    //   (?=.*[a-z]) : Au moins une lettre minuscule.
    // (?=.*[A-Z]) : Au moins une lettre majuscule.
    // (?=.*\d) : Au moins un chiffre.
    // (?=.*[@$!%*?&]) : Au moins un caractère spécial parmi ceux spécifiés (@, $, !, %, *, ?, &).
    // [A-Za-z\d@$!%*?&]+ : Correspond à la combinaison des lettres, chiffres et caractères spéciaux autorisés.
      password:['', [Validators.required,
                     Validators.minLength(8),
                     Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/)]],
      confirmPassword:['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }


  passwordMatchValidator(registerForm: FormGroup) {
    const password = registerForm.get('password')?.value;
    const confirmPassword = registerForm.get('confirmPassword')?.value;

    console.log('confirmPassword: ' + confirmPassword);

    if ( password && confirmPassword && password !== confirmPassword) {
      registerForm.get('confirmPassword')?.setErrors({ mismatch: true });
    } else {
      registerForm.get('confirmPassword')?.setErrors(null);
    }
    console.log(registerForm.get('confirmPassword'));
  }

  register(event:Event) {
    event.preventDefault();

    console.log(this.registerForm);
    console.log('isFormSubmitted: ' + this.isFormSubmitted);
    console.log('valid:' + this.registerForm.valid)

    this.isFormSubmitted = true;
    if (this.registerForm.valid) {
      console.log(this.registerForm.value)
      this.authService.registerUser(this.registerForm.value)
        .subscribe({next : response => {
          console.log(response)
          this.messageSvc.show('Compte créé', 'success')
           this.route.navigate(['']);
          }})
      // pour remettre le formulaire à blanc - nettoyer les champs
      this.isFormSubmitted= false;
      this.registerForm.reset();
    }
  }

  }
