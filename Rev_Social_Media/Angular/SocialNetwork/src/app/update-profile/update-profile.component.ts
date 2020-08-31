import { HttpClient } from '@angular/common/http';
import { UserService } from './../services/user.service';
import { ImageUploadService } from './../services/image-upload.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css'],
})
export class UpdateProfileComponent implements OnInit {
  @Input('user') user;
  @Output() newUser = new EventEmitter();
  edit = false;
  imagePreview;
  firstNameValue = '';
  lastNameValue = '';
  bioValue = '';
  ngOnInit() {
    this.getImage();
  }
  constructor(
    private httpClient: HttpClient,
    private sanitizer: DomSanitizer
  ) {}

  selectedFile: File;
  image: SafeUrl;

  updateUserInfo() {
    this.user.firstName = (document.getElementById(
      'fName'
    ) as HTMLInputElement).value;
    this.user.lastName = (document.getElementById(
      'lName'
    ) as HTMLInputElement).value;
    this.user.bio = (document.getElementById(
      'thebio'
    ) as HTMLInputElement).value;
    this.httpClient
      .post(
        'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/user/adduser',
        this.user
      )
      .subscribe((x) => {
        console.log(x);
        window.alert('successfully updated');
        this.newUser.emit(this.user);
        this.firstNameValue = '';
        this.lastNameValue = '';
        this.bioValue = '';
      });
  }

  toggleEdit() {
    this.edit = !this.edit;
  }
  changeFile(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });
  }
  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile.name);
    if (event.target.value) {
      const file = event.target.files[0];
      this.changeFile(file).then((base64: string): any => {
        this.imagePreview = [base64];
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
          window.alert('successfully updated');
          this.user.picLink = data;
          this.newUser.emit(this.user);
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
