import { Pipe, PipeTransform } from '@angular/core';
import {MapIhmService} from "../services/map-ihm-service";

@Pipe({
  name: 'mapIhmStatutToBackend'
})
export class MapIhmStatutToBackendPipe implements PipeTransform {

  constructor(private mapIhmService: MapIhmService) {}
  transform(ihmStatus: string): string | undefined {
    return this.mapIhmService.mapIhmStatusToBackendStatus(ihmStatus);
  }
}
