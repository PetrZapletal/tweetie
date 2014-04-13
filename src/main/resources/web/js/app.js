'use strict';


dependencies = [
    'ngRoute',
    'ui.bootstrap',
    'tweetie.filters',
    'tweetie.services',
    'tweetie.controllers',
    'tweetie.directives',
    'tweetie.common',
    'tweetie.routeConfig'
]

var tweetie = angular.module('tweetie', dependencies)

tweetie.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'partials/home.html',
        controller: 'HomeCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);

