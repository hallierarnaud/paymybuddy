import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-component1-view',
  templateUrl: './component1-view.component.html',
  styleUrls: ['./component1-view.component.scss']
})
export class Component1ViewComponent {

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
