import { Pipe, PipeTransform } from '@angular/core';
import {MapIhmService} from "../services/map-ihm-service";
import {Status} from "../../core/models/status";

@Pipe({
  name: 'mapIhmStatutToBackend'
})
export class MapIhmStatutToBackendPipe implements PipeTransform {

  constructor(private mapIhmService: MapIhmService) {}
  transform(ihmStatus: Status): string  {
    return this.mapIhmService.mapIhmStatusToBackendStatus(ihmStatus);
  }
}
