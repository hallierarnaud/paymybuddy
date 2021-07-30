import { Component, OnInit } from '@angular/core';
import {Transaction} from "../data/transaction";
import {UserAccount} from "../data/userAccount";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.scss']
})
export class NewTransactionComponent implements OnInit {

  authStatus: boolean;
  transaction: Transaction;
  userAccount: UserAccount = {} as UserAccount;

  constructor(private router: Router,
              private authService: AuthService) {
    this.transaction = new Transaction();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onSubmit(form: NgForm) {
    const currentSenderInternalAccountId = window.history.state;
    this.transaction.senderInternalAccountId = currentSenderInternalAccountId.transferredData.data;
    this.authService.getUserAccountByEmail(this.transaction.recipientInternalAccountEmail).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.transaction.recipientInternalAccountId = this.userAccount.internalAccountId;
      this.authService.saveTransaction(this.transaction).subscribe(result => {
        this.router.navigate(['transactionList'], {state: {data: this.transaction.senderInternalAccountId}});
      });
    });
  }

}
