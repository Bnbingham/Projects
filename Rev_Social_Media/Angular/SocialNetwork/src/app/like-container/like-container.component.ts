import { UserLikesService } from './../services/user-likes.service';
import { Input, Output, EventEmitter } from '@angular/core';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'like-container',
  templateUrl: './like-container.component.html',
  styleUrls: ['./like-container.component.css'],
})
export class LikeContainerComponent implements OnInit {
  @Input('user') user;
  @Input('post') post;

  initIsSelected;
  constructor(private uselikesService: UserLikesService) {}

  ngOnInit(): void {
    // this.initIsSelected = this.isSelected;
  }

  onClick() {
    console.log(this.user.username);
    this.post.isLiked = !this.post.isLiked;
    if (this.post.isLiked == true) {
      this.post.likeCount += 1;
      this.uselikesService
        .addLike(this.user.username, this.post.id)
        .subscribe((res) => console.log(res));
    } else {
      this.post.likeCount -= 1;
      this.uselikesService
        .deletePostLike(this.user.username, this.post.id)
        .subscribe((res) => console.log(res));
    }
  }
}
