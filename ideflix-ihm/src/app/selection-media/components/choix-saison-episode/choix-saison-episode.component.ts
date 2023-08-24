import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatSelectChange} from "@angular/material/select";

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

@Component({
  selector: 'app-choix-saison-episode',
  templateUrl: './choix-saison-episode.component.html',
  styleUrls: ['./choix-saison-episode.component.css']
})
export class ChoixSaisonEpisodeComponent {
  @Input() media!: MediaDatabaseModel;
  @Output() emitterParent = new EventEmitter<SerieCurrentSaisonEpisode>();
  //saisonForm!: FormGroup;
  avancementSerie!: SerieCurrentSaisonEpisode;
  listeSaisons: SaisonElement[] = [];
  listeEpisodes: EpisodeElement[] = [];

  saisonCurrent: number = 1;
  episodeCurrent: number = 1;

  constructor(/*private formBuilder: FormBuilder*/) {
  }

  ngOnInit() {
    /*this.saisonForm = this.formBuilder.group({
      saison: [1],
      episode: [1]
    });*/

    this.avancementSerie = {
      saison : this.saisonCurrent,
      idSaisonTmdb: this.media.saisons[0].idDatabaseSaison.toString(),
      episode: this.episodeCurrent
    };

    this.defineListeSaisons();
    this.defineListeEpisodes();

    console.log(this.media);
    this.emitToParent();
  }

  onChangeSaison(numeroSaison: MatSelectChange) {
    this.saisonCurrent = numeroSaison.value;

    this.defineListeEpisodes();
    this.emitToParent();
  }

  onChangeEpisode(numeroEpisode: MatSelectChange) {
    this.episodeCurrent = numeroEpisode.value;

    this.emitToParent();
  }

  defineListeEpisodes() {
    //let numeroSaisonChoisie : number = this.saisonCurrent;
    let nombreEpisodes: number = this.nombreEpisodesSaison(this.saisonCurrent);
    this.listeEpisodes = [];

    for(let i:number=1;i<=nombreEpisodes;i++) {
      this.listeEpisodes.push({numeroEpisode: i, libelleEpisode:"Episode "+i});
    }

    //this.saisonForm.get('episode')?.setValue(1);
    this.episodeCurrent = 1;
  }

  defineListeSaisons() {
    this.listeSaisons = [];
    for(let i:number=0;i<=this.media.nombreSaisons;i++) {
      this.listeSaisons.push({numeroSaison: i, libelleSaison:this.media.saisons[i].titreSaison});
    }
  }

  nombreEpisodesSaison(numeroSaison: number) {
    return this.media.saisons[numeroSaison].nombreEpisodes;
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
