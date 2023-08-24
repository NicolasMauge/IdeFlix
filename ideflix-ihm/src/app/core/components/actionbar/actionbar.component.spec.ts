import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionbarComponent } from './actionbar.component';

describe('ActionbarComponent', () => {
  let component: ActionbarComponent;
  let fixture: ComponentFixture<ActionbarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActionbarComponent]
    });
    fixture = TestBed.createComponent(ActionbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
