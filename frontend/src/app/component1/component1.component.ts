import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-component1',
  templateUrl: './component1.component.html',
  styleUrls: ['./component1.component.scss']
})
export class Component1Component implements OnInit {

  // @ts-ignore
  @Input() dataInput: string;
  // @ts-ignore
  @Input() dataState: string;

  constructor() { }

  ngOnInit(): void {
  }

  getStatus() {
    return this.dataState;
  }

  // @ts-ignore
  getColor() {
    if (this.dataState === 'obligatoire') {
      return 'red';
    } else if (this.dataState === 'facultatif') {
      return 'green';
    }
  }

}
