import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrHpComponent } from './hr-hp.component';

describe('HrHpComponent', () => {
  let component: HrHpComponent;
  let fixture: ComponentFixture<HrHpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HrHpComponent]
    });
    fixture = TestBed.createComponent(HrHpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
