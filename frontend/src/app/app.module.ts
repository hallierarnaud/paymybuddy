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
import { UseraccountFormComponent } from './useraccount-form/useraccount-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ConnectionListComponent } from './connection-list/connection-list.component';
import { NewConnectionComponent } from './new-connection/new-connection.component';

const appRoutes: Routes = [
  { path: 'addUserAccount', component: UseraccountFormComponent},
  { path: 'signin', component: SigninComponent},
  { path: 'dashBoard', canActivate:[AuthGuardService], component: DashboardComponent},
  { path: 'connectionList', canActivate:[AuthGuardService], component: ConnectionListComponent},
  { path: 'addConnection', canActivate:[AuthGuardService], component: NewConnectionComponent},
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
    UseraccountFormComponent,
    DashboardComponent,
    ConnectionListComponent,
    NewConnectionComponent
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
