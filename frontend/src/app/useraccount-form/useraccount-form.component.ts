import { Component, OnInit } from '@angular/core';
import {UserAccount} from "../data/userAccount";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-useraccount-form',
  templateUrl: './useraccount-form.component.html',
  styleUrls: ['./useraccount-form.component.scss']
})
export class UseraccountFormComponent implements OnInit {

  userAccount : UserAccount;

  constructor(private router: Router, private authService: AuthService) {
    this.userAccount = new UserAccount();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authService.saveUserAccount(this.userAccount).subscribe(result => this.gotoUserAccountList());
  }

  gotoUserAccountList() {
    this.router.navigate(['/userAccounts']);
  }

}
