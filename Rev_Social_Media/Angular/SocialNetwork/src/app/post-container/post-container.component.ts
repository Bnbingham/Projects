import { PostService } from './../services/post.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'post-container',
  templateUrl: './post-container.component.html',
  styleUrls: ['./post-container.component.css'],
})
export class PostContainerComponent implements OnInit {
  @Input('user') user;
  @Input('postFeed') posts;
  @Input('isLoading') isLoading;
  @Output() newPost = new EventEmitter();
  pushPost(e) {
    this.newPost.emit(e);
  }
  constructor() {}

  ngOnInit(): void {}
}
