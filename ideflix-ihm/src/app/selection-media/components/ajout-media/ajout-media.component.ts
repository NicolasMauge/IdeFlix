import {Component, Input} from '@angular/core';
import {Status} from "../../../core/models/status";
import {EtiquettesService} from "../../shared/services/etiquettes.service";
import {EtiquetteModel} from "../../shared/model/EtiquetteModel";
import {MediaSelectionneToAppService} from "../../shared/services/media-selectionne-to-app.service";
import {MediaSelectionneDtoModel} from "../../shared/model/MediaSelectionneDtoModel";
import {GenreAppModel} from "../../shared/model/GenreAppModel";
import {MediaToAppService} from "../../shared/services/media-to-app.service";
import {GenreToAppService} from "../../shared/services/genre-to-app.service";
import {MediaMaListeModel} from "../../../ma-liste-de-selection/models/media-ma-liste.model";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {DialogEtiquettesComponent} from "../dialog-etiquettes/dialog-etiquettes.component";
import {MapIhmService} from "../../../shared/services/map-ihm-service";

// fonction pour la correspondance entre les status provenant du backend et les status affichés sur l'ihm
function mapIhmStatusToBackendStatus(ihmStatus: string): string | undefined {
  // TODO : à supprimer quand fonction disponible
  switch (ihmStatus) {
    case Status.Completed:
      return "VU";
    case Status.ToSee:
      return "A_VOIR";
    case Status.InProgress:
      return "EN_COURS";
    case Status.Dropped:
      return "ABANDONNE";
    default:
      return undefined;
  }
}

export interface DialogData {
  ajoutEtiquette: string;
}

@Component({
  selector: 'app-ajout-media',
  templateUrl: './ajout-media.component.html',
  styleUrls: ['./ajout-media.component.css']
})
export class AjoutMediaComponent {
  protected readonly Status = Status;
  statusEnum = Status;

  etiquettes$!: Observable<EtiquetteModel[]>;
  email: string|null = "";

  buttonAdd: boolean = true;
  buttonModify: boolean = false;
  buttonDelete : boolean = false;

  @Input() media!: MediaDatabaseModel;
  @Input() typeMedia!:string;

  userForm!: FormGroup;

  ajoutEtiquette: string | undefined;

  constructor(private etiquetteService:EtiquettesService,
              private mediaAppService:MediaSelectionneToAppService,
              private mediaService: MediaToAppService,
              private genreService: GenreToAppService,
              private formBuilder: FormBuilder,
              private route: Router,
              public dialog: MatDialog,
              private mapStatus: MapIhmService) {
  }

  ngOnInit() {
    this.email = localStorage.getItem('email');

    if (this.email !== null) {
      this.loadEtiquettes();
      this.loadMediaSelectionne();
    }
    else {
      console.log('email non présent dans le localstorage');
      //this.messageSvc.show('erreur de conexion - veuillez vous reconnecter', 'error')
      //this.route.navigate(['/login']);
    }
  }

