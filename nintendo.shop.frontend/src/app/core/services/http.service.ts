import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {catchError, of, tap} from "rxjs";

type LoginDTO = {username: string, password: string};
type RegisterDTO = {username: string, password: string};
type TokenDTO = {token: string, type: string};

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  BACKEND_URL = 'http://localhost:8080';
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.authService.getToken()}`
  })
  handleError = (response: any) => of(response.status);
  constructor(private http: HttpClient, private authService: AuthService) {}

  login = (credentials: LoginDTO) => {
    const url = `${this.BACKEND_URL}/api/auth/login`;
    return this.http
        .post<TokenDTO>(url, credentials)
        .pipe(
            catchError(this.handleError),
            tap(response =>
                typeof response != 'number' ?
                    this.authService.login(response.token) :
                    response
            )
        );
  };

  register = (user: RegisterDTO) => {
    const url = `${this.BACKEND_URL}/api/auth/register`;
    return this.http
        .post<any>(url, user)
        .pipe(
            tap(response => response.status)
        );
  }

  ping = () => {
    const url = `${this.BACKEND_URL}/ping`;
    this.http.get(url, { headers: this.headers, responseType: 'text' }).subscribe(
            response => alert(JSON.stringify(response))
    );
  };
}
