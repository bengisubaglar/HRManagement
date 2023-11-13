import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateTestConfirmationComponent } from './candidate-test-confirmation.component';

describe('CandidateTestConfirmationComponent', () => {
  let component: CandidateTestConfirmationComponent;
  let fixture: ComponentFixture<CandidateTestConfirmationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CandidateTestConfirmationComponent]
    });
    fixture = TestBed.createComponent(CandidateTestConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
