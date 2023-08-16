
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



export class SerieModel {

  // cette classe sert :
  //- de type (respect de la convention de type)
  // - d'instancier des objets SerieModel = mappage de nos objets pour s'affranchir de la réponse de l'API


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

  constructor(serieFromApi: any) {
    this.id = serieFromApi.id;
    this.idTmdb = serieFromApi.id;
    this.titre = serieFromApi.name;
    this.duration = serieFromApi.runtime? serieFromApi.runtime : undefined;
    this.resume = serieFromApi.overview;
    this.image_landscape = serieFromApi.backdrop_path;
    this.image_portrait = serieFromApi.poster_path;
    this.score = serieFromApi.vote_average;
    this.genres = serieFromApi.genre_ids != undefined ?
      serieFromApi.genre_ids.map((item: number) => {
        return {id:item, name:''}
      }) :
      [...serieFromApi.genres];
    // this.acteurs = movieFromApi.cast_ids != undefined ?
    //   movieFromApi.cast_ids.map((item: number) => {
    //     return {id:item, name:''}
    //   }) :
    //   [...movieFromApi.acteurs];
    this.date = new Date(serieFromApi.first_air_date);
  }
}
