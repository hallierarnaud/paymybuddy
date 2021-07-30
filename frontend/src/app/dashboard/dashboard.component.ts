import { Component, OnInit } from '@angular/core';
import {UserAccount} from "../data/userAccount";
import {AuthService} from "../services/auth.service";
import {DatePipe} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DatePipe]
})
export class DashboardComponent implements OnInit {

  authStatus: boolean;
  userId: number;
  internalAccountId: number;

  userAccount: UserAccount = {} as UserAccount;

  constructor(private authService: AuthService,
              private router: Router,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.authStatus = this.authService.isAuth;
    const loginEmail = window.history.state;
    this.authService.getUserAccountByEmail(loginEmail.data).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.userAccount.birthdate = this.datePipe.transform(this.userAccount.birthdate, 'dd/MM/yyyy');
    })
  }

  onSignOut() {
    this.authService.signOut();
    this.authStatus = this.authService.isAuth;
    this.router.navigate(['signin']);
  }

  onAddConnection() {
    this.userId = this.userAccount.userId;
    this.router.navigate(['connectionList'], {state: {data: this.userId}});
  }

  onSendMoney() {
    this.internalAccountId = this.userAccount.internalAccountId;
    this.router.navigate(['transactionList'], {state: {data: this.internalAccountId}});
  }

}
