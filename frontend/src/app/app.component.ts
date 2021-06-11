import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  enabledAuthentication = false;

  lastUpdate: Promise<Date> = new Promise(
    (resolve, reject) => {
      const date = new Date();
      setTimeout(
        () => {
          resolve(date);
        }, 2000
      );
    }
  );

  email = 'hsimpson@email.fr';
  lastName = 'Simpson';
  firstName = 'Homer';

  constructor() {
    setTimeout(
      () => {
        this.enabledAuthentication = true;
      }, 4000
    );
  }
  onAuthenticationEnabled() {
    console.log('Vous pouvez vous authentifier !');
  }
}
