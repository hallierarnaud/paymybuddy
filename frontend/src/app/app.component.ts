import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  enabledAuthentication = false;
  constructor() {
    setTimeout(
      () => {
        this.enabledAuthentication = true;
      }, 4000
    );
  }
}
