import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpService} from "../../../core/services/http.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  constructor(private httpService:HttpService) {}
  public handleSubmit($event:Event){
    $event.preventDefault();
    const credentials = {
      username: this.loginForm.get('username')?.value + '',
      password: this.loginForm.get('password')?.value + '',
    };
    this.httpService.login(credentials).subscribe(status => {
        let message = '';
        switch (status) {
          case 404:
            message = 'User not found.'
            break;
          case 401:
            message = 'Account not activated, please check your mailbox.'
            break;
          default:
            message = 'Unknown error, Please try later.';
            break;
        }
        this.loginForm.setErrors({message});
      }
    );
  }

  protected readonly JSON = JSON;
}
