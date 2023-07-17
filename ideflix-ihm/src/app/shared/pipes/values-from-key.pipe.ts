import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'valuesFromKey'
})
export class ValuesFromKeyPipe implements PipeTransform {

  transform(value: any): any[] {
    return Object.keys(value).map(key => value[key]);
  }

}
