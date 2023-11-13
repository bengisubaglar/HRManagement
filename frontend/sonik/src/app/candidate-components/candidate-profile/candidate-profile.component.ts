import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription, identity } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import jwt_decode from "jwt-decode";
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.scss']
})
export class CandidateProfileComponent implements OnInit {
  profileForm: any = FormGroup;
  responseMessage: any = '';
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  
  constructor(private httpClient: HttpClient,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private datePipe: DatePipe,
    private matSnackBar: MatSnackBar
  ) { }


  result: any;
  url = "http://localhost:8080/api/";
  email:any;
  first: any;
  last: any;
  about:any;
  ngOnInit(): void {

     this.email = this.userService.showProfile();

  this.profileForm = this.formBuilder.group({
    firstName: [''],
    lastName: [''],
    birthdate: [''],
    tckn: [''],
    gender: [''],
    address: [''],
    about: ['']
  });

  this.httpClient.get(this.url + "candidate/findByEmail?email=" + this.email).subscribe((data: any) => {
    this.result = {
      firstName: data.firstName,
      lastName: data.lastName,
      birthdate: data.birthdate,
      tckn: data.tckn,
      gender: data.gender,
      address: data.address,
      about: data.about,
    }
    this.first = data.firstName;
    this.last = data.lastName;
    this.about = data.about;

    this.profileForm.patchValue({
      firstName: this.result.firstName,
      lastName: this.result.lastName,
      birthdate: this.result.birthdate,
      tckn: this.result.tckn,
      gender: this.result.gender,
      address: this.result.address,
      about: this.result.about
    });
  });
    
   
  }
  

  handleUpdate() {
    var formData = this.profileForm.value;
    const token = localStorage.getItem('token')
    var email = '';
    if (token) {
      const decodedToken: any = jwt_decode(token);
      email = decodedToken.sub;  
    }
    var data = {
      email: email,
      firstName: formData.firstName,
      lastName: formData.lastName,
      birthdate: this.datePipe.transform(formData.birthdate, 'yyyy-MM-dd'),
      tckn: formData.tckn,
      gender: formData.gender,
      address: formData.address,
      about: formData.about
    }
    this.userService.submitUpdate(data).subscribe((response: any) => {
      this.matSnackBar.open("Profile updated successfully!", "OKAY", { horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition})
    },(error) => {
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = "SOMETHING WENT WRONG.";
      }
      
      this.matSnackBar.open(this.responseMessage);
    });
  }

  logout() {
    this.userService.logout();
  }

}

