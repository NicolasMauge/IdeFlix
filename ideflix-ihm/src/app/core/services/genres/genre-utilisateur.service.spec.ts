import { TestBed } from '@angular/core/testing';

import { GenreUtilisateurService } from './genre-utilisateur.service';

describe('GenreUtilisateurService', () => {
  let service: GenreUtilisateurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GenreUtilisateurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
