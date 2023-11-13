import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private tokenKey = 'token';
  

  constructor() { }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

/*   getRole(): string | null {
    return localStorage.getItem(this.roleKey);
  }

  setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  setRole(role: string) {
    localStorage.setItem(this.roleKey, role);
  } */

  removeToken() {
    localStorage.removeItem(this.tokenKey);
  }

  /* removeRole() {
    localStorage.removeItem(this.roleKey);
  } */
}