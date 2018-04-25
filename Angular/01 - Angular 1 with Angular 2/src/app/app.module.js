var routes = require('./app.routing');

require('./components/timeline/timeline.module');

var app = angular.module('timelineApp', [
    'ngRoute',
    'timelineApp.timelineModule'
]);

app.config(['$routeProvider', routes]);