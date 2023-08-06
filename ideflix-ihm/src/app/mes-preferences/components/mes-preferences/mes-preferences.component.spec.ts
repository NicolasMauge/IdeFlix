import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesPreferencesComponent } from './mes-preferences.component';

describe('MesPreferencesComponent', () => {
  let component: MesPreferencesComponent;
  let fixture: ComponentFixture<MesPreferencesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MesPreferencesComponent]
    });
    fixture = TestBed.createComponent(MesPreferencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
