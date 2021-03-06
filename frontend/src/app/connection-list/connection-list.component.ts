import { Component, OnInit } from '@angular/core';
import {Contact} from "../data/contact";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-connection',
  templateUrl: './connection-list.component.html',
  styleUrls: ['./connection-list.component.scss']
})

/**
 * a class to get the logged user's contact list
 */
export class ConnectionListComponent implements OnInit {

  contacts: Contact[];
  userId = window.history.state;

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.authService.getContactsByUserId(this.userId.data).subscribe(data => {
      this.contacts = data;
    });
  }

  onCreateConnection() {
    this.router.navigate(['addConnection'], {state: {transferredData: this.userId}});
  }

}
