import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'focused-user-posts',
  templateUrl: './focused-user-posts.component.html',
  styleUrls: ['./focused-user-posts.component.css'],
})
export class FocusedUserPostsComponent implements OnInit {
  @Input() post;
  @Input('focus') user;
  @Input('user') currUser;
  constructor() {}

  ngOnInit(): void {}
}
