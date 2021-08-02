import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {BankTransfer} from "../data/bankTransfer";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-new-banktransfer',
  templateUrl: './new-banktransfer.component.html',
  styleUrls: ['./new-banktransfer.component.scss']
})
export class NewBanktransferComponent implements OnInit {

  authStatus: boolean;
  bankTransfer: BankTransfer;
  userEmail: string;

  constructor(private authService: AuthService,
              private router: Router) {
    this.bankTransfer = new BankTransfer();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onSubmit(form: NgForm) {
    this.bankTransfer.internalAccountId = window.history.state.internalAccountIdData;
    this.bankTransfer.externalAccountIBAN = window.history.state.externalAccountIBANData;
    this.userEmail = window.history.state.emailData;
    this.authService.saveBankTransfer(this.bankTransfer).subscribe( result => {
      this.router.navigate(['dashBoard'], {state: {data: this.userEmail}});
    });
  }

}
