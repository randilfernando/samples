import {UpgradeAdapter} from 'angular2/upgrade';
import {TimelineBarNg2} from './components_ng2/timeline-bar-ng2/timeline-bar-ng2';

declare var angular:any;

var adapter = new UpgradeAdapter();

var app = angular.module('timelineApp');

app.directive('timelineBarNg2', adapter.downgradeNg2Component(TimelineBarNg2));

adapter.bootstrap(document.body, ['timelineApp']);