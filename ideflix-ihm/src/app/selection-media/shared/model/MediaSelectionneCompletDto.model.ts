import {EtiquetteModel} from "./Etiquette.model";
import {MediaAppOutModel} from "./MediaAppOut.model";

export class MediaSelectionneCompletDtoModel {
  typeMedia: string;
  avisPouce:boolean;
  dateSelection: Date;
  etiquetteList: EtiquetteModel[];
  statutMedia: string;
  media: MediaAppOutModel;
  email: string;
  dateModification: Date;
  numeroSaison: number;
  idTmdbSaison: string;
  numeroEpisode: number
  idTmdbEpisode: string;

  constructor(mediaFromIhm: any) {
    this.typeMedia = mediaFromIhm.typeMedia;
    this.avisPouce = mediaFromIhm.avisPouce;
    this.dateSelection = mediaFromIhm.dateSelection;
    this.etiquetteList = mediaFromIhm.etiquetteList;
    this.statutMedia = mediaFromIhm.statutMedia;
    this.media = mediaFromIhm.media;
    this.email = mediaFromIhm.email;
    this.dateModification = mediaFromIhm.dateModification;
    this.numeroSaison = mediaFromIhm.numeroSaison;
    this.idTmdbSaison = mediaFromIhm.idTmdbSaison;
    this.numeroEpisode = mediaFromIhm.numeroEpisode;
    this.idTmdbEpisode = mediaFromIhm.idTmdbEpisode;
  }
}
