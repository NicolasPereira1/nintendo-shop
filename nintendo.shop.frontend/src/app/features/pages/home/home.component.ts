import { Component } from '@angular/core';
import {HttpService} from "../../../core/services/http.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent {
  constructor(private httpService: HttpService) {
  }
  ping = () => this.httpService.ping();
}
