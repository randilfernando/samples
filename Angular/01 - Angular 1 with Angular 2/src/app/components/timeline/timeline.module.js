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