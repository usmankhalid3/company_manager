'use strict';

/**
 * @ngdoc function
 * @name companyManagerApp.controller:CompanyCtrl
 * @description
 * # CompanyCtrl
 * Controller of the companyManagerApp
 */
angular.module('companyManagerApp')
  .controller('CompanyCtrl', function ($scope, $http, ngDialog, $window, SERVER_URL) {
  	
    $scope.dataLoaded = false;
  	$scope.data = [];
    $scope.owners = [];
    $scope.showForm = false;
    $scope.owner = {};
    $scope.selectedCompanyIndex = -1;

  	$http.get(SERVER_URL)
  		.success(function(response) {
  			$scope.data = response;
        $scope.dataLoaded = true;
  		})
  		.error(function(response) {
  			$scope.data = [];
        $scope.dataLoaded = true;
  		});

    $scope.openModal = function(index) {
      $scope.selectedCompanyIndex = index;
      $scope.owners = $scope.data[index].owners
      $scope.dialog = ngDialog.open({ 
        template: 'views/owners.html',
        className: 'ngdialog-theme-default dialogwidth600',
        scope: $scope,
        plain: false,
        showClose: true,
        closeByDocument: true,
        closeByEscape: true
      });
    };

    $scope.showAddOwnerForm = function() {
      $scope.showForm = true;
    };

    $scope.hideOwnerForm = function() {
      $scope.showForm = false;
    };

    $scope.addOwner = function() {
      var newOwner = angular.copy($scope.owner);
      if ($scope.owners) {
        $scope.owners.push(newOwner);
      }
      else {
        $scope.owners = [newOwner];
      }
      var data = {
        companyId: $scope.data[$scope.selectedCompanyIndex].id,
        owner: JSON.stringify(newOwner)
      };
      $http.patch(SERVER_URL, data)
        .success(function(response) {
          $window.location.href = "#/company";
          $scope.owner = {};
        })
        .error(function(response) {
          alert("error occured while creating owner");
          $scope.owner = {};
        });
    };
  });
