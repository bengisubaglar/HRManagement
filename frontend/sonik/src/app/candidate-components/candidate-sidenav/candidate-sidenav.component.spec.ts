import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateSidenavComponent } from './candidate-sidenav.component';

describe('CandidateSidenavComponent', () => {
  let component: CandidateSidenavComponent;
  let fixture: ComponentFixture<CandidateSidenavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CandidateSidenavComponent]
    });
    fixture = TestBed.createComponent(CandidateSidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
