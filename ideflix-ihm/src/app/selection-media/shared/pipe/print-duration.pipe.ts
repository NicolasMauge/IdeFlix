import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'printDuration'
})
export class PrintDurationPipe implements PipeTransform {

  transform(value: number|undefined): string {
    if(value!=undefined) {
      let hours = Math.floor(value/60);
      let minutes = value % 60;
      return hours+'h'+minutes+'m';
    } else {
      return '';
    }
  }
}
