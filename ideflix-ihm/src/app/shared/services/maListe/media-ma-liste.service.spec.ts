import { TestBed } from '@angular/core/testing';

import { MediaMaListeService } from './media-ma-liste.service';

describe('MediaMaListeService', () => {
  let service: MediaMaListeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MediaMaListeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
