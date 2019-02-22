import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  employees: { firstName: string, lastName: string, age: number }[] = [
    {firstName: 'Randil', lastName: 'Fernando', age: 23},
    {firstName: 'Wishwa', lastName: 'Wijerathna', age: 23},
    {firstName: 'Lalindu', lastName: 'Rajapaksha', age: 21},
  ];

  contentChange(employee: { firstName: string, lastName: string, age: number }, attribute: string, newValue: any) {
    employee[attribute] = newValue;
  }
}
