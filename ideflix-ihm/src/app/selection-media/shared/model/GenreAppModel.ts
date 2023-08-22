export class GenreAppModel {
  idTmdb: string;
  nomGenre: string;

  constructor(genreFromApi: any) {
    this.idTmdb = genreFromApi.idDatabase;
    this.nomGenre = genreFromApi.nomGenre;
  }
}
