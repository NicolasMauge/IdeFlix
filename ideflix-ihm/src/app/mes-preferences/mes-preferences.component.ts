import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-mes-preferences',
  templateUrl: './mes-preferences.component.html',
  styleUrls: ['./mes-preferences.component.css']
})
export class MesPreferencesComponent {

  genresList = [
    { name: "Action", value: 1, checked: false },
    { name: "Aventure", value: 2, checked: false },
    { name: "Science-Fiction", value: 3, checked: false },
    { name: "Drame", value: 3, checked: false },
    { name: "Comédie", value: 3, checked: false },
    { name: "Thriller", value: 3, checked: false },
    { name: "Familial", value: 3, checked: false }
  ];
  genres!: FormArray;

  preferencesForm!: FormGroup;
  isFormSubmitted: boolean =  false;
  sites = [];
  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    /*
      J'initialise le formulaire this.form qui est un formGroup
      qui a 2 propriétés
      - lastname : string
      - roles : formArray

      en valeur de  roles:FormArray
      -> j'instancie autant de formGroup que j'ai de roles dans rolesList
    */
    this.preferencesForm = this.fb.group({
      pseudo: ["", Validators.required],
      genres: this.fb.array(
        this.genresList.map(r =>
          this.fb.group({
            name: r.name,
            value: r.value,
            checked: this.fb.control(false)
          })
        ),
        [Validators.required]
      )
    });
  }

  // Permet de récupérer formData dans la vue qui est une instance de FormArray
  get formData() {
    console.log(this.preferencesForm.get("genres"));
    return <FormArray>this.preferencesForm.get("genres");
  }

  onSubmit(event:Event) {
    event.preventDefault();

    // console.log(this.registerForm);
    console.log('isFormSubmitted: ' + this.isFormSubmitted);
    console.log('valid:' + this.preferencesForm.valid)

    this.isFormSubmitted = true;
    if (this.preferencesForm.valid) {
      console.log('registerForm' + this.preferencesForm.value)
      // this.authService.registerUser(this.registerForm.value)
      //   .subscribe({next : response => {
      //       console.log('reponse register' + response)
      //       this.messageSvc.show('Compte créé', 'success')
      //       this.route.navigate(['/login']); //TODO redirigé vers la page des préférences ou LOGIN
      //     }})
      // pour remettre le formulaire à blanc - nettoyer les champs
      this.isFormSubmitted= false;
      this.preferencesForm.reset();
    }

  }

}
