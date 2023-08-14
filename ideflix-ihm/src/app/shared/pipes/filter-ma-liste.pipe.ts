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
// export class FilterMaListePipe implements PipeTransform {
//
//   transform(items: any[], filter: any): any[] {
//     if (!items) {
//       return [];
//     }
//
//     const { status, genre } = filter;
//
//     return items.filter(item => {
//       const statusMatch = !status || item.status.toLowerCase().includes(status.toLowerCase());
//       const genreMatch = !genre || item.genre.toLowerCase().includes(genre.toLowerCase());
//
//       return statusMatch && genreMatch;
//     });
//   }
//
// }

export class FilterMaListePipe implements PipeTransform {

  transform(mediaList:MediaMaListeModel[], filter: any): MediaMaListeModel[] {

    console.log("pipe: " + mediaList)
    console.log("filter.status: " + filter.status)
    if (!mediaList)  {
      return [];
    }

    // const { status, genre } = filter;

    if (filter.status) {
      const appStatus = mapIhmStatusToBackendStatus(filter.status);
      if (appStatus) {
        mediaList = mediaList.filter(media => media.statutMedia === appStatus);
      }
    }
    return mediaList;
  }
}

