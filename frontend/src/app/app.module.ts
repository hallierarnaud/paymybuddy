import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { Component1Component } from './component1/component1.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { UserListComponent } from './user-list/user-list.component';
import { UserService } from "./services/user.service";
import { RouterModule, Routes } from "@angular/router";
import { Component1ViewComponent } from './component1-view/component1-view.component';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { AuthGuard } from "./services/auth-guard.service";
import { EditUserComponent } from './edit-user/edit-user.component';
import { NewUserComponent } from './new-user/new-user.component';
import { HttpClientModule } from '@angular/common/http';

const appRoutes: Routes = [
  { path: 'home', canActivate: [AuthGuard], component: Component1ViewComponent },
  { path: 'users', component: UserListComponent },
  { path: 'edit', canActivate: [AuthGuard], component: EditUserComponent },
  { path: 'new-user', component: NewUserComponent},
  { path: '', component: Component1ViewComponent },
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: '/not-found' }
]

@NgModule({
  declarations: [
    AppComponent,
    Component1Component,
    UserListComponent,
    Component1ViewComponent,
    FourOhFourComponent,
    EditUserComponent,
    NewUserComponent
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
    UserService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
