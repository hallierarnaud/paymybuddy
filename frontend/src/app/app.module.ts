import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { RouterModule, Routes } from "@angular/router";
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { HttpClientModule } from '@angular/common/http';
import { SigninComponent } from './signin/signin.component';
import { HeaderComponent } from "./header/header.component";
import { AuthService } from "./services/auth.service";
import { AuthGuardService } from "./services/auth-guard.service";
import { UseraccountListComponent } from './useraccount-list/useraccount-list.component';
import { UseraccountFormComponent } from './useraccount-form/useraccount-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ConnectionComponent } from './connection/connection.component';

const appRoutes: Routes = [
  { path: 'userAccounts', canActivate:[AuthGuardService], component: UseraccountListComponent},
  { path: 'addUserAccount', component: UseraccountFormComponent},
  { path: 'signin', component: SigninComponent},
  { path: 'dashBoard', canActivate:[AuthGuardService], component: DashboardComponent},
  { path: 'addConnection', component: ConnectionComponent},
  { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: '/not-found' }
]

@NgModule({
  declarations: [
    AppComponent,
    FourOhFourComponent,
    SigninComponent,
    HeaderComponent,
    UseraccountListComponent,
    UseraccountFormComponent,
    DashboardComponent,
    ConnectionComponent
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
