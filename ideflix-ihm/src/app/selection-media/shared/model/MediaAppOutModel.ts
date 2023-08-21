import {GenreAppModel} from "./GenreAppModel";

export class MediaAppOutModel {
  idTmdb!: string;
  typeMedia!: string;
  titre!: string;
  dateSortie!: Date;
  duree!: number | undefined;
  cheminAffichePortrait!: string;
  cheminAffichePaysage!: string;
  noteTmdb!: number;
  genreList!: GenreAppModel[];
  nombreSaisons: number;


  constructor(mediaFromApi: any) {
    this.idTmdb = mediaFromApi.idTmdb;
    this.typeMedia = mediaFromApi.typeMedia;
    this.titre = mediaFromApi.titre;
    this.dateSortie = mediaFromApi.dateSortie;
    this.duree = mediaFromApi.duree? mediaFromApi.duree : undefined;
    this.cheminAffichePortrait = mediaFromApi.cheminAffichePortrait;
    this.cheminAffichePaysage = mediaFromApi.cheminAffichePaysage;
    this.noteTmdb = mediaFromApi.noteTmdb;
    this.genreList = mediaFromApi.genreList;
    this.nombreSaisons = mediaFromApi.nombreSaisons;
  }
}
