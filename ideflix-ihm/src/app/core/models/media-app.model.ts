
interface Genre {
  id: number;
  idTmdb: string;
  nomGenre: string;
}


export class MediaAppModel {

  //déclaration des propriétés
  idTmdb!: string;
  typeMedia!: string;
  titre!: string;
  dateSortie!: Date;
  duree!: number | undefined;
  resume!: string | undefined;
  cheminAffichePortrait!: string;
  cheminAffichePaysage!: string;
  noteTmdb!: number;
  genreList!: Genre[];
  nombreSaisons: number;


  constructor(mediaFromApi: any) {
    this.idTmdb = mediaFromApi.id;
    this.typeMedia = mediaFromApi.typeMedia;
    this.titre = mediaFromApi.titre;
    this.dateSortie = mediaFromApi.dateSortie;
    this.duree = mediaFromApi.duree? mediaFromApi.duree : undefined;
    this.resume = mediaFromApi.resume ? mediaFromApi.resume : undefined;
    this.cheminAffichePortrait = mediaFromApi.cheminAffichePortrait;
    this.cheminAffichePaysage = mediaFromApi.cheminAffichePaysage;
    this.noteTmdb = mediaFromApi.noteTmdb;
    this.genreList = mediaFromApi.genreList != undefined ?
      mediaFromApi.genreList.map((id: number, idTmdb: string, nomGenre: string) => {
        return {id:id, idTmdb: idTmdb, nomGenre: nomGenre}
      }) :
      [...mediaFromApi.genreList];
    this.nombreSaisons = mediaFromApi.nombreSaisons;
  }


}
