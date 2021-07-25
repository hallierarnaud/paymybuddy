import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {Contact} from "../data/contact";

@Component({
  selector: 'app-new-connection',
  templateUrl: './new-connection.component.html',
  styleUrls: ['./new-connection.component.scss']
})
export class NewConnectionComponent implements OnInit {

  authStatus: boolean;
  contact: Contact;
  userId: number;
  contactId: number;


  constructor(private router: Router,
              private authService: AuthService) {
    this.contact = new Contact();
  }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onSubmit(form: NgForm) {
    this.authService.saveConnection(this.contact).subscribe(result => {
      this.userId = this.contact.userId;
      this.contactId = this.contact.contactId;
      this.router.navigate(['addConnection'], {state: {data: this.contact.userId}});
    })
  }

}
