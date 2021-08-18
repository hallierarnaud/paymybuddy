import { Component, OnInit } from '@angular/core';
import {UserAccount} from "../data/userAccount";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {NgForm} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {ModalErrorComponent} from "../modal-error/modal-error.component";

export interface DialogData {
  errorNumber: string;
  errorMessage: string;
}

@Component({
  selector: 'app-useraccount-form',
  templateUrl: './useraccount-form.component.html',
  styleUrls: ['./useraccount-form.component.scss']
})

/**
 * a class to create a user account
 */
export class UseraccountFormComponent implements OnInit {

  authStatus: boolean;
  userAccount : UserAccount;
  email: string;

  constructor(private router: Router,
              private authService: AuthService,
              private dialog:MatDialog) {
    this.userAccount = new UserAccount();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  /**
   * @param form a form to input user account's data
   */
  onSubmit(form: NgForm) {
    this.authService.saveUserAccount(this.userAccount).subscribe(result => {
      this.email = this.userAccount.email;
      this.authService.signIn();
      this.authStatus = this.authService.isAuth;
      this.router.navigate(['/dashBoard'], {state: {data: this.email}});
    }, error => {
      this.errorAlreadyExist();
    });
  }

  /**
   * a popup to inform that the input email already exists
   */
  errorAlreadyExist() {
    this.dialog.open(ModalErrorComponent, {
      data: {
        errorNumber: '422',
        errorMessage: 'This email already exists in PayMyBuddy application, try to login !'
      }
    });
  }

}
