import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  // ajout d'une variable pour le statut d'authentification
  authStatus: boolean;

  // formulaire réactif
  signInForm: FormGroup;
  errorMessage: string;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
    // mise à jour de la variable d'authentification
    this.authStatus = this.authService.isAuth;
    // formulaire réactif
    this.initForm();
  }

  // ajout du système d'authentification et de desauthentification
  onSignIn() {
    this.authService.signIn();
    this.authStatus = this.authService.isAuth;
    this.router.navigate(['userAccounts']);
  }

  onSignOut() {
    this.authService.signOut();
    this.authStatus = this.authService.isAuth;
  }

  // formulaire réactif
  initForm() {
    this.signInForm = this.formBuilder.group( {
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]]
    });
  }

  // formulaire réactif
  /*onSubmit() {
    // @ts-ignore
    const email = this.signInForm.get('email').value;
    // @ts-ignore
    const password = this.signInForm.get('password').value;
    this.authService.signInUser(email, password).then(
      () => {
        this.router.navigate(['/not-found']);
      },
      (error) => {
        this.errorMessage = error;
      }
    );
  }*/

  // ajout de la méthode pour récupérer un useraccount par son email et password
  checkUserAccount() {
    // @ts-ignore
    const email = this.signInForm.get('email').value;
    // @ts-ignore
    const password = this.signInForm.get('password').value;
    this.authService.checkUserAccount(email,password).subscribe(userAccount => {
        console.log('userAccount: ', userAccount);
      this.authService.signIn();
      this.authStatus = this.authService.isAuth;
      }, error => {
        console.log('error', error);
      }
    )
  }

}
