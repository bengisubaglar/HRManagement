import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateInterviewComponentComponent } from './update-interview-component.component';

describe('UpdateInterviewComponentComponent', () => {
  let component: UpdateInterviewComponentComponent;
  let fixture: ComponentFixture<UpdateInterviewComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateInterviewComponentComponent]
    });
    fixture = TestBed.createComponent(UpdateInterviewComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
