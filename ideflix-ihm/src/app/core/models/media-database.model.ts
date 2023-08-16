interface Genre {
  idDatabase: number;
  // idTmdb: string;
  nomGenre: string;
}

export class MediaDatabaseModel {

  // cette classe sert :
  //- de type (respect de la convention de type)
  // - d'instancier des objets MediaModel = mappage de nos objets pour s'affranchir de la réponse de l'API


  //déclaration des propriétés
  idDataBase!: number;
  titre!: string;
  dateSortie!: Date;
  duree!: number | undefined;
  resume!: string;
  image_portrait!: string;
  image_paysage!: string;
  scoreDataBase!: number;
  genres!: Genre[];

  constructor(movieFromApi: any) {
    this.idDataBase = movieFromApi.idDataBase;
    this.titre = movieFromApi.titre;
    this.duree = movieFromApi.duree? movieFromApi.duree : undefined;
    this.resume = movieFromApi.resume;
    this.image_paysage = movieFromApi.cheminAffichePaysage;
    this.image_portrait = movieFromApi.cheminAffichePortrait;
    this.scoreDataBase = movieFromApi.noteDataBase;
    this.genres = movieFromApi.genreDataBaseResponseDtos != undefined ?
      movieFromApi.genreDataBaseResponseDtos.map((idDataBase: number) => {
        return {idDataBase:idDataBase, nomGenre:''}
      }) :
      [...movieFromApi.genres];
    this.dateSortie = new Date(movieFromApi.dateSortie);
  }
}
