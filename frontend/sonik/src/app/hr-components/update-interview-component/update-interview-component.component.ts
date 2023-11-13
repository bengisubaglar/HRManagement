import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HrHpComponent } from '../hr-hp/hr-hp.component';
import { UserService } from 'src/app/services/user.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-interview-component',
  templateUrl: './update-interview-component.component.html',
  styleUrls: ['./update-interview-component.component.scss']
})
export class UpdateInterviewComponentComponent {

  updateCandidateForm: any = FormGroup;
  responseMessage: any = '';
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';


  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private hrComponent: HrHpComponent,
    private userService: UserService,
    public dialogRef: MatDialogRef<UpdateInterviewComponentComponent>,
    private matSnackBar: MatSnackBar) { }
    

  ngOnInit(): void {
    this.updateCandidateForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i)]],
      isPassed: ['']
    })
  }

  handleSubmit(){
    var formData = this.updateCandidateForm.value;
    var data = {
      hrId: this.userService.getCandidateId(),
      candidateEmail: formData.email, 
      isPassed: formData.isPassed,
      forceUpdate: false
    }
    this.hrComponent.handleUpdate(data).subscribe((response: any) => {     
      this.matSnackBar.open("Candidate upgraded to interview stage successfull!", "OKAY", { horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition})
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
