import { Component, OnInit } from '@angular/core';
import {Transaction} from "../data/transaction";
import {UserAccount} from "../data/userAccount";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {NgForm} from "@angular/forms";
import {Contact} from "../data/contact";
import {MatDialog} from "@angular/material/dialog";
import {ModalErrorComponent} from "../modal-error/modal-error.component";

export interface DialogData {
  errorNumber: string;
  errorMessage: string;
}

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.scss']
})

/**
 * a class to create a transaction
 */
export class NewTransactionComponent implements OnInit {

  authStatus: boolean;
  transaction: Transaction;
  userAccount: UserAccount = {} as UserAccount;
  contacts: Contact[];
  emails: string[] = [];
  currentSenderInternalAccountId = window.history.state;

  constructor(private router: Router,
              private authService: AuthService,
              private dialog:MatDialog) {
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

  /**
   * @param form a form to input transaction's information
   */
  onSubmit(form: NgForm) {
    this.transaction.senderInternalAccountId = this.currentSenderInternalAccountId.transferredData.data;
    this.authService.getUserAccountByEmail(this.transaction.recipientInternalAccountEmail).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.transaction.recipientInternalAccountId = this.userAccount.internalAccountId;
      this.authService.saveTransaction(this.transaction).subscribe(result => {
        this.router.navigate(['transactionList'], {state: {data: this.transaction.senderInternalAccountId}});
      }, error => {
        this.errorInsufficientAmount();
      });
    });
  }

  /**
   * a popup to inform that the balance is insufficient to perform the transaction
   */
  errorInsufficientAmount() {
    this.dialog.open(ModalErrorComponent, {
      data: {
        errorNumber: '422',
        errorMessage: 'Sorry but your balance is less than the wished amount to transfer.'
      }
    });
  }

}
