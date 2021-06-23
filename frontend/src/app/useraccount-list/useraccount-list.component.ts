import { Component, OnInit } from '@angular/core';
import {UserAccount} from "../data/userAccount";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-useraccount-list',
  templateUrl: './useraccount-list.component.html',
  styleUrls: ['./useraccount-list.component.scss']
})
export class UseraccountListComponent implements OnInit {

  userAccounts: UserAccount[];

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.getUserAccounts().subscribe(data => {
      this.userAccounts = data;
    });
  }

}
