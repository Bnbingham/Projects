import { UserService } from './../services/user.service';
import { HttpClient } from '@angular/common/http';
import { UserLogin } from './../interfaces/UserLogin';
import { NewUser } from './../interfaces/NewUser';
import { PostService } from './../services/post.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  @Output() userChange = new EventEmitter();

  passwordReset = false;
  togglePasswordReset() {
    this.passwordReset = !this.passwordReset;
  }
  constructor(
    private postService: PostService,
    private userService: UserService
  ) {}
  onReset(event) {
    this.userChange.emit(event);
  }
  ngOnInit(): void {}

  submitLogin(input: UserLogin) {
    //Submits UserLogin
    this.userService.validateUserLogin(input).subscribe(
      (response) => {
        if (response == null) {
          window.alert('Incorrect username or password');
        } else {
          this.userChange.emit(response);
        }
      },
      (error) => {
        window.alert('Error retrieving user');
      }
    );
  }
  submitRegisteration(input: NewUser) {
    //submit NewUser
    this.userService.addUser(input).subscribe(
      (response) => {
        this.userChange.emit(response);
      },
      (error) => {
        window.alert('Error creating user');
      }
    );
  }
}
