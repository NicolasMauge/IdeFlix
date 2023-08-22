export class SaisonModel {

  //déclaration des propriétés
  dateSortieSaison!: Date;
  nombreEpisodes!: number;
  idDatabaseSaison!: number;
  titreSaison!: string;
  resumeSaison!: string;
  image_portraitSaison!: string;
  numeroSaison!: number;
  scoreDataBaseSaison!: number;

  constructor(saisonFromApi: any) {
    this.dateSortieSaison = saisonFromApi.dateSortie;
    this.nombreEpisodes = saisonFromApi.nombreEpisodes;
    this.idDatabaseSaison = saisonFromApi.idDataBase;
    this.titreSaison = saisonFromApi.titre;
    this.resumeSaison = saisonFromApi.resume;
    this.image_portraitSaison = saisonFromApi.cheminAffichePortrait;
    this.numeroSaison = saisonFromApi.numeroSaison;
    this.scoreDataBaseSaison = saisonFromApi.noteDataBase;
  }
}
