import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { AuthService } from './auth.service';
import { Observable, of, tap } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { LogoutComponent } from '../logout/logout.component';
import jwt_decode from "jwt-decode";


@Injectable({
  providedIn: 'root'
})
export class UserService {



  url = "http://localhost:8080/api/";

  constructor(private httpClient: HttpClient, private authService: AuthService, private router: Router, public dialog: MatDialog) { }


  login(data: any) {
    if (data.email.toLowerCase().includes('sonik')) {

      return this.httpClient.post(this.url +
        "auth/hr/login", data, {
        headers: new HttpHeaders()
      }).pipe(
        tap((response: any) => {
          const token = response.token;
          localStorage.setItem('token', token);
          this.authService.login(response.token);
        })
      );
    }
    else {
      return this.httpClient.post(this.url +
        "auth/candidate/login", data, {
        headers: new HttpHeaders()
      }).pipe(
        tap((response: any) => {
          const token = response.token;
          localStorage.setItem('token', token);
          this.authService.login(response.token);
        })
      );
    }

  }


  register(data: any) {
    return this.httpClient.post(this.url +
      "auth/candidate/register", data, {
      headers: new HttpHeaders()
    })
  }

  logout() {
    this.dialog.open(LogoutComponent);
  }

  redirectProfile() {
    this.router.navigate(['candidate/findByEmail']);
  }

  showProfile(): any {
    const token = localStorage.getItem('token');

    if (token) {
      const decodedToken: any = jwt_decode(token);
      const email = decodedToken.sub;
      return email;
    }

  }

  getCandidateId(): any{
    const token = localStorage.getItem('token');
    if(token){
      const decodedToken :any = jwt_decode(token);
      const id = decodedToken.id;
      return id;
    }
  }

  getRole(): any{
    const token = localStorage.getItem('token');
    if(token){
      const decodedToken: any = jwt_decode(token);
      const role = decodedToken.userRole;
      return role;
    }
  }

  submitUpdate(data: any) {
    return this.httpClient.post(this.url +
      "candidate/update", data, {
      headers: new HttpHeaders()
    });
  }


}
