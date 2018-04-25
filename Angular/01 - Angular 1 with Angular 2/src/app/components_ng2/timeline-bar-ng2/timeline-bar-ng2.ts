import {Component} from 'angular2/core';

@Component({
    selector: 'timeline-bar-ng2',
    template: `
        <h3>This is from a angular 2 component</h3>
        <p>{{message}}</p>
    `
})

export class TimelineBarNg2 {
    private message = 'This is a message from angular 2';

    constructor() {
        
    }
}