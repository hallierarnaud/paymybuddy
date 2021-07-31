import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {Contact} from "../data/contact";
import {UserAccount} from "../data/userAccount";
import {MatDialog} from "@angular/material/dialog";
import {ModalErrorComponent} from "../modal-error/modal-error.component";

export interface DialogData {
  errorNumber: string;
  errorMessage: string;
}

@Component({
  selector: 'app-new-connection',
  templateUrl: './new-connection.component.html',
  styleUrls: ['./new-connection.component.scss']
})
export class NewConnectionComponent implements OnInit {

  //possible d'utiliser un formulaire réactif pour pouvoir rajouter des cases à volonté pour ajouter des contacts

  authStatus: boolean;
  contact: Contact;
  userAccount: UserAccount = {} as UserAccount;

  constructor(private router: Router,
              private authService: AuthService,
              private dialog: MatDialog) {
    this.contact = new Contact();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onSubmit(form: NgForm) {
    const currentUserId = window.history.state;
    this.contact.userId = currentUserId.transferredData.data;
    this.authService.getUserAccountByEmail(this.contact.contactEmail).subscribe(userAccount => {
      this.userAccount = userAccount;
      this.contact.contactId = this.userAccount.userId;
      this.authService.saveConnection(this.contact).subscribe(result => {
        this.router.navigate(['connectionList'], {state: {data: this.contact.userId}});
      },error => {
        this.errorAlreadyExist();
      });
    },error => {
      this.errorUnknownAddress();
    });
  }

  errorAlreadyExist() {
    this.dialog.open(ModalErrorComponent, {
      data: {
        errorNumber: '422',
        errorMessage: 'This contact already exists !'
      }
    });
  }

  errorUnknownAddress() {
    this.dialog.open(ModalErrorComponent, {
      data: {
        errorNumber: '404',
        errorMessage: 'This contact doesn\'t exist !'
      }
    });
  }

}
