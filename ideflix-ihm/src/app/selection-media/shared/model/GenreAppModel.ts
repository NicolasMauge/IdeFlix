export class GenreAppModel {
  idTmdb: string;
  nomGenre: string;

  constructor(genreFromApi: any) {
    this.idTmdb = genreFromApi.id;
    this.nomGenre = genreFromApi.name;
  }
}
