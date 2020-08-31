import { PostService } from './../services/post.service';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'user-content-input-form',
  templateUrl: './user-content-input-form.component.html',
  styleUrls: ['./user-content-input-form.component.css'],
})
export class UserContentInputFormComponent implements OnInit {
  @Input('type') type;
  @Input('user') user;
  @Output() newPost = new EventEmitter();

  imagePreview: Array<any>;
  selectedFile;
  placeholderText;
  image: SafeUrl;
  textValue = '';
  constructor(
    private httpClient: HttpClient,
    private sanitizer: DomSanitizer,
    private postService: PostService
  ) {}

  submitForm(input) {
    if (this.selectedFile == null) {
      let post = {
        username: this.user.username,
        post: this.textValue,
      };
      this.postService.addPost(post).subscribe((x) => this.newPost.emit(x));
      window.alert('successfully submitted');
      this.textValue = '';
      this.imagePreview = null;
    } else {
      let arr = this.selectedFile.split(',');
      let post = {
        username: this.user.username,
        post: this.textValue,
        image: arr[1],
      };
      this.postService.addPost(post).subscribe((x) => this.newPost.emit(x));
      window.alert('successfully submitted');
      this.textValue = '';
      this.imagePreview = null;
    }
  }
  ngOnInit(): void {
    this.placeholderText = `Please enter your ${this.type}`;
    this.getImage();
  }
  changeFile(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
      console.log(this.textValue);
    });
  }
  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile.name);
    if (event.target.value) {
      const file = event.target.files[0];
      this.changeFile(file).then((base64: string): any => {
        this.imagePreview = [base64];
        this.selectedFile = base64;
      });
    } else alert('Nothing');
  }
  onUpload() {
    let username = this.user.username;
    let s = this.selectedFile.name;
    let j = s.split('.');
    let name = username + '.png';
    //let newFile : File = new File(, name , {type : this.selectedFile.type});
    let imageData = new FormData();
    imageData.append('imageFile', this.selectedFile, name);
    this.httpClient
      .post(
        'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/user/updateprofilepic',
        imageData
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
