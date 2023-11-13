import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateMeetingConfirmationComponent } from './candidate-meeting-confirmation.component';

describe('CandidateMeetingConfirmationComponent', () => {
  let component: CandidateMeetingConfirmationComponent;
  let fixture: ComponentFixture<CandidateMeetingConfirmationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CandidateMeetingConfirmationComponent]
    });
    fixture = TestBed.createComponent(CandidateMeetingConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
