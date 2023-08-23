import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MediaDatabaseModel} from "../../../core/models/media-database.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
  saisonForm!: FormGroup;
  avancementSerie!: SerieCurrentSaisonEpisode;
  listeSaisons: SaisonElement[] = [];
  listeEpisodes: EpisodeElement[] = [];

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.saisonForm = this.formBuilder.group({
      saison: [1],
      episode: [1]
    });

    this.avancementSerie = {saison : 1,
                            idSaisonTmdb: this.media.saisons[0].idDatabaseSaison.toString(),
                            episode: 1};

    this.defineListeSaisons();
    this.defineListeEpisodes();

    console.log(this.media);
  }

  onChangeSaison() {
    this.defineListeEpisodes();
  }

  onChangeEpisode() {
    this.avancementSerie = {saison: this.saisonForm.value.saison,
                            idSaisonTmdb: this.media.saisons[this.saisonForm.value.saison-1].idDatabaseSaison.toString(),
                            episode: this.saisonForm.value.episode};
    this.emitterParent.emit(this.avancementSerie);
  }

  defineListeEpisodes() {
    let numeroSaisonChoisie : number = this.saisonForm.value.saison;
    let nombreEpisodes: number = this.nombreEpisodesSaison(numeroSaisonChoisie-1);
    this.listeEpisodes = [];

    for(let i:number=1;i<=nombreEpisodes;i++) {
      this.listeEpisodes.push({numeroEpisode: i, libelleEpisode:"Episode "+i});
    }

    this.saisonForm.get('episode')?.setValue(1);
  }

  defineListeSaisons() {
    this.listeSaisons = [];
    for(let i:number=1;i<=this.media.nombreSaisons;i++) {
      this.listeSaisons.push({numeroSaison: i, libelleSaison:"Saison "+i});
    }
  }

  nombreEpisodesSaison(numeroSaisonAPartirDeZero: number) {
    return this.media.saisons[numeroSaisonAPartirDeZero].nombreEpisodes;
  }
}
