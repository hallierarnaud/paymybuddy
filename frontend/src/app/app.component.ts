import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title: string;
  subtitle: string;

  constructor() {
    this.title = 'PayMyBuddy';
    this.subtitle = 'Spring Boot - Angular Application';
  }

}
