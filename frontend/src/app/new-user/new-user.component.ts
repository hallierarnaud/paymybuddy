import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserService } from "../services/user.service";
import { Router } from "@angular/router";
import { User } from "../models/User.model";

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.scss']
})
export class NewUserComponent implements OnInit {

  // @ts-ignore
  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.userForm = this.formBuilder.group( {
      Email: ['', Validators.required],
      Password: ['', Validators.required]
    });
  }

  onSubmitForm() {
    const formValue = this.userForm.value;
    const newUser = new User(
      formValue['Email'],
      formValue['Password']
    );
    this.userService.addUser(newUser);
    this.router.navigate(['/users']);
  }

  onSave() {
    this.userService.saveUsersToServer();
  }

  onFetch() {
    this.userService.getUsersFromServer();
  }

}
