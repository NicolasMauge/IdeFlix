export class GenreModel {

  //déclaration des propriétés
  id!: number;
  idTmdb!: string;
  nomGenre!: string;

  constructor(genreFromApi: any) {
    this.id = genreFromApi.id;
    this.idTmdb = genreFromApi.idTmdb;
    this.nomGenre = genreFromApi.nomGenre;
  }
}
