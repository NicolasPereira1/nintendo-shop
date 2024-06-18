import { Component } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {HttpService} from "../../../core/services/http.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  constructor(private httpService: HttpService) {}

  registerForm = new FormGroup(
      {
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        confirmedPassword: new FormControl('', [Validators.required, this.samePasswordValidator()])
      }
  );

  samePasswordValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if(this.registerForm) {
        const same =
            control.value &&
            this.registerForm.get('password') &&
            control.value === this.registerForm.get('password')!.value;
        return !same ? { passwordMatch: false } : null;
      }
      return null;
    }
  }
  handleSubmit = () => {
    if(this.registerForm.valid){
      const user = {
        username: this.registerForm.get('username')!.value!,
        password: this.registerForm.get('password')!.value!
      };
      this.httpService.register(user).subscribe(status => {
        console.log(status);
        switch (status) {
          case 200:

            break;
        }
      });
    }
  };

}
