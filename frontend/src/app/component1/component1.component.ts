import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-component1',
  templateUrl: './component1.component.html',
  styleUrls: ['./component1.component.scss']
})
export class Component1Component implements OnInit {

  dataInput: string = 'adresse email';
  dataState: string = 'obligatoire';

  constructor() { }

  ngOnInit(): void {
  }

  getStatus() {
    return this.dataState;
  }

}
