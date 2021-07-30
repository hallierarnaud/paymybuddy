import { Component, OnInit } from '@angular/core';
import {Transaction} from "../data/transaction";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.scss']
})
export class TransactionListComponent implements OnInit {

  transactions: Transaction[];
  internalAccountId = window.history.state;

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.authService.getTransactionsByInternalAccountId(this.internalAccountId.data).subscribe(data => {
      this.transactions = data;
    });
  }

}
