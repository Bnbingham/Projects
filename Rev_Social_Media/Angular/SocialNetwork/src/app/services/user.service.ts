import { ResetPassword } from './../interfaces/ResetPassword';
import { UserLogin } from './../interfaces/UserLogin';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/User';
import { NewUser } from '../interfaces/NewUser';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  url = 'http://ec2-3-133-98-43.us-east-2.compute.amazonaws.com:9000/user';
  constructor(private httpClient: HttpClient) {}

  searchForUser(input) {
    return this.httpClient.post(this.url + '/usersearch', input);
  }
  ResetPassword(input): Observable<User> {
    return this.httpClient.post<User>(this.url + '/changepass', input);
  }
  validateUserLogin(input: UserLogin): Observable<User> {
    return this.httpClient.post<User>(this.url + '/login', input);
  }
  addUser(input: NewUser): Observable<User> {
    return this.httpClient.post<User>(this.url + '/adduser', input);
  }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.url + '/all');
  }

  getSampleUser() {
    return {
      id: 0,
      username: '-',
      password: '-',
      firstName: '-',
      lastName: '',
      picLink: null,
      email: '-',
      aboutMe: '-',
    };
  }
  getSampleUsers() {
    return [
      {
        username: 'Maurie',
        lastName: 'G',
        email: 'email@email.com',
        aboutMe: 'Sherlock stuff',
        picLink:
          'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
      },
      {
        username: 'Andres',
        lastName: 'A',
        email: 'email@email.com',
        aboutMe: 'Guitar stuff',
        picLink:
          'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
      },
      {
        username: 'Nathan',
        lastName: 'A',
        email: 'email@email.com',
        aboutMe: 'Climbing steep overhangs',
        picLink:
          'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
      },
      {
        username: 'Brad',
        lastName: 'B',
        email: 'email@email.com',
        aboutMe: 'not much',
        picLink:
          'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
      },
    ];
  }
}
