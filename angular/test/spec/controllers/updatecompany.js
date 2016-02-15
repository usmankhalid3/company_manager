'use strict';

describe('Controller: UpdatecompanyCtrl', function () {

  // load the controller's module
  beforeEach(module('companyManagerApp'));

  var UpdatecompanyCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UpdatecompanyCtrl = $controller('UpdatecompanyCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UpdatecompanyCtrl.awesomeThings.length).toBe(3);
  });
});
