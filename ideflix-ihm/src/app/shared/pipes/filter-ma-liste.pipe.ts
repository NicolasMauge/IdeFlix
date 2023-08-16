import { Pipe, PipeTransform } from '@angular/core';
import {MediaMaListeModel} from "../../ma-liste-de-selection/models/media-ma-liste.model";
import {Status} from "../../core/models/status";

// fonction pour la correspondance entre les status provenant du backend et les status affichés sur l'ihm
function mapIhmStatusToBackendStatus(ihmStatus: string): string | undefined {
  switch (ihmStatus) {
    case Status.Completed:
      return "VU";
    case Status.ToSee:
      return "A_VOIR";
    case Status.InProgress:
      return "EN_COURS";
    case Status.Pending:
      return "ABANDONNE";
    default:
      return undefined;
  }
}

@Pipe({
  name: 'filtrerMaListe'
})

export class FilterMaListePipe implements PipeTransform {

  transform(mediaList:MediaMaListeModel[], filter: any): MediaMaListeModel[] {

    console.log("pipe: " + mediaList)
    console.log("filter.status: " + filter.status)
    if (!mediaList)  {
      return [];
    }

    let filteredList = mediaList;

    if (filter.status) {
      const appStatus = mapIhmStatusToBackendStatus(filter.status);
      if (appStatus) {
        filteredList = filteredList.filter(media => media.statutMedia === appStatus);
      }
    }

    if (filter.genre) {
      filteredList = filteredList.filter(media => media.media.genreList
                                 .some(genre => genre.nomGenre.toUpperCase()
                                   .includes(filter.genre.toUpperCase())));
    }
    return filteredList;
  }
}



