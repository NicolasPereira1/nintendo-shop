import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private router: Router) {}
  isAuthenticated = () => {
    const token = localStorage.getItem('access_token');
    return !! token && this.isTokenValid(token);
  };
  login = (token: string) => {
    localStorage.setItem('access_token', token);
    this.router.navigateByUrl('').then();
    return token;
  }
  logout = () => {
    localStorage.removeItem('access_token');
  }
  getToken = () => localStorage.getItem('access_token');

  private isTokenValid(token:string){
    const jwtTimestamp = jwtDecode(token).exp;
    return jwtTimestamp && jwtTimestamp > Date.now()/1000;
  }
}
