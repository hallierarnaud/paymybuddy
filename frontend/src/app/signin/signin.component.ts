import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {Login} from "../data/login";
import {UserAccount} from "../data/userAccount";
import {ModalErrorComponent} from "../modal-error/modal-error.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  authStatus: boolean;
  login: Login;
  email: string;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private dialog: MatDialog) {
    this.login = new Login();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onCheckLogin(form: NgForm) {
    this.authService.checkLogin(this.login).subscribe(userAccount => {
      this.email = userAccount.email;
      this.authService.signIn();
      this.authStatus = this.authService.isAuth;
      this.router.navigate(['/dashBoard'], {state: {data: this.email}});
      }, error => {
        this.errorUnknownLogin();
      }
    );
  }

  errorUnknownLogin() {
    this.dialog.open(ModalErrorComponent, {
      data: {
        errorNumber: '404',
        errorMessage: 'email ' + this.login.email + ' doesn\'t exist or password is false !'
      }
    });
  }

}
