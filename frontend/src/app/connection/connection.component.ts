import { Component, OnInit } from '@angular/core';
import {Contact} from "../data/contact";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  //TODO: penser à protéger ce path
  //TODO: utiliser un formulaire réactif pour pouvoir ajouter des cases à volonté pour ajouter des contacts

  contacts: Contact[];

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    const userId = window.history.state;
    this.authService.getContactsByUserId(userId.data).subscribe(data => {
      this.contacts = data;
    })
  }

  onCreateConnection() {
    this.router.navigate(['newConnection']);
  }

}
