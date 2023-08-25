import {Component, EventEmitter, Output} from '@angular/core';
import {Status} from "../../../core/models/status";
import {EtiquetteCoreService} from "../../../core/services/etiquettes/etiquette-core.service";
import {EtiquetteModel} from "../../../core/models/etiquette.model";
import {GenreModel} from "../../../core/models/genre.model";
import {GenreUtilisateurService} from "../../../core/services/genres/genre-utilisateur.service";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-ma-liste-filtre',
  templateUrl: './ma-liste-filtre.component.html',
  styleUrls: ['./ma-liste-filtre.component.css']
})
export class MaListeFiltreComponent {

  myFilter: any = {status: '', genre: '', etiquette: ''};
  statusEnum = Status;
  genres: GenreModel[] = [];
  etiquettes: EtiquetteModel[] = [];
  subEtiquette!: Subscription;
  subGenre!: Subscription;

  constructor(private etiquetteService: EtiquetteCoreService,
              private genreUtilisateurService: GenreUtilisateurService) {}

  ngOnInit() {
    // Chargez les étiquettes et les genres de l'utilisateur en utilisant les services
    const email = localStorage.getItem('email');
    if (email !== null) {
    this.etiquetteService.loadEtiquettes(email);

    // Souscrivez aux changements de l'observable etiquettes$ pour mettre à jour la liste d'étiquettes dans le composant
    this.subEtiquette = this.etiquetteService.etiquettes$.subscribe((etiquettes: EtiquetteModel[]) => {
      this.etiquettes = etiquettes;
    });

    this.genreUtilisateurService.loadGenresByUtilisateur(email);
      // Souscrivez aux changements de l'observable genreUtilisateur$ pour mettre à jour la liste des genres dans le composant
      this.subGenre = this.genreUtilisateurService.genresUtilisateur$.subscribe((genres: GenreModel[]) => {
        this.genres = genres;
    });
  }}

  ngOnDestroy() {
    this.subEtiquette.unsubscribe();
    this.subGenre.unsubscribe();

  }

  @Output() filterEvent = new EventEmitter<{status : string, genre : string, etiquette : string}>();
  onSubmitFilter(statusInput: string, genreInput: string, etiquetteInput: string) {
    this.filterEvent.emit({status: statusInput, genre: genreInput, etiquette: etiquetteInput});
  }

}
