import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { TokenService } from '../services/token.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],



})

export class LoginComponent {
  hide = true;
  loginForm: any = FormGroup;
  responseMessage: any = '';

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    public dialogRef: MatDialogRef<LoginComponent>,
    
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required,Validators.pattern(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i)]],
      password: [null, [Validators.required]]
    })
  }

  handleSubmit() {
    var formData = this.loginForm.value;
    var data = {
      email: formData.email,
      password: formData.password
    }

    this.userService.login(data).subscribe((response: any) => {     
    this.dialogRef.close();
    if(this.userService.getRole() == 'CANDIDATE'){
      this.router.navigate(['/candidate/homepage']);
    }
    if(this.userService.getRole() == 'HUMAN_RESOURCES'){
      this.router.navigate(['/hr/homepage'])
    }
    
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
