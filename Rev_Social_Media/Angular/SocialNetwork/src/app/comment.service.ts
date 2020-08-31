import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  url = 'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/comment';
  constructor(private http: HttpClient) {}

  getAllComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url + '/all');
  }
  addComment(input) {
    return this.http.post(this.url + '/addcomment', input);
  }
}
