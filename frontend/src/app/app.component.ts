import {Component} from '@angular/core';
import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor() {
    var firebaseConfig = {
      apiKey: "AIzaSyCSXS5nh2rAXlT_uX41iwT5ret_vYb-3ds",
      authDomain: "paymybuddy.firebaseapp.com",
      projectId: "paymybuddy",
      storageBucket: "paymybuddy.appspot.com",
      messagingSenderId: "535043600941",
      appId: "1:535043600941:web:d7686b81973beb948dbea4"
    };
    firebase.initializeApp(firebaseConfig);
  }

}
