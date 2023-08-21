import {Injectable} from "@angular/core";
import {Status} from "../../core/models/status";

@Injectable({
  providedIn: 'root', // Cela rend le service disponible dans tout l'application
})

export class MapIhmService {

  //pour la correspondance entre les status provenant du backend et les status affichés sur l'ihm
  mapIhmStatusToBackendStatus(ihmStatus: string): string | undefined {
    switch (ihmStatus) {
      case Status.Completed:
        return "VU";
      case Status.ToSee:
        return "A_VOIR";
      case Status.InProgress:
        return "EN_COURS";
      case Status.Dropped:
        return "ABANDONNE";
      default:
        return undefined;
    }
  }
}
