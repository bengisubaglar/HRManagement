import { Component } from '@angular/core';
import {MatDialog, MatDialogRef, MatDialogModule} from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss'],
  
})
export class WelcomeComponent {

  constructor(public dialog: MatDialog) {}

  openDialog(){
    this.dialog.open(LoginComponent);
    }
  
  openDialogRegister(){
    this.dialog.open(RegisterComponent);
  }
}