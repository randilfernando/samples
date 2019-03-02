import {Component} from '@angular/core';

interface Employee {
  firstName: string;
  lastName: string;
  age: number;
  gender: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  employees: Employee[] = [
    {firstName: 'Randil', lastName: 'Fernando', age: 23, gender: 'M'},
    {firstName: 'Wishwa', lastName: 'Wijerathna', age: 23, gender: 'M'},
    {firstName: 'Lalindu', lastName: 'Rajapaksha', age: 21, gender: 'M'},
  ];

  contentChange(employee: Employee, attribute: string, event: any) {
    employee[attribute] = event.target.textContent;
  }

  send() {
    console.log(this.employees);
  }
}
