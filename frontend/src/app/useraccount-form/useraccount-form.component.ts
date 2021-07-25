import { Component, OnInit } from '@angular/core';
import {UserAccount} from "../data/userAccount";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-useraccount-form',
  templateUrl: './useraccount-form.component.html',
  styleUrls: ['./useraccount-form.component.scss']
})
export class UseraccountFormComponent implements OnInit {

  authStatus: boolean;
  userAccount : UserAccount;
  email: string;

  constructor(private router: Router,
              private authService: AuthService) {
    this.userAccount = new UserAccount();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onSubmit(form: NgForm) {
    this.authService.saveUserAccount(this.userAccount).subscribe(result => {
      this.email = this.userAccount.email;
      this.authService.signIn();
      this.authStatus = this.authService.isAuth;
      this.router.navigate(['/dashBoard'], {state: {data: this.email}});
      }
    )
  }

}
