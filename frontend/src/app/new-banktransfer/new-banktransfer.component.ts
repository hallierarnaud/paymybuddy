import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {BankTransfer} from "../data/bankTransfer";
import {NgForm} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {ModalErrorComponent} from "../modal-error/modal-error.component";

export interface DialogData {
  errorNumber: string;
  errorMessage: string;
}

@Component({
  selector: 'app-new-banktransfer',
  templateUrl: './new-banktransfer.component.html',
  styleUrls: ['./new-banktransfer.component.scss']
})

/**
 * a class to create a bank transfer from or to an external account
 */
export class NewBanktransferComponent implements OnInit {

  authStatus: boolean;
  bankTransfer: BankTransfer;
  userEmail: string;

  constructor(private authService: AuthService,
              private router: Router,
              private dialog: MatDialog) {
    this.bankTransfer = new BankTransfer();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  /**
   * @param form a form to input bank transfer's information
   */
  onSubmit(form: NgForm) {
    this.bankTransfer.internalAccountId = window.history.state.internalAccountIdData;
    this.bankTransfer.externalAccountId = window.history.state.externalAccountIdData;
    this.userEmail = window.history.state.emailData;
    this.authService.saveBankTransfer(this.bankTransfer).subscribe( result => {
      this.router.navigate(['dashBoard'], {state: {data: this.userEmail}});
    }, error => {
      this.errorInsufficientAmount();
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
