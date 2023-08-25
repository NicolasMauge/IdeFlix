import { Pipe, PipeTransform } from '@angular/core';
import {MediaMaListeModel} from "../../ma-liste-de-selection/models/media-ma-liste.model";
import {MapIhmService} from "../services/map-ihm-service";


@Pipe({
  name: 'filtrerMaListe'
})

export class FilterMaListePipe implements PipeTransform {

  constructor(private mapIhmService: MapIhmService) {
  }

  transform(mediaList:MediaMaListeModel[], filter: any): MediaMaListeModel[] {

    if (!mediaList)  {
      return [];
    }

    let filteredList = mediaList;

    if (filter.status) {
      const appStatus = this.mapIhmService.mapIhmStatusToBackendStatus(filter.status);
      if (appStatus) {
        filteredList = filteredList.filter(media => media.statutMedia === appStatus);
      }
    }

    if (filter.genre) {
      filteredList = filteredList.filter(media => media.media.genreList
                                 .some(genre => genre.nomGenre
                                   .includes(filter.genre)));
    }

    if (filter.etiquette) {
      filteredList = filteredList.filter(media => media.etiquetteList
        .some(etiquette => etiquette.nomTag
          .includes(filter.etiquette)));
    }
    return filteredList;
  }
}



