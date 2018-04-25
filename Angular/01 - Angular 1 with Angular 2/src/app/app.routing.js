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