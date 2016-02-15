'use strict';

/**
 * @ngdoc function
 * @name companyManagerApp.controller:AddcompanyCtrl
 * @description
 * # AddcompanyCtrl
 * Controller of the companyManagerApp
 */
angular.module('companyManagerApp')
  .controller('AddcompanyCtrl', function ($scope, $http, $window, SERVER_URL) {
    $scope.dataLoading = false;
    $scope.company = {};

    $scope.add = function() {
    	var data = {
    		company: JSON.stringify(angular.copy($scope.company))
    	};
		$scope.dataLoading = true;
    	$http.post(SERVER_URL, data)
	  		.success(function(response) {
	  			$scope.dataLoading = false;
	  			$window.location.href = "/";
	  		})
	  		.error(function(response) {
	  			$scope.dataLoading = false;
	  		});
	};

  });
