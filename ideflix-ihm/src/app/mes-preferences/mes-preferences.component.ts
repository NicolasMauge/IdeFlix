import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-mes-preferences',
  templateUrl: './mes-preferences.component.html',
  styleUrls: ['./mes-preferences.component.css']
})
export class MesPreferencesComponent {

  genresList = [
    { id: 1, idTmdb: "28", name: "Action", checked: false },
    { id: 1, idTmdb: "12", name: "Aventure", checked: false },
    { id: 1, idTmdb: "16", name: "Animation", checked: false },
    { id: 1, idTmdb: "35", name: "Comédie", checked: false },
    { id: 1, idTmdb: "80", name: "Crime", checked: false },
    { id: 1, idTmdb: "99", name: "Documentaire", checked: false },
    { id: 1, idTmdb: "18", name: "Drame", checked: false },
    { id: 1, idTmdb: "10751", name: "Familial", checked: false },
    { id: 1, idTmdb: "14", name: "Fantastique", checked: false },
    { id: 1, idTmdb: "36", name: "Histoire", checked: false },
    { id: 1, idTmdb: "27", name: "Horreur", checked: false },
    { id: 1, idTmdb: "10402", name: "Musique", checked: false },
    { id: 1, idTmdb: "9648", name: "Mystère", checked: false },
    { id: 1, idTmdb: "10749", name: "Romance", checked: false },
    { id: 1, idTmdb: "878", name: "Science-Fiction", checked: false },
    { id: 1, idTmdb: "10770", name: "Téléfilm", checked: false },
    { id: 1, idTmdb: "53", name: "Thriller", checked: false },
    { id: 1, idTmdb: "10752", name: "Guerre", checked: false },
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
        this.genresList.map(genre =>
          this.fb.group({
            name: genre.name,
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
