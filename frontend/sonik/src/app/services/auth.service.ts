import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = false;
  private tokenKey = 'token';

  constructor(public dialog: MatDialog ) { }

  initializeAuth() {
    const token = localStorage.getItem(this.tokenKey);
    
    if (token) {
      this.loggedIn = true;
    }
  }

  login(token: string) {
    
    this.loggedIn = true;
  }
  
  logout() { 
    localStorage.clear();
    this.loggedIn = false;
  }

  isLoggedIn() {
    return this.loggedIn;
  }
  getToken() {
    return localStorage.getItem(this.tokenKey); 
  }
}
