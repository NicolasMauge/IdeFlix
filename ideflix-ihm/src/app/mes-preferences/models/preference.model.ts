import {GenreModel} from "../../core/models/genre.model";

export class PreferenceModel {

  //déclaration des propriétés
  email!: string;
  pseudo!: string;
  genreList!: GenreModel[];

  constructor(preferencesFromApi: any) {
    this.email = preferencesFromApi.email;
    this.pseudo = preferencesFromApi.pseudo;

    // Vérification si preferencesFromApi.genreList existe et est un tableau
    if (Array.isArray(preferencesFromApi.genreList)) {
      // Utilisation de la méthode map pour transformer chaque élément de preferencesFromApi.genreList en un objet GenreModel
      this.genreList = preferencesFromApi.genreList.map((genreFromApi: any) => new GenreModel(genreFromApi));
    } else {
      this.genreList = [];
    }
  }

}
