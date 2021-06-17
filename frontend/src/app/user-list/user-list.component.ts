import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../models/User.model";
import {Subscription} from "rxjs";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit, OnDestroy {

  // @ts-ignore
  authStatus: boolean;
  // @ts-ignore
  users: User[];
  // @ts-ignore
  userSubscription: Subscription;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.authStatus = this.userService.isAuth;
    this.userSubscription = this.userService.userSubject.subscribe(
      (users: User[]) => {
        this.users = users;
      }
    );
    this.userService.emitUsers();
  }

  onSignIn() {
    this.userService.signIn().then(
      () => {
        this.authStatus = this.userService.isAuth;
        this.router.navigate(['home']);
      }
    )
  }

  onSignOut() {
    this.userService.signOut();
    this.authStatus = this.userService.isAuth;
  }

  ngOnDestroy() {
    this.userSubscription.unsubscribe();
  }

}
