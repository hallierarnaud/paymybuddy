import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { RouterModule, Routes } from "@angular/router";
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { HttpClientModule } from '@angular/common/http';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HeaderComponent } from "./header/header.component";
import { AuthService } from "./services/auth.service";
import { AuthGuardService } from "./services/auth-guard.service";

const appRoutes: Routes = [
  { path: 'signup', component: SignupComponent},
  { path: 'signin', component: SigninComponent},
  { path: 'not-found', component: FourOhFourComponent },
  { path: '', redirectTo: '/not-found', pathMatch: 'full' },
  { path: '**', redirectTo: '/not-found' }
]

@NgModule({
  declarations: [
    AppComponent,
    FourOhFourComponent,
    SigninComponent,
    SignupComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    AuthService,
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
