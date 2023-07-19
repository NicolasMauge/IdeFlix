import {GenreModel} from "./genre.model";

export class PreferenceModel {

  //déclaration des propriétés
  email!: string;
  pseudo!: string;
  genreList!: GenreModel[];

  constructor(preferencesFromApi: any) {
    this.email = preferencesFromApi.email;
    this.pseudo = preferencesFromApi.pseudo;
    this.genreList = preferencesFromApi.genre_ids != undefined ?
      preferencesFromApi.genre_ids.map((item: number) => {
        return {id:item, idTmdb: '', nomGenre:''}
      }) :
      [...preferencesFromApi.genreList];
  }
}
