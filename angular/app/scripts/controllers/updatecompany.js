'use strict';

/**
 * @ngdoc function
 * @name companyManagerApp.controller:UpdatecompanyCtrl
 * @description
 * # UpdatecompanyCtrl
 * Controller of the companyManagerApp
 */
angular.module('companyManagerApp')
  .controller('UpdatecompanyCtrl', function ($scope, $http, $routeParams, $window, SERVER_URL) {
    $scope.company = {loaded: false};
    $scope.dataLoading = true;
    $http.get("http://localhost:1435/company?id=" + $routeParams.companyId)
    	.success(function(response){
    		$scope.dataLoading = false;
    		$scope.company = response;
    		$scope.company.loaded = true;
    	})
    	.error(function(response){
    		$scope.dataLoading = false;
    	})

    $scope.update = function() {
    	var data = {
    		company: JSON.stringify(angular.copy($scope.company))
    	};
		$scope.dataLoading = true;
    	$http.put(SERVER_URL, data)
	  		.success(function(response) {
	  			$scope.dataLoading = false;
	  			$window.location.href = "/";
	  		})
	  		.error(function(response) {
	  			alert(JSON.stringify(response));
	  			$scope.dataLoading = false;
	  		});
	};
  });
