import { Comment } from './../interfaces/Comment';
import { Post } from './../interfaces/Post';
import { UserData } from './../interfaces/UserData';
import { UserLogin } from './../interfaces/UserLogin';
import { NewUser } from './../interfaces/NewUser';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/User';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  url = 'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/post';
  constructor(private httpClient: HttpClient) {}

  addPost(input): Observable<Post> {
    return this.httpClient.post<Post>(this.url + '/addpost', input);
  }
  getPostsByUser(input) {
    return this.httpClient.post(this.url + '/userordered', input);
  }
  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.url + '/allordered');
  }

  getTestPosts() {
    return [
      {
        id: 1,
        firstName: 'post.firstName',
        lastName: 'post.lastName',
        picLink:
          'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        post: 'post.post',
        image: 'post.image',
        date: 'post.date',
        //if entry exist in LIKES_TABLE matching current USER_ID & POST_ID
        isLiked: true,
        //number of entries in LIKES_TABLE with this POST_ID
        likeCount: 10,
        comments: [
          {
            firstName: 'comments.firstName',
            lastName: 'comments.lastName',
            picLink:
              'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
            body: 'comment.text1',
          },
          {
            firstName: 'comments.firstName',
            lastName: 'comments.lastName',
            picLink:
              'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
            body: 'comment.text1',
          },
          {
            firstName: 'comments.firstName',
            lastName: 'comments.lastName',
            picLink:
              'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
            body: 'comment.text1',
          },
        ],
      },
    ];
  }
}
