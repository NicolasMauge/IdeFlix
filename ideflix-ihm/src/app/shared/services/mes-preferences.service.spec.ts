import { TestBed } from '@angular/core/testing';

import { MesPreferencesService } from './mes-preferences.service';

describe('MesPreferencesService', () => {
  let service: MesPreferencesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MesPreferencesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
