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
  			$window.location.href = "/";
  		})
  		.error(function(response) {
  			alert("Could not delete company with id = " + companyId);
  		});
  });
