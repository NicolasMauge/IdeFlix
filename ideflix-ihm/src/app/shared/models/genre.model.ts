export class GenreModel {

  //déclaration des propriétés
  idGenre!: number;
  idTmdbGenre!: string;
  nomGenre!: string;

  constructor(genreFromApi: any) {
    this.idGenre = genreFromApi.id;
    this.idTmdbGenre = genreFromApi.idTmdb;
    this.nomGenre = genreFromApi.nomGenre;
  }

}
