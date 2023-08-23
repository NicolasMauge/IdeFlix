import {SaisonModel} from "./saison.model";

interface Genre {
  idDatabase: number;
  nomGenre: string;
}

export class MediaDatabaseModel {

  //déclaration des propriétés
  idDataBase!: number;
  titre!: string;
  dateSortie!: Date;
  duree!: number | undefined;
  resume!: string;
  image_portrait!: string | undefined;
  image_paysage!: string | undefined;
  scoreDataBase!: number;
  genres!: Genre[];
  typeMedia!: string;
  nombreSaisons!: number;
  saisons: SaisonModel[] = [];

  constructor(movieFromApi: any) {
    this.idDataBase = movieFromApi.idDataBase;
    this.titre = movieFromApi.titre;
    this.duree = movieFromApi.duree? movieFromApi.duree : undefined;
    this.resume = movieFromApi.resume;
    this.image_paysage = movieFromApi.cheminAffichePaysage? movieFromApi.cheminAffichePaysage : undefined;
    this.image_portrait = movieFromApi.cheminAffichePortrait? movieFromApi.cheminAffichePortrait : undefined;
    this.scoreDataBase = movieFromApi.noteDataBase;
    this.genres = movieFromApi.genreDataBaseResponseDtos != undefined ?
      movieFromApi.genreDataBaseResponseDtos.map((genre: any) => {
        return {idDatabase:genre.idDataBase, nomGenre:genre.nomGenre}
      }) :
      [...movieFromApi.genres];
    this.dateSortie = new Date(movieFromApi.dateSortie);
    this.typeMedia = movieFromApi.typeMedia;
    this.nombreSaisons = movieFromApi.nombreSaisons;
    this.saisons = movieFromApi.saisonDataBaseResponseDtos != undefined ?
      movieFromApi.saisonDataBaseResponseDtos.map((saison: any) => {
        return {dateSortieSaison:saison.dateSortie,
          nombreEpisodes:saison.nombreEpisodes,
          idDatabaseSaison:saison.idDataBase,
          titreSaison:saison.titre,
          resumeSaison:saison.resume,
          image_portraitSaison:saison.cheminAffichePortrait,
          numeroSaison:saison.numeroSaison,
          scoreDataBaseSaison:saison.noteDataBase}
      }) :
      [];
  }
}
