import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserLikesService {
  url = 'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/likes';
  constructor(private http: HttpClient) {}
  getAllLikes(): Observable<any> {
    return this.http.get<any>(this.url + '/all');
  }
  getLikeCount(postId) {
    let input = {
      pId: postId,
    };
    return this.http.post(this.url + '/likecount', input);
  }
  getIsLiked(postId, username) {
    let input = {
      pId: postId,
      username,
    };
    return this.http.post(this.url + '/isliked', input);
  }
  addLike(username, postId?, commentId?) {
    let input = {
      pId: postId,
      cId: commentId,
      username,
    };
    return this.http.post(this.url + '/addlike', input);
  }
  deletePostLike(username, postId?) {
    let input = {
      pId: postId,
      username,
    };
    return this.http.post(this.url + '/deletelike', input);
  }
}
