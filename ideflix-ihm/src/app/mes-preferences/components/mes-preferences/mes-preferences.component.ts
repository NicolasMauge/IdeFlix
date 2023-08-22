import {Component} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MesPreferencesService} from "../../services/preferences/mes-preferences.service";
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";
import {GenreModel} from "../../../core/models/genre.model";
import {Subscription} from "rxjs";
import {GenreService} from "../../../core/services/genres/genre.service";
import {PreferenceModel} from "../../models/preference.model";
import {AddCheckedPropertyPipe} from "../../../shared/pipes/add-checked-property.pipe";

@Component({
  selector: 'app-mes-preferences',
  templateUrl: './mes-preferences.component.html',
  styleUrls: ['./mes-preferences.component.css'],
  providers: [AddCheckedPropertyPipe]
})
export class MesPreferencesComponent {

  // propriétés pour chargement de tous les genres existants en Bdd
  genreList!: FormArray;
  allGenres: GenreModel[] = [];
  subGenre!: Subscription;

  // propriétés pour chargement des préférences de l'utilisateur
  subPreferences!: Subscription;
  preferences!: PreferenceModel;
  genresListChecked: GenreModel[] = [];

  // propriétés du formulaire
  preferencesForm!: FormGroup;
  isFormSubmitted: boolean = false;

  constructor(private fb: FormBuilder,
              private mesPreferencesService: MesPreferencesService,
              private messageSvc: MessageService,
              private route: Router,
              private genreService: GenreService,
              private addCheckedPropertyPipe: AddCheckedPropertyPipe) {
  }

  ngOnInit() {

    // abonnement à la source service.genres$ via un subscribe pour récupérer la liste de tous les genres possibles
    this.subGenre = this.genreService.genres$.subscribe((data: GenreModel[]) => {
      this.allGenres = data;
    });


    // requête pour récupérer les préférences de l'utilisateur et charger la page
    const email = localStorage.getItem('email');
    if (email !== null) {
      this.mesPreferencesService.getPreferencesFromApi(email);
      // abonnement à la source service.preferences$ via un subscribe
      this.subPreferences = this.mesPreferencesService.preferences$.subscribe((data: PreferenceModel) => {
          this.preferences = data;
          //  ajouter un checked à true sur les genres des préférences de l'utilisateur
          this.genresListChecked = this.getFormattedGenres(this.allGenres, this.preferences.genreList);
          this.initFormBuilder();
        }
      );

    } else {
      console.log('email non présent dans le localstorage');
      this.messageSvc.show('erreur de connexion - veuillez vous reconnecter', 'error')
      this.route.navigate(['/login']);
    }

  }

  initFormBuilder() {

    // initialisation du formulaire this.preferencesForm avec pré affichage des préférences utilisateurs enregsitrées en BDD
    this.preferencesForm = this.fb.group({
      email: localStorage.getItem('email'),
      pseudo: [this.preferences?.pseudo, Validators.required],
      genreList: this.fb.array(
        this.genresListChecked.map(genre =>
          this.fb.group({
            id: genre.id,
            idTmdb: genre.idTmdb,
            name: genre.nomGenre,
            checked: this.fb.control(genre.checked)
          }))
      )
    });
  }

  // Permet de récupérer formData dans la vue qui est une instance de FormArray
  get formData() {
    return <FormArray>this.preferencesForm.get("genreList");
  }

  onSubmit(event: Event) {
    event.preventDefault();

    this.isFormSubmitted = true;
    if (this.preferencesForm.valid) {

      // formattage des préférences pour appel à l'API (email + pseudo + genreList)
      const selectedGenres = this.getGenresChecked();
      const data = {
        email: this.preferencesForm.get('email')?.value,
        pseudo: this.preferencesForm.get('pseudo')?.value,
        genreList: selectedGenres
      };


      // enregistrement des préférences créées ou modifiées en BDD
      this.mesPreferencesService.registerPreferences(data)
        .subscribe({
          next: () => {
            this.messageSvc.show('préférences enregistrées', 'success')
            this.route.navigate(['/maListe']);
          }
        })
      this.isFormSubmitted = false;
    }
  }

  // fonction pour matcher entre la liste des préférences choisies par l'utilisateur et la liste de tous les genres
  getFormattedGenres(allGenre: GenreModel[], preferencesGenre: GenreModel[]): GenreModel[] {
    return this.addCheckedPropertyPipe.transform(allGenre, preferencesGenre);
  }

  // fonction pour formatter GenreList (uniquement les genres cochés )
  getGenresChecked(): GenreModel[] {
    const selectedGenres: GenreModel[] = [];
    const formArray = this.formData;
    for (let i = 0; i < formArray.length; i++) {
      const genreControl = formArray.at(i);
      if (genreControl.get('checked')?.value) {
        selectedGenres.push(genreControl.value);
      }
    }
    return selectedGenres;
  }

  ngOnDestroy() {
    this.subGenre.unsubscribe
    this.subPreferences.unsubscribe()
  }

}
