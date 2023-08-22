import {Component, Input} from '@angular/core';
import {Status} from "../../../core/models/status";
import {EtiquettesService} from "../../shared/services/etiquettes.service";
import {EtiquetteModel} from "../../shared/model/EtiquetteModel";
import {MediaSelectionneToAppService} from "../../shared/services/media-selectionne-to-app.service";
import {MediaSelectionneModel} from "../../shared/model/MediaSelectionneModel";
import {GenreAppModel} from "../../shared/model/GenreAppModel";
import {MediaToAppService} from "../../shared/services/media-to-app.service";
import {GenreToAppService} from "../../shared/services/genre-to-app.service";
import {MediaMaListeModel} from "../../../ma-liste-de-selection/models/media-ma-liste.model";
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";

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

@Component({
  selector: 'app-ajout-media',
  templateUrl: './ajout-media.component.html',
  styleUrls: ['./ajout-media.component.css']
})
export class AjoutMediaComponent {
  nouveauMedia: any = {status: '', listTag: []};
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

  constructor(private etiquetteService:EtiquettesService,
              private mediaAppService:MediaSelectionneToAppService,
              private mediaService: MediaToAppService,
              private genreService: GenreToAppService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.email = localStorage.getItem('email');

    if (this.email !== null) {
      this.userForm = this.formBuilder.group({
        status: [null, [ Validators.required ] ],
        etiquettes:  [null]
      });

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
    this.mediaAppService.mediaSelectionne$.subscribe((data:MediaMaListeModel[])=> {
      if(data.length>0) {
        this.buttonAdd = false;
        this.buttonModify = true;
        this.buttonDelete = true;
      } else {
        this.buttonAdd = true;
        this.buttonModify = false;
        this.buttonDelete = false;
      }});
  }

  loadEtiquettes() {
    this.etiquetteService.loadEtiquettes(this.email!);
    this.etiquettes$ = this.etiquetteService.etiquettes$;
    //this.etiquetteService.etiquettes$.subscribe( (data: EtiquetteModel[]) => /*this.etiquettesSansObs = data*/ console.log(data));
  }

  public createEtiquette() {
    console.log("ajouter étiquette");
  }

  get etiquette$(): Observable<EtiquetteModel[]> {
    return this.etiquetteService.etiquettes$;
  }

  public addEtiquette(etiquette: EtiquetteModel) {
    let found = false;
    for(let i=0;i<this.nouveauMedia.listTag.length;i++) {
      if(this.nouveauMedia.listTag[i].nomTag == etiquette.nomTag) {
        found = true;
        break;
      }
    }
    if(!found) {
      this.nouveauMedia.listTag.push(etiquette);
    }
  }

  OnSubmitAdd() {
    //event.preventDefault();

    console.log(this.userForm.value);

    if (this.userForm.value.status != '') {
      //sauvegarde de la partie genre
      this.genreService.saveToApp(this.media.genres.map((genre:any) => new GenreAppModel(genre)))
        .subscribe(data => this.mediaService.saveToApp(this.media, this.typeMedia)
          .subscribe(data => {
              let statusApp = mapIhmStatusToBackendStatus(this.userForm.value.status);

              let mediaSelectionneObject = {
                typeMedia: this.typeMedia,
                avisPouce: false,
                dateSelection: '2023-08-17',
                etiquetteList: this.userForm.value.etiquettes,
                statutMedia: statusApp,
                mediaIdTmdb: this.media.idDataBase,
                email: this.email,
                dateModification: '2023-08-17',
                numeroSaison: 0,
                idTmdbSaison: 0,
                numeroEpisode: 0,
                idTmdbEpisode: 0
              }

              let mediaSelectionne = new MediaSelectionneModel(mediaSelectionneObject);

              this.mediaAppService.saveToApp(mediaSelectionne);
          }));
    }
  }

  OnSubmitDelete() {
    console.log("supprimer");
    console.log(this.userForm.value);
  }

  OnSubmitModify() {
    console.log("modifier");
    console.log(this.userForm.value);
  }

}