  loadMediaSelectionne() {
    this.mediaAppService.trouveMediaSelectionnePourEmailEtIdTmdb(this.email!, this.media.idDataBase.toString());
    this.mediaAppService.mediaSelectionne$.subscribe((data:MediaSelectionneDtoModel[])=> {
      if(data.length>0) {
        this.buttonAdd = false;
        this.buttonModify = true;
        this.buttonDelete = true;

        let defaultStatus: Status;
        switch (data[0].statutMedia) {
          case "ABANDONNE":
            defaultStatus = Status.Dropped;
            break;
          case "A_VOIR":
            defaultStatus = Status.ToSee;
            break;
          case "EN_COURS":
            defaultStatus = Status.InProgress;
            break;
          case "VU":
            defaultStatus = Status.Completed;
            break;
          default:
            defaultStatus = Status.ToSee;
        }
        //let defaultStatus: Status|undefined = this.mapStatus.mapBackendStatusToIhmStatus(data[0].statutMedia);

        //this.userForm.get('status')?.setValue(defaultStatus);
        // this.userForm.get('etiquettes')?.setValue(data[0].etiquetteList.map((etiquette)=>new EtiquetteModel(etiquette)));

        let etiquettesExistantes: EtiquetteModel[] = data[0].etiquetteList.map((etiquette)=>new EtiquetteModel(etiquette));
        console.log(data[0].etiquetteList);
        console.log(etiquettesExistantes);

        this.userForm = this.formBuilder.group({
          status: [defaultStatus, [ Validators.required ] ],
          etiquettes:  []
        });

        this.etiquettes$.subscribe((etiquettes) => {
          let etiquettesChecked: EtiquetteModel[] = [];
          data[0].etiquetteList.map((etiquette) => {
            console.log(etiquettes);
            let etiquetteFound: EtiquetteModel|undefined = etiquettes.find(tag => tag.id == etiquette.id);

            console.log("Etiquette found");
            console.log(etiquetteFound);
            if(etiquetteFound!=undefined) {
              etiquettesChecked.push(etiquetteFound);
            }
          });

          this.userForm = this.formBuilder.group({
            status: [Status.ToSee, [ Validators.required ] ],
            etiquettes:  [etiquettesChecked]
          });
        })


        console.log(this.userForm.value);
      }
      else {
        this.buttonAdd = true;
        this.buttonModify = false;
        this.buttonDelete = false;

        this.userForm = this.formBuilder.group({
          status: [Status.ToSee, [ Validators.required ] ],
          etiquettes:  [[]]
        });
      }
    });
  }

  loadEtiquettes() {
    this.etiquetteService.loadEtiquettes(this.email!);
    this.etiquettes$ = this.etiquetteService.etiquettes$;
  }

  get etiquette$(): Observable<EtiquetteModel[]> {
    return this.etiquetteService.etiquettes$;
  }

  OnSubmitAdd() {
    //event.preventDefault();

    if (this.userForm.value.status != '') {
      //sauvegarde de la partie genre
      this.genreService.saveToApp(this.media.genres.map((genre:any) => {return new GenreAppModel(genre);}))
        .subscribe(() => this.mediaService.saveToApp(this.media, this.typeMedia)
            .subscribe(() => {
              let statusApp = mapIhmStatusToBackendStatus(this.userForm.value.status); // TODO :

              let mediaSelectionneObject = {
                typeMedia: this.typeMedia,
                avisPouce: false,
                dateSelection: new Date(),
                etiquetteList: this.userForm.value.etiquettes,
                statutMedia: statusApp,
                mediaIdTmdb: this.media.idDataBase,
                email: this.email,
                dateModification: new Date(),
                numeroSaison: 0,
                idTmdbSaison: 0,
                numeroEpisode: 0,
                idTmdbEpisode: 0
              }

              let mediaSelectionne = new MediaSelectionneDtoModel(mediaSelectionneObject);

              this.mediaAppService.saveToApp(mediaSelectionne);
            })
        );
    }
    this.route.navigate(['/maListe']);
  }

  OnSubmitDelete() {
    this.mediaAppService.deleteFromApp(this.email!, this.media.idDataBase.toString());
    this.route.navigate(['/maListe']);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogEtiquettesComponent, {
      data: {name: this.ajoutEtiquette},
    });

    dialogRef.afterClosed().subscribe(result => {
      // fermeture de la page dialog
      this.ajoutEtiquette = result; // TODO : supprimer quand fonction dispo
      this.saveEtiquette(result);
      this.loadEtiquettes();
    });
  }

  saveEtiquette(nouvelleEtiquette: string) {
    console.log(nouvelleEtiquette);
    this.etiquetteService.saveToApp(new EtiquetteModel({nomTag: nouvelleEtiquette}), this.email!);
  }
}
