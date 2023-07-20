export class GenreModel {

  //déclaration des propriétés
  idGenre!: number;
  idTmdbGenre!: string;
  nomGenre!: string;
  checked!: boolean;

  constructor(genreFromApi: any) {
    this.idGenre = genreFromApi.id;
    this.idTmdbGenre = genreFromApi.idTmdb;
    this.nomGenre = genreFromApi.nomGenre;
    this.checked = false;
  }
}
