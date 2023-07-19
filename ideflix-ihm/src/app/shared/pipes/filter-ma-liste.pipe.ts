import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtrerMaListe'
})
export class FilterMaListePipe implements PipeTransform {

  transform(items: any[], filter: any): any[] {
    if (!items) {
      return [];
    }

    const { status, genre } = filter;

    return items.filter(item => {
      const statusMatch = !status || item.status.toLowerCase().includes(status.toLowerCase());
      const genreMatch = !genre || item.genre.toLowerCase().includes(genre.toLowerCase());

      return statusMatch && genreMatch;
    });
  }

}
