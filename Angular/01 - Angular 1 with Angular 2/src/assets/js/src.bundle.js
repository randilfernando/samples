(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var routes = require('./app.routing');

require('./components/timeline/timeline.module');

var app = angular.module('timelineApp', [
    'ngRoute',
    'timelineApp.timelineModule'
]);

app.config(['$routeProvider', routes]);
},{"./app.routing":2,"./components/timeline/timeline.module":7}],2:[function(require,module,exports){
module.exports = function ($routeProvider) {
    $routeProvider
        .when("/timeline", {
            templateUrl: "/src/app/components/timeline/timeline.view.html",
            controller: "timelineController"
        })
        .otherwise({
            redirectTo: '/'
        });
};
},{}],3:[function(require,module,exports){
module.exports = function () {
    return {
        restrict: 'E',
        replace: false,
        scope: false,
        templateUrl: 'src/app/components/timeline/timeline-bar/timeline-bar.html'
    };
};
},{}],4:[function(require,module,exports){
module.exports = function () {
    return {
        restrict: 'E',
        replace: false,
        scope: false,
        templateUrl: 'src/app/components/timeline/timeline-event/timeline-event.html'
    };
};
},{}],5:[function(require,module,exports){
module.exports = function () {
    return {
        restrict: 'E',
        replace: false,
        scope: false,
        templateUrl: 'src/app/components/timeline/timeline-gap/timeline-gap.html'
    };
};
},{}],6:[function(require,module,exports){
module.exports = function ($scope) {
    $scope.test = {
        message: 'Hello world'
    };
};
},{}],7:[function(require,module,exports){
var timelineController = require('./timeline.controller');
var timelineBarDirective = require('./timeline-bar/timeline-bar');
var timelineEventDirective = require('./timeline-event/timeline-event');
var timelineGapDirective = require('./timeline-gap/timeline-gap');

var timelineModule = angular.module('timelineApp.timelineModule', []);

timelineModule
    .controller('timelineController', ['$scope', timelineController])
    .directive('timelineBar', timelineBarDirective)
    .directive('timelineEvent', timelineEventDirective)
    .directive('timelineGap', timelineGapDirective);
},{"./timeline-bar/timeline-bar":3,"./timeline-event/timeline-event":4,"./timeline-gap/timeline-gap":5,"./timeline.controller":6}]},{},[1]);
