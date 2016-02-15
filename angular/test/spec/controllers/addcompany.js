'use strict';

describe('Controller: AddcompanyCtrl', function () {

  // load the controller's module
  beforeEach(module('companyManagerApp'));

  var AddcompanyCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AddcompanyCtrl = $controller('AddcompanyCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AddcompanyCtrl.awesomeThings.length).toBe(3);
  });
});
