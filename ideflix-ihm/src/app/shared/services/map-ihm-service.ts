import {Injectable} from "@angular/core";
import {Status} from "../../core/models/status";

@Injectable({
  providedIn: 'root', // Cela rend le service disponible dans tout l'application
})

export class MapIhmService {

  //faire correspondre les statuts IHM avec ceux du backend (IHM vers backend)
  mapIhmStatusToBackendStatus(ihmStatus: Status): string {
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
        return "";
    }
  }

  //faire correspondre les statuts IHM avec ceux du backend (backend vers IHM)
  mapBackendStatusToIhmStatus(backendStatus: string): Status {
    switch (backendStatus) {
      case "VU":
        return Status.Completed;
      case "A_VOIR":
        return Status.ToSee;
      case "EN_COURS":
        return Status.InProgress;
      case "ABANDONNE":
        return Status.Dropped;
      default:
        return Status.ToSee;
    }
  }
}
