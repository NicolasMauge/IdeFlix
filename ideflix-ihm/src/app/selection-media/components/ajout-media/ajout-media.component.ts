import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Status} from "../../../core/models/status";
import {EtiquetteModel} from "../../shared/model/Etiquette.model";
import {MediaSelectionneToAppService} from "../../shared/services/media-selectionne-to-app.service";
import {MediaSelectionneDtoModel} from "../../shared/model/MediaSelectionneDto.model";
import {GenreAppModel} from "../../shared/model/GenreApp.model";
import {MediaToAppService} from "../../shared/services/media-to-app.service";
import {GenreToAppService} from "../../shared/services/genre-to-app.service";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {concatMap, Observable} from "rxjs";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {DialogEtiquettesComponent} from "../dialog-etiquettes/dialog-etiquettes.component";
import {MapIhmService} from "../../../shared/services/map-ihm-service";
import {SerieCurrentSaisonEpisode} from "../choix-saison-episode/choix-saison-episode.component";
import {EtiquetteCoreService} from "../../../core/services/etiquettes/etiquette-core.service";
import {MediaSelectionneCompletDtoModel} from "../../shared/model/MediaSelectionneCompletDto.model";


export interface DialogData {
  ajoutEtiquette: string;
}

export interface SaisonEpisode {
  saison: number,
  episode: number
}

@Component({
  selector: 'app-ajout-media',
  templateUrl: './ajout-media.component.html',
  styleUrls: ['./ajout-media.component.css']
})
export class AjoutMediaComponent {
  statusEnum = Status;

  etiquettes$!: Observable<EtiquetteModel[]>;
  mediaSelectionne$!: Observable<MediaSelectionneCompletDtoModel[]>

  email: string|null = "";

  buttonAdd: boolean = true;
  buttonModify: boolean = false;
  buttonDelete : boolean = false;

  @Input() media!: MediaDatabaseModel;
  @Input() typeMedia!:string;
  @Output() emitterParentNumeroSaison = new EventEmitter<number>();

  userForm!: FormGroup;

  ajoutEtiquette: string | undefined;

  constructor(private etiquetteService:EtiquetteCoreService,
              private mediaSelectionneToAppService:MediaSelectionneToAppService,
              private mediaService: MediaToAppService,
              private genreService: GenreToAppService,
              private formBuilder: FormBuilder,
              private route: Router,
              public dialog: MatDialog,
              private mapStatus: MapIhmService) {
  }

  ngOnInit() {
    this.email = localStorage.getItem('email');

    this.userForm = this.formBuilder.group({
      status: [Status.ToSee, [ Validators.required ] ],
      etiquettes:  [[]],
      avancement: [null]
    });

    if (this.email !== null) {
      this.loadEtiquettes();
      this.loadMediaSelectionne();

      this.buttonAdd = true;
      this.buttonModify = false;
      this.buttonDelete = false;

      this.userForm.get('status')?.setValue(Status.ToSee);
      this.userForm.get('etiquettes')?.setValue([]);

      this.mediaSelectionne$.subscribe((data:MediaSelectionneCompletDtoModel[]) => {
        if(data.length > 0) {
          this.initializeDefaultValues(data[0]);
        }
      })
    }
    else {
      console.log('email non présent dans le localstorage');
      this.route.navigate(['/login']);
    }
  }

  initializeDefaultValues(mediaSelectionne: MediaSelectionneCompletDtoModel) {
    this.buttonAdd = false;
    this.buttonModify = true;
    this.buttonDelete = true;

    let defaultStatus: Status|undefined = this.mapStatus.mapBackendStatusToIhmStatus(mediaSelectionne.statutMedia);
    this.userForm.get('status')?.setValue(defaultStatus);

    this.etiquettes$.subscribe((etiquettes) => {
      let etiquettesChecked: EtiquetteModel[] = [];
      mediaSelectionne.etiquetteList.map((etiquette) => {
        let etiquetteFound: EtiquetteModel|undefined = etiquettes.find(tag => tag.id == etiquette.id);

        if(etiquetteFound!=undefined) {
          etiquettesChecked.push(etiquetteFound);
        }
      });

      this.userForm.get('etiquettes')?.setValue(etiquettesChecked);
    });
  }

  loadMediaSelectionne() {
    this.mediaSelectionne$ = this.mediaSelectionneToAppService.trouveMediaSelectionnePourEmailEtIdTmdb(this.email!, this.media.idDataBase.toString());
  }

  loadEtiquettes() {
    this.etiquetteService.loadEtiquettes(this.email!);
    this.etiquettes$ = this.etiquetteService.etiquettes$;
  }

  get etiquette$(): Observable<EtiquetteModel[]> {
    return this.etiquettes$;
  }


  OnSubmitAdd() {
    //event.preventDefault();

    if (this.userForm.value.status != '') {
      //sauvegarde de la partie genre
      this.genreService.saveToApp(this.media.genres.map((genre: any) => new GenreAppModel(genre)))
        .pipe(
          //concatMap attend que chaque opération précédente soit terminée avant de déclencher la suivante
          concatMap(() => this.mediaService.saveToApp(this.media, this.typeMedia)),
          concatMap(() => {
            const statusApp = this.mapStatus.mapIhmStatusToBackendStatus(this.userForm.value.status);

              const mediaSelectionneObject = {
                typeMedia: this.typeMedia,
                avisPouce: false,
                dateSelection: new Date(),
                etiquetteList: this.userForm.value.etiquettes,
                statutMedia: statusApp,
                mediaIdTmdb: this.media.idDataBase,
                email: this.email,
                dateModification: new Date(),
                numeroSaison: this.typeMedia == "SERIE" ? this.userForm.value.avancement.saison : 0,
                idTmdbSaison: this.typeMedia == "SERIE" ? this.userForm.value.avancement.idSaisonTmdb : "",
                numeroEpisode: this.typeMedia == "SERIE" ? this.userForm.value.avancement.episode : 0,
                idTmdbEpisode: ""
              };

            const mediaSelectionne = new MediaSelectionneDtoModel(mediaSelectionneObject);

            return this.mediaSelectionneToAppService.saveToApp(mediaSelectionne); // Appel sans retour d'Observable

            //return of(null); // Retourne un observable qui émet une valeur nulle
          })
          )
          .subscribe(() => {
            // Toutes les opérations asynchrones sont terminées, navigation vers MaListe
            this.route.navigate(['/maListe']);
          });
      }
    }


  OnSubmitDelete() {
    this.mediaSelectionneToAppService.deleteFromApp(this.email!, this.media.idDataBase.toString()).subscribe(()=>{
      this.route.navigate(['/maListe']);
    } );
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogEtiquettesComponent, {
      data: {name: this.ajoutEtiquette},
    });

    dialogRef.afterClosed().subscribe(nouvelleEtiquette => {
      if(nouvelleEtiquette!=undefined) {
        // fermeture de la page dialog
        this.etiquetteService.saveToApp(new EtiquetteModel({nomTag: nouvelleEtiquette}), this.email!)
            .subscribe(()=>
                this.etiquetteService.loadEtiquettes(this.email!));
      }
    });
  }

  setAvancement(saisonEpisodeCourant: SerieCurrentSaisonEpisode) {
    this.userForm.get('avancement')?.setValue(saisonEpisodeCourant);

    this.emitterParentNumeroSaison.emit(saisonEpisodeCourant.saison);
  }
}
