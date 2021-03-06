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

/**
 * a class to present the user's dashboard
 */
export class DashboardComponent implements OnInit {

  authStatus: boolean;
  userId: number;
  internalAccountId: number;
  externalAccountId: number;
  email: string;

  userAccount: UserAccount = {} as UserAccount;

  constructor(private authService: AuthService,
              private router: Router,
              private datePipe: DatePipe) {
  }

  /**
   * a method to present the logged user account information
   */
  ngOnInit() {
    this.authStatus = this.authService.isAuth;
    const loginEmail = window.history.state;
    this.authService.getUserAccountByEmail(loginEmail.data).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.userAccount.birthdate = this.datePipe.transform(this.userAccount.birthdate, 'dd/MM/yyyy');
    })
  }

  /**
   * a method to log out
   */
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

  onBankTransfer() {
    this.internalAccountId = this.userAccount.internalAccountId;
    this.externalAccountId = this.userAccount.externalAccountId;
    this.email = this.userAccount.email;
    this.router.navigate(['addBankTransfer'], {state: {internalAccountIdData: this.internalAccountId, externalAccountIdData: this.externalAccountId, emailData: this.email}});
  }

}
