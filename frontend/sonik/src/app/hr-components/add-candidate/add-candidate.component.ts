import { Component, Injectable, OnInit } from '@angular/core';
import { HrHpComponent } from '../hr-hp/hr-hp.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-candidate',
  templateUrl: './add-candidate.component.html',
  styleUrls: ['./add-candidate.component.scss']
})


export class AddCandidateComponent {
  addCandidateForm: any = FormGroup;
  responseMessage: any = '';

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private hrComponent: HrHpComponent,
    private userService: UserService,
    public dialogRef: MatDialogRef<AddCandidateComponent>) { }
    

  ngOnInit(): void {
    this.addCandidateForm = this.formBuilder.group({
      to: [null, [Validators.required, Validators.pattern(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i)]]
    })
  }

  handleSubmit(){
    var formData = this.addCandidateForm.value;
    var data = {
      to: formData.to,
    }
    this.hrComponent.handleSubmit(data).subscribe((response: any) => {
      
    }, (error) => {
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = "SOMETHING WENT WRONG.";
      }
      alert(this.responseMessage);
    });
   
  }


}
