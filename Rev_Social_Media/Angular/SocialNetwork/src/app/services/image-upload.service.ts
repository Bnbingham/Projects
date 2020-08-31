import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ImageUploadService {
  url = 'http://localhost:9000/user';

  constructor(private http: HttpClient) {}

  imageUpload(imageForm: FormData) {
    console.log('image uploading');
    return this.http.post(this.url + '/updateprofilepic', imageForm);
  }
  getProfilePic(username) {
    return this.http.post(this.url + '/getprofilepic', username);
  }
}
