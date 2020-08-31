import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  view = 'login';
  user;

  onLogout() {
    this.view = 'login';
    this.user = null;
  }
  userChange(user) {
    console.log(user);
    this.user = user;
    this.view = 'home';
  }
}
