import { Pipe, PipeTransform } from '@angular/core';
import {GenreModel} from "../models/genre.model";

@Pipe({
  name: 'addCheckedProperty'
})
export class AddCheckedPropertyPipe implements PipeTransform {

  transform(allGenres: GenreModel[], userGenres: GenreModel[]): GenreModel[] {
    return allGenres.map((genre) => ({
      ...genre,
      checked: userGenres.some((userGenre) => userGenre.id === genre.id)
    }));
  }
}
