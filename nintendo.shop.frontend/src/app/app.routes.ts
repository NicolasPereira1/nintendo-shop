import { Routes } from '@angular/router';
import {LoginComponent} from "./features/pages/login/login.component";
import {RegisterComponent} from "./features/pages/register/register.component";
import {HomeComponent} from "./features/pages/home/home.component";
import {AuthGuard} from "./core/guards/auth.guard";

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', component: HomeComponent, canActivate: [AuthGuard]}
];
