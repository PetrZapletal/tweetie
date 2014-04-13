'use strict';

var tweetieControllers =
  angular.module(
    'tweetie.controllers',['tweetie.services']
  );

tweetieControllers.controller('HomeCtrl', ['$scope', 'Tweet', function($scope, Tweet) {

  $scope.messages = [];

  $scope.getTweets = function() {
    var tweets = Tweet.query(function() {
      $scope.tweets = tweets.concat($scope.tweets);
      $scope.getTweets();
    });
  };

  $scope.tweet = new Tweet({ 'user': '' });

  $scope.sendTweet = function() {
    $scope.tweet.$save();
    $scope.tweet = new Tweet({ 'user': '' });
  };
}]);