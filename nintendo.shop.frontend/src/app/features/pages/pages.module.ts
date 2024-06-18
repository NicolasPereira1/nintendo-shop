import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {GameListComponent} from "../components/game-list/game-list.component";

@NgModule({
  declarations: [
      HomeComponent,
      LoginComponent,
      RegisterComponent
  ],
    imports: [
        CommonModule,
        FormsModule,
        RouterLink,
        ReactiveFormsModule,
        GameListComponent
    ]
})
export class PagesModule { }
