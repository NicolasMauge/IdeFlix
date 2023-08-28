import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {Observable} from "rxjs";
import {MediaSelectionneCompletDtoModel} from "../../shared/model/MediaSelectionneCompletDto.model";

export interface SerieCurrentSaisonEpisode {
  saison: number,
  idSaisonTmdb: string,
  episode: number
}

export interface SaisonElement {
  numeroSaison : number,
  libelleSaison: string
}

export interface EpisodeElement {
  numeroEpisode : number,
  libelleEpisode: string
}

export interface SaisonEpisode {
  saison: number,
  episode: number
}

@Component({
  selector: 'app-choix-saison-episode',
  templateUrl: './choix-saison-episode.component.html',
  styleUrls: ['./choix-saison-episode.component.css']
})
export class ChoixSaisonEpisodeComponent {
  @Input() media!: MediaDatabaseModel;
  @Input() mediaSelectionne!: Observable<MediaSelectionneCompletDtoModel[]>

  @Output() emitterParent = new EventEmitter<SerieCurrentSaisonEpisode>();
  //saisonForm!: FormGroup;
  avancementSerie!: SerieCurrentSaisonEpisode;
  listeSaisons: SaisonElement[] = [];
  listeEpisodes: EpisodeElement[] = [];

  saisonCurrent: number = 1;
  episodeCurrent: number = 1;

  constructor() {
  }

  ngOnInit() {
    this.avancementSerie = {
      saison : this.saisonCurrent,
      idSaisonTmdb: this.media.saisons[this.saisonCurrent].idDatabaseSaison.toString(),
      episode: this.episodeCurrent
    };

    this.mediaSelectionne.subscribe((data:MediaSelectionneCompletDtoModel[]) => {
      if(data.length>0) {
        this.saisonCurrent = data[0].numeroSaison;
        this.episodeCurrent = data[0].numeroEpisode;

        this.emitToParent();
      }
    })

    this.defineListeSaisons();
    this.defineListeEpisodes();

    this.emitToParent();
  }

  onChangeSaison() {
    this.defineListeEpisodes();
    this.emitToParent();
  }

  onChangeEpisode() {
    this.emitToParent();
  }

  defineListeSaisons() {
    this.listeSaisons = [];
    for(let i:number=0;i<=this.media.nombreSaisons;i++) {
      this.listeSaisons.push({numeroSaison: i, libelleSaison:this.media.saisons[i].titreSaison});
    }
  }

  defineListeEpisodes() {
    let nombreEpisodes: number = this.nombreEpisodesSaison(this.saisonCurrent);
    this.listeEpisodes = [];

    for(let i:number=1;i<=nombreEpisodes;i++) {
      this.listeEpisodes.push({numeroEpisode: i, libelleEpisode:"Episode "+i});
    }

    this.episodeCurrent = 1;
  }
  nombreEpisodesSaison(numeroSaison: number) {
    return (this.media.saisons)?this.media.saisons[numeroSaison].nombreEpisodes:0;
  }

  emitToParent() {
    this.avancementSerie = {
      saison: this.saisonCurrent,
      idSaisonTmdb: this.media.saisons[this.saisonCurrent].idDatabaseSaison.toString(),
      episode: this.episodeCurrent
    };

    this.emitterParent.emit(this.avancementSerie);
  }
}
