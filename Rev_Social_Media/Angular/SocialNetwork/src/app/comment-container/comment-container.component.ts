import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'comment-container',
  templateUrl: './comment-container.component.html',
  styleUrls: ['./comment-container.component.css'],
})
export class CommentContainerComponent implements OnInit {
  @Input('user') user;
  @Input('comments') comments;
  @Input('post') post;
  // @Output() toggled = new EventEmitter();
  pushComment(e) {
    this.comments.push(e);
  }
  constructor() {}

  ngOnInit(): void {}
}
