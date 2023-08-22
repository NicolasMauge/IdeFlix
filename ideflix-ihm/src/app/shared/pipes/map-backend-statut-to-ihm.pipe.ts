import { Pipe, PipeTransform } from '@angular/core';
import {MapIhmService} from "../services/map-ihm-service";

@Pipe({
  name: 'mapBackendStatutToIhm'
})
export class MapBackendStatutToIhmPipe implements PipeTransform {

  constructor(private mapIhmService: MapIhmService) {}

  transform(backendStatus: string): string | undefined {
    return this.mapIhmService.mapBackendStatusToIhmStatus(backendStatus);
  }
}
