import {MediaAppModel} from "../../core/models/media-app.model";

interface Etiquette {
  id: number;
  nomTag: string;
}

export class MediaMaListeModel {

  //déclaration des propriétés
  typeMedia!: string;
  avisPouce!: boolean;
  dateSelection!: Date;
  etiquetteList!: Etiquette[];
  statutMedia!: string;
  media!: MediaAppModel;
  email!: string;
  dateModification!: Date;
  numeroSaison!: number;
  idTmdbSaison : string;
  numeroEpisode : number;
  idTmdbEpisode: string;

  constructor(mediaSelectionneFromApi: any) {
    this.typeMedia = mediaSelectionneFromApi.typeMedia;
    this.avisPouce = mediaSelectionneFromApi.avisPouce;
    this.dateSelection = mediaSelectionneFromApi.dateSelecetion;
    this.etiquetteList = mediaSelectionneFromApi.etiquetteList != undefined ?
      mediaSelectionneFromApi.etiquetteList.map((id: number, nomTag: string) => {
        return {id:id, nomTag: nomTag}
      }) :
      [...mediaSelectionneFromApi.genreList];
    this.statutMedia = mediaSelectionneFromApi.statutMedia;
    this.media = mediaSelectionneFromApi.media;
    // this.media = mediaSelectionneFromApi.media != undefined ?
    //   mediaSelectionneFromApi.media.map((mediaApp: MediaAppModel) => {
    //     return mediaApp
    //   }) : [mediaSelectionneFromApi.media];
    this.email = mediaSelectionneFromApi.email;
    this.dateModification = mediaSelectionneFromApi.dateModification;
    this.numeroSaison = mediaSelectionneFromApi.numeroSaison;
    this.idTmdbSaison = mediaSelectionneFromApi.idTmdbSaison;
    this.numeroEpisode = mediaSelectionneFromApi.numeroEpisode;
    this.idTmdbEpisode = mediaSelectionneFromApi.idTmdbEpisode;
  }
}
