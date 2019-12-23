import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Output()
  loginType:EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }


}
