import { Component, OnInit } from '@angular/core';
import {UserAccount} from "../data/userAccount";
import {AuthService} from "../services/auth.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DatePipe]
})
export class DashboardComponent implements OnInit {

  userAccount: UserAccount = {} as UserAccount;

  constructor(private authService: AuthService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    /*this.authService.getUserAccounts().subscribe(data => {
      this.userAccounts = data;
    })*/
    const loginEmail = window.history.state;
    console.log('loginEmail :', loginEmail.data);
    this.authService.getUserAccountByLogin(loginEmail.data).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.userAccount.birthdate = this.datePipe.transform(this.userAccount.birthdate, 'dd/MM/yyyy');
      console.log('userAccount: ', userAccount);
    })
  }

}
