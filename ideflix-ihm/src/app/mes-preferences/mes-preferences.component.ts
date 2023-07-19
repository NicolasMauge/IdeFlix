import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MesPreferencesService} from "../shared/services/mes-preferences.service";
import {MessageService} from "../shared/services/message.service";
import {Router} from "@angular/router";
import {GenreModel} from "../shared/models/genre.model";
import {Subscription} from "rxjs";
import {GenreService} from "../shared/services/genre.service";

@Component({
  selector: 'app-mes-preferences',
  templateUrl: './mes-preferences.component.html',
  styleUrls: ['./mes-preferences.component.css']
})
export class MesPreferencesComponent {

  // genresList = [
  //   { id: 1, idTmdb: "28", name: "Action", checked: false },
  //   { id: 1, idTmdb: "12", name: "Aventure", checked: false },
  //   { id: 1, idTmdb: "16", name: "Animation", checked: false },
  //   { id: 1, idTmdb: "35", name: "Comédie", checked: false },
  //   { id: 1, idTmdb: "80", name: "Crime", checked: false },
  //   { id: 1, idTmdb: "99", name: "Documentaire", checked: false },
  //   { id: 1, idTmdb: "18", name: "Drame", checked: false },
  //   { id: 1, idTmdb: "10751", name: "Familial", checked: false },
  //   { id: 1, idTmdb: "14", name: "Fantastique", checked: false },
  //   { id: 1, idTmdb: "36", name: "Histoire", checked: false },
  //   { id: 1, idTmdb: "27", name: "Horreur", checked: false },
  //   { id: 1, idTmdb: "10402", name: "Musique", checked: false },
  //   { id: 1, idTmdb: "9648", name: "Mystère", checked: false },
  //   { id: 1, idTmdb: "10749", name: "Romance", checked: false },
  //   { id: 1, idTmdb: "878", name: "Science-Fiction", checked: false },
  //   { id: 1, idTmdb: "10770", name: "Téléfilm", checked: false },
  //   { id: 1, idTmdb: "53", name: "Thriller", checked: false },
  //   { id: 1, idTmdb: "10752", name: "Guerre", checked: false },
  // ];

  genreList!: FormArray;

  genresList : GenreModel[] = [];
  sub!: Subscription;

  preferencesForm!: FormGroup;
  isFormSubmitted: boolean =  false;
  sites = [];
  constructor(private fb: FormBuilder,
              private mesPreferencesService : MesPreferencesService,
              private messageSvc: MessageService,
              private route: Router,
              private genreService: GenreService) {}

  ngOnInit() {
    /* appel à l'API des préférences de l'utilisateur pour rechercher ses préférences
     */


    /*
      J'initialise le formulaire this.preferencesForm qui est un formGroup
      qui a 2 propriétés
      - pseudo : string
      - genres : formArray

      en valeur de  genres:FormArray
      -> j'instancie autant de formGroup que j'ai de genres dans genresList
    */
    // abonnement à la source service.genres$ via un subscribe
    this.sub = this.genreService.genres$.subscribe( (data: GenreModel[]) => this.genresList = data);

    this.preferencesForm = this.fb.group({
      pseudo: ["test", Validators.required],
      // genreList: this.fb.array(
      //   this.genresList.map(genre =>
      //     this.fb.group({
      //       name: genre.name,
      //       checked: this.fb.control(false)
      //     })
      //   ),
      //   [Validators.required]
      // )
      genreList: this.fb.array(
        this.genresList.map(genre =>
        this.fb.group({
          id: genre.idGenre,
          idTmdb: genre.idTmdbGenre,
          name: genre.nomGenre,
          checked: this.fb.control(false)
        }))
      )
    });
  }

  // Permet de récupérer formData dans la vue qui est une instance de FormArray
  get formData() {
    console.log(this.preferencesForm.get("genreList"));
    return <FormArray>this.preferencesForm.get("genreList");
  }

  onSubmit(event:Event) {
    event.preventDefault();

    // console.log(this.preferencesForm);
    console.log('isFormSubmitted: ' + this.isFormSubmitted);
    console.log('valid:' + this.preferencesForm.valid)

    this.isFormSubmitted = true;
    if (this.preferencesForm.valid) {
      console.log('preferencesForm' + this.preferencesForm.value)
      this.mesPreferencesService.registerPreferences(this.preferencesForm.value)
        .subscribe({next : response => {
            console.log('reponse register' + response)
            this.messageSvc.show('préférences enregistrées', 'success')
            this.route.navigate(['/MaListe']);
          }})
      // pour remettre le formulaire à blanc - nettoyer les champs
      this.isFormSubmitted= false;
      this.preferencesForm.reset();
    }
  }
}
