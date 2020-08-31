import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css'],
})
export class PostFormComponent implements OnInit {
  @Input('user') user;
  @Output() newPost = new EventEmitter();
  constructor() {}
  pushPost(e) {
    this.newPost.emit(e);
  }
  ngOnInit(): void {}
}
