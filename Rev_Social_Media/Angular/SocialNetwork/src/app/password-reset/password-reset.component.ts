import { UserService } from './../services/user.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.css'],
})
export class PasswordResetComponent implements OnInit {
  @Output() onReset = new EventEmitter();
  constructor(private userService: UserService) {}

  ngOnInit(): void {}
  submitReset(input) {
    this.userService.ResetPassword(input).subscribe((res) => {
      if (res == null) {
        window.alert('incorrect username or password');
      } else {
        this.onReset.emit(res);
      }
    });
  }
}
