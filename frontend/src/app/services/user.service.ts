import {User} from "../models/User.model";
import {Subject} from "rxjs/Subject";
import {HttpClient} from "@angular/common/http"
import {Injectable} from "@angular/core";

@Injectable()
export class UserService {

  isAuth = false;

  signIn() {
    return new Promise(
      (resolve, reject) => {
        setTimeout(
          () => {
            this.isAuth = true;
            resolve(true);
          }, 2000
        );
      }
    );
  }

  signOut() {
    this.isAuth = false;
  }

  private users: User[] = [
    {
      Email: 'homer@simpson.fr',
      Password: 'homersim'
    }
  ];
  userSubject = new Subject<User[]>();

  constructor(private httpClient: HttpClient) {
  }

  emitUsers() {
    this.userSubject.next(this.users.slice());
  }

  addUser(user: User) {
    this.users.push(user);
    this.emitUsers();
  }

  saveUsersToServer() {
    this.httpClient
      .post('jdbc:mysql://localhost:3306/paymybuddy/logins', this.users)
      .subscribe(
        () => {
          console.log('ok');
        },
        (error) => {
          console.log('erreur' + error);
        }
      )
  }

  getUsersFromServer() {
    this.httpClient
      .get<any[]>('jdbc:mysql://localhost:3306/paymybuddy/logins')
      .subscribe(
        (response) => {
          this.users = response;
          this.emitUsers();
        },
        (error) => {
          console.log('erreur' + error);
        }
      );
  }

}
