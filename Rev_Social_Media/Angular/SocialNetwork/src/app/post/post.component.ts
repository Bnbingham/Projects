import { UserLikesService } from './../services/user-likes.service';
import { PostService } from './../services/post.service';
import { Component, OnInit, Input } from '@angular/core';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
  @Input('user') user;
  @Input('in') post;
  @Input() currUser;

  image: SafeUrl;
  image2;

  showPost: boolean = true;
  showComments: boolean;
  constructor(
    private httpClient: HttpClient,
    private sanitizer: DomSanitizer,
    private userLikesService: UserLikesService
  ) {}
  ngOnInit(): void {
    this.getImage();
    this.image2 = 'data:image/jpg;base64,' + this.post.image;
  }
  postToggled() {
    this.showPost = !this.showPost;
    this.showComments = false;
  }
  commentsToggled() {
    this.showComments = !this.showComments;
  }

  getImage() {
    let input = {
      username: this.post.username,
    };
    this.httpClient
      .post(
        'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/user/getprofilepic',
        input
      )
      .subscribe(
        (x) => {
          let data = x[0];
          this.image = this.sanitizer.bypassSecurityTrustResourceUrl(
            'data:image/jpg;base64,' + data
          );
        },
        (error) => {
          this.image =
            'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1200px-No_image_available.svg.png';
        }
      );
  }
}
