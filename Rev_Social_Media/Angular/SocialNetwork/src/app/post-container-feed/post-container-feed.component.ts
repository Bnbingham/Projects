import { PostService } from './../services/post.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'post-container-feed',
  templateUrl: './post-container-feed.component.html',
  styleUrls: ['./post-container-feed.component.css'],
})
export class PostContainerFeedComponent implements OnInit {
  @Input('user') user;
  @Input('postFeed') posts;

  constructor() {}

  ngOnInit(): void {}
}
