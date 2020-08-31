import { CommentService } from './../comment.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css'],
})
export class CommentFormComponent implements OnInit {
  @Input('user') user;
  @Input('type') type;
  @Input('post') post;

  @Output() newComment = new EventEmitter();
  image;
  placeholderText;
  textValue = '';
  constructor(
    private httpClient: HttpClient,
    private sanitizer: DomSanitizer,
    private commentService: CommentService
  ) {}

  submitCommentForm(input) {
    let comment: any = {
      username: this.user.username,
      pId: this.post.id,
      body: input.body,
    };
    this.commentService.addComment(comment).subscribe((x) => console.log(x));
    window.alert('comment submitted');
    comment.picLink = this.user.picLink;
    this.newComment.emit(comment);
    this.textValue = '';
  }
  ngOnInit(): void {
    this.getImage();
  }

  getImage() {
    let input = {
      username: this.user.username,
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
          console.log('no such user');
          this.image =
            'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1200px-No_image_available.svg.png';
        }
      );
  }
}
