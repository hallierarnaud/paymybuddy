import { Component, OnInit } from '@angular/core';
import {Transaction} from "../data/transaction";
import {UserAccount} from "../data/userAccount";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {NgForm} from "@angular/forms";
import {Contact} from "../data/contact";

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.scss']
})
export class NewTransactionComponent implements OnInit {

  authStatus: boolean;
  transaction: Transaction;
  userAccount: UserAccount = {} as UserAccount;
  contacts: Contact[];
  emails: string[] = [];
  currentSenderInternalAccountId = window.history.state;

  constructor(private router: Router,
              private authService: AuthService) {
    this.transaction = new Transaction();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
    this.authService.getContactsByUserId(this.currentSenderInternalAccountId.transferredData.data).subscribe(data => {
      this.contacts = data;
      for (const index in this.contacts) {
        this.emails.push(this.contacts[index].contactEmail);
      }
    });
  }

  onSubmit(form: NgForm) {
    this.transaction.senderInternalAccountId = this.currentSenderInternalAccountId.transferredData.data;
    this.authService.getUserAccountByEmail(this.transaction.recipientInternalAccountEmail).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.transaction.recipientInternalAccountId = this.userAccount.internalAccountId;
      this.authService.saveTransaction(this.transaction).subscribe(result => {
        this.router.navigate(['transactionList'], {state: {data: this.transaction.senderInternalAccountId}});
      });
    });
  }

}
