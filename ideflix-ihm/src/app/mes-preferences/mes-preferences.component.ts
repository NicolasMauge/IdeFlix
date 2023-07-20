import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MesPreferencesService} from "../shared/services/mes-preferences.service";
import {MessageService} from "../shared/services/message.service";
import {Router} from "@angular/router";
import {GenreModel} from "../shared/models/genre.model";
import {Subscription} from "rxjs";
import {GenreService} from "../shared/services/genre.service";
import {PreferenceModel} from "../shared/models/preference.model";
import {AddCheckedPropertyPipe} from "../shared/pipes/add-checked-property.pipe";

@Component({
  selector: 'app-mes-preferences',
  templateUrl: './mes-preferences.component.html',
  styleUrls: ['./mes-preferences.component.css'],
  providers: [AddCheckedPropertyPipe]
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

  // propriétés pour chargement de tous les genres existants en Bdd
  genreList!: FormArray;
  genresList : GenreModel[] = [];
  genresListChecked: GenreModel[] = [];
  subGenre!: Subscription;

  // propriétés pour chargement des préférences de l'utilisateur
  subPreferences!: Subscription;
  preferences!: PreferenceModel;

  // propriétés du formulaire
  preferencesForm!: FormGroup;
  isFormSubmitted: boolean =  false;
  // loading : boolean = true;

  constructor(private fb: FormBuilder,
              private mesPreferencesService : MesPreferencesService,
              private messageSvc: MessageService,
              private route: Router,
              private genreService: GenreService,
              private addCheckedPropertyPipe : AddCheckedPropertyPipe) {}

  ngOnInit() {

    // abonnement à la source service.genres$ via un subscribe pour récupérer la liste de tous les genres possibles
    this.subGenre = this.genreService.genres$.subscribe( (data: GenreModel[]) => this.genresList = data);
    console.log('get genres:' + this.genresList);

    // requête pour récupérer les préférences de l'utilisateur et charger la page
    const email = localStorage.getItem('email');
    if (email !== null) {
      this.mesPreferencesService.getPreferencesFromApi(email);
      // abonnement à la source service.preferences$ via un subscribe
      this.subPreferences = this.mesPreferencesService.preferences$.subscribe( (data: PreferenceModel) => {
        this.preferences = data;
        console.log('getPreferenceFromAPI', this.preferences);
        this.initFormBuilder();
      }
      );

    } else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);
    }

  }

  initFormBuilder(){
    /*
  J'initialise le formulaire this.preferencesForm qui est un formGroup
  qui a 2 propriétés
  - pseudo : string
  - genres : formArray

  en valeur de  genres:FormArray
  -> j'instancie autant de formGroup que j'ai de genres dans genresList
*/
    //  ajouter un checked à true sur les genres des préférences de l'utilisateur
    this.genresListChecked = this.getFormattedGenres(this.genresList, this.preferences.genreList)

    this.preferencesForm = this.fb.group({
      email: localStorage.getItem('email'),
      pseudo: [this.preferences?.pseudo,Validators.required],
      genreList: this.fb.array(
        this.genresListChecked.map(genre =>
          this.fb.group({
            id: genre.idGenre,
            idTmdb: genre.idTmdbGenre,
            name: genre.nomGenre,
            checked: this.fb.control(genre.checked)
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

  getFormattedGenres(allGenre : GenreModel[], preferencesGenre: GenreModel[]): GenreModel[] {
    // return this.addCheckedPropertyPipe.transform(this.genresList, this.preferences?.genreList);
  return this.addCheckedPropertyPipe.transform(allGenre, preferencesGenre);
  }

}
