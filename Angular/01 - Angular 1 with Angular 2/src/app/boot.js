System.register(['angular2/upgrade', './components_ng2/timeline-bar-ng2/timeline-bar-ng2'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var upgrade_1, timeline_bar_ng2_1;
    var adapter, app;
    return {
        setters:[
            function (upgrade_1_1) {
                upgrade_1 = upgrade_1_1;
            },
            function (timeline_bar_ng2_1_1) {
                timeline_bar_ng2_1 = timeline_bar_ng2_1_1;
            }],
        execute: function() {
            adapter = new upgrade_1.UpgradeAdapter();
            app = angular.module('timelineApp');
            app.directive('timelineBarNg2', adapter.downgradeNg2Component(timeline_bar_ng2_1.TimelineBarNg2));
            adapter.bootstrap(document.body, ['timelineApp']);
        }
    }
});
//# sourceMappingURL=boot.js.map