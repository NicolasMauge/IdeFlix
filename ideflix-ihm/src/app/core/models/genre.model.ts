export class GenreModel {

  //déclaration des propriétés
  id!: number;
  idTmdb!: string;
  nomGenre!: string;
  checked!: boolean;

  constructor(genreFromApi: any) {
    this.id = genreFromApi.id;
    this.idTmdb = genreFromApi.idTmdb;
    this.nomGenre = genreFromApi.nomGenre;
    this.checked = false;
  }

}
