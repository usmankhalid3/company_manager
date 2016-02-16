'use strict';

describe('Controller: DeletecompanyCtrl', function () {

  // load the controller's module
  beforeEach(module('companyManagerApp'));

  var DeletecompanyCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DeletecompanyCtrl = $controller('DeletecompanyCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DeletecompanyCtrl.awesomeThings.length).toBe(3);
  });
});
