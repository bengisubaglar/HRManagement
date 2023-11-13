import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  hide = true;
  registerForm: any = FormGroup;
  responseMessage: any = '';

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    public dialogRef: MatDialogRef<RegisterComponent>,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i)]],
      password: [null, [Validators.required]],
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      birthdate: ['', Validators.required],
      tckn: [null, Validators.required]
    })
  }

  handleSubmit() {
    var formData = this.registerForm.value;
    var data = {
      email: formData.email,
      password: formData.password,
      firstName: formData.firstName,
      lastName: formData.lastName,
      birthdate: this.datePipe.transform(formData.birthdate,'yyyy-MM-dd'),
      tckn: formData.tckn
    }
    

    this.userService.register(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.router.navigate(['']);

   
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
