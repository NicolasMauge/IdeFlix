import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaListeFiltreComponent } from './ma-liste-filtre.component';

describe('MaListeFiltreComponent', () => {
  let component: MaListeFiltreComponent;
  let fixture: ComponentFixture<MaListeFiltreComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MaListeFiltreComponent]
    });
    fixture = TestBed.createComponent(MaListeFiltreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
