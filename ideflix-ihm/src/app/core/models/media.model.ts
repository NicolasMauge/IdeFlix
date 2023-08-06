interface Genre {
  id: number;
  // idTmdb: string;
  name: string;
}

// interface Acteur {
//   id: number;
//   // idTmdbActeur: string;
//   name: string;
// }



export class MediaModel {

  // cette classe sert :
  //- de type (respect de la convention de type)
  // - d'instancier des objets MediaModel = mappage de nos objets pour s'affranchir de la réponse de l'API


  //déclaration des propriétés
  id!: number;
  idTmdb!: string;
  titre!: string;
  date!: Date;
  duration!: number | undefined;
  resume!: string;
  image_portrait!: string;
  image_landscape!: string;
  score!: number;
  genres!: Genre[];
  // acteurs!: Acteur[];
  videos!:string;

  constructor(movieFromApi: any) {
    this.id = movieFromApi.id;
    this.idTmdb = movieFromApi.id;
    this.titre = movieFromApi.title;
    this.duration = movieFromApi.runtime? movieFromApi.runtime : undefined;
    this.resume = movieFromApi.overview;
    this.image_landscape = movieFromApi.backdrop_path;
    this.image_portrait = movieFromApi.poster_path;
    this.score = movieFromApi.vote_average;
    this.genres = movieFromApi.genre_ids != undefined ?
      movieFromApi.genre_ids.map((item: number) => {
        return {id:item, name:''}
      }) :
      [...movieFromApi.genres];
    // this.acteurs = movieFromApi.cast_ids != undefined ?
    //   movieFromApi.cast_ids.map((item: number) => {
    //     return {id:item, name:''}
    //   }) :
    //   [...movieFromApi.acteurs];
    this.date = new Date(movieFromApi.release_date);
  }
}
