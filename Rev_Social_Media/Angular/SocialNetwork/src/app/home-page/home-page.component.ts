import { UserLikesService } from './../services/user-likes.service';
import { Comment } from './../interfaces/Comment';
import { CommentService } from './../comment.service';
import { UserService } from './../services/user.service';
import { PostService } from './../services/post.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Post } from '../interfaces/Post';
import { User } from '../interfaces/User';
import { SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  @Output() logout = new EventEmitter();
  view: string;
  tab = 'all';
  focus;
  focusPosts;
  isLoading = true;
  //current user
  @Input('user') user: User;

  posts = [];
  users;
  comments;
  likes;

  changeFocus(input) {
    this.focus = input;
    this.focusPosts = this.posts.filter((x) => x.username == input.username);
  }
  onLogout() {
    this.logout.emit();
  }
  pushPost(e) {
    e.picLink = this.user.picLink;
    e.isLiked = false;
    e.likeCount = 0;
    e.likes = 0;
    e.comments = [];
    this.posts.unshift(e);
  }
  pushUser(e) {
    this.user = e;
  }
  constructor(
    private postService: PostService,
    private userService: UserService,
    private commentService: CommentService,
    private userLikesService: UserLikesService
  ) {}

  ngOnInit(): void {
    this.view = 'Feed';
    this.postService.getPosts().subscribe((posts) => {
      this.userService.getUsers().subscribe((users) => {
        this.users = users;
        this.userLikesService.getAllLikes().subscribe((likes) => {
          this.likes = likes;
          this.commentService.getAllComments().subscribe((comments) => {
            posts.forEach((post: any) => {
              //Create Post Comments, adds them to the post obj
              post.comments = [];
              post.commentCount = 0;
              comments.forEach((comment: any) => {
                users.forEach((user) => {
                  if (user.username == comment.username) {
                    comment.picLink = user.picLink;
                  }
                });
                if (post.id == comment.pId) {
                  post.commentCount++;
                  post.comments.push(comment);
                }
              });
              //Add Post Authors picLink to post obj
              users.forEach((user) => {
                if (user.username == post.username) {
                  post.picLink = user.picLink;
                }
              });
              likes.forEach((like) => {
                if (
                  this.user.username == like.username &&
                  post.id == like.pId
                ) {
                  post.isLiked = true;
                  post.likeCount++;
                } else if (post.id == like.pId) {
                  post.likeCount++;
                }
              });
            });
          });
        });
      });
      this.posts = posts;
      this.focus = this.userService.getSampleUser();
      //TODO find a better place to end the loading screen
      this.isLoading = false;
      console.log('These are the post objects');
      console.log(this.posts);
      console.log('these are the users');
      console.log(this.users);
    });
  }
}
