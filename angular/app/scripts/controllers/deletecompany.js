'use strict';

/**
 * @ngdoc function
 * @name companyManagerApp.controller:DeletecompanyCtrl
 * @description
 * # DeletecompanyCtrl
 * Controller of the companyManagerApp
 */
angular.module('companyManagerApp')
  .controller('DeletecompanyCtrl', function (SERVER_URL, $http, $routeParams, $window) {
  	var companyId = $routeParams.companyId;
	$http.delete(SERVER_URL + "/company?id=" + companyId)
  		.success(function(response) {
  			$scope.dataLoading = false;
  			$window.location.href = "/";
  		})
  		.error(function(response) {
  			$scope.dataLoading = false;
  		});
    $window.location.href = "/";
  });
