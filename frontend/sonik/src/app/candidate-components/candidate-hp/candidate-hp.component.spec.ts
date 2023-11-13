import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateHpComponent } from './candidate-hp.component';

describe('CandidateHpComponent', () => {
  let component: CandidateHpComponent;
  let fixture: ComponentFixture<CandidateHpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CandidateHpComponent]
    });
    fixture = TestBed.createComponent(CandidateHpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
