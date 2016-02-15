"use strict";function configureDefaults(a){a.params.count=5,a.settings.counts=[]}angular.module("companyManagerApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","ngTouch","ngDialog","ngTable"],["$httpProvider",function(a){a.defaults.headers.post["Content-Type"]="application/x-www-form-urlencoded;charset=utf-8",a.defaults.headers.put["Content-Type"]="application/x-www-form-urlencoded;charset=utf-8",a.defaults.headers.patch["Content-Type"]="application/x-www-form-urlencoded;charset=utf-8";var b=function(a){var c,d,e,f,g,h,i,j="";for(c in a)if(d=a[c],d instanceof Array)for(i=0;i<d.length;++i)g=d[i],e=c+"["+i+"]",h={},h[e]=g,j+=b(h)+"&";else if(d instanceof Object)for(f in d)g=d[f],e=c+"["+f+"]",h={},h[e]=g,j+=b(h)+"&";else void 0!==d&&null!==d&&(j+=encodeURIComponent(c)+"="+encodeURIComponent(d)+"&");return j.length?j.substr(0,j.length-1):j};a.defaults.transformRequest=[function(a){return angular.isObject(a)&&"[object File]"!==String(a)?b(a):a}]}]).config(["$routeProvider",function(a){a.when("/",{templateUrl:"views/company.html",controller:"CompanyCtrl",controllerAs:"company"}).when("/company/add",{templateUrl:"views/addcompany.html",controller:"AddcompanyCtrl",controllerAs:"addCompany"}).when("/company/update/:companyId",{templateUrl:"views/updatecompany.html",controller:"UpdatecompanyCtrl",controllerAs:"updateCompany"}).otherwise({redirectTo:"/"})}]).constant("SERVER_URL","http://localhost:1435").run(configureDefaults),configureDefaults.$inject=["ngTableDefaults"],angular.module("companyManagerApp").controller("MainCtrl",function(){this.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}),angular.module("companyManagerApp").controller("AboutCtrl",function(){this.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}),angular.module("companyManagerApp").controller("CompanyCtrl",["$scope","$http","ngDialog","$window","SERVER_URL",function(a,b,c,d,e){a.dataLoaded=!1,a.data=[],a.owners=[],a.showForm=!1,a.owner={},a.selectedCompanyIndex=-1,b.get(e).success(function(b){a.data=b,a.dataLoaded=!0}).error(function(b){a.data=[],a.dataLoaded=!0}),a.openModal=function(b){a.selectedCompanyIndex=b,a.owners=a.data[b].owners,a.dialog=c.open({template:"views/owners.html",className:"ngdialog-theme-default dialogwidth600",scope:a,plain:!1,showClose:!0,closeByDocument:!0,closeByEscape:!0})},a.showAddOwnerForm=function(){a.showForm=!0},a.hideOwnerForm=function(){a.showForm=!1},a.addOwner=function(){var c=angular.copy(a.owner);a.owners?a.owners.push(c):a.owners=[c];var f={companyId:a.data[a.selectedCompanyIndex].id,owner:JSON.stringify(c)};b.patch(e,f).success(function(b){d.location.href="#/company",a.owner={}}).error(function(b){alert("error occured while creating owner"),a.owner={}})}}]),angular.module("companyManagerApp").controller("AddcompanyCtrl",["$scope","$http","$window","SERVER_URL",function(a,b,c,d){a.dataLoading=!1,a.company={},a.add=function(){var e={company:JSON.stringify(angular.copy(a.company))};a.dataLoading=!0,b.post(d,e).success(function(b){a.dataLoading=!1,c.location.href="/"}).error(function(b){a.dataLoading=!1})}}]),angular.module("companyManagerApp").controller("UpdatecompanyCtrl",["$scope","$http","$routeParams","$window","SERVER_URL",function(a,b,c,d,e){a.company={loaded:!1},a.dataLoading=!0,b.get("http://localhost:1435/company?id="+c.companyId).success(function(b){a.dataLoading=!1,a.company=b,a.company.loaded=!0}).error(function(b){a.dataLoading=!1}),a.update=function(){var c={company:JSON.stringify(angular.copy(a.company))};a.dataLoading=!0,b.put(e,c).success(function(b){a.dataLoading=!1,d.location.href="/"}).error(function(b){alert(JSON.stringify(b)),a.dataLoading=!1})}}]),angular.module("companyManagerApp").run(["$templateCache",function(a){a.put("views/about.html","<p>This is the about view.</p>"),a.put("views/addcompany.html",'<div class="col-md-8 col-md-offset-2"> <h2>Add New Company</h2> <br> <form name="form" ng-submit="add()" role="form"> <div class="form-group" ng-class="{ \'has-error\': form.name.$dirty && form.name.$error.required }"> <label for="name">Company Name</label> <input type="text" name="name" id="name" class="form-control" ng-model="company.name" required> <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Company Name is required</span> </div> <div class="form-group" ng-class="{ \'has-error\': form.address.$dirty && form.address.$error.required }"> <label for="address">Address</label> <input type="text" name="address" id="address" class="form-control" ng-model="company.address" required> <span ng-show="form.address.$dirty && form.address.$error.required" class="help-block">Address is required</span> </div> <div class="form-group" ng-class="{ \'has-error\': form.city.$dirty && form.city.$error.required }"> <label for="city">City</label> <input type="text" name="city" id="city" class="form-control" ng-model="company.city" required> <span ng-show="form.city.$dirty && form.city.$error.required" class="help-block">City is required</span> </div> <div class="form-group" ng-class="{ \'has-error\': form.country.$dirty && form.country.$error.required }"> <label for="country">Country</label> <input type="text" name="country" id="country" class="form-control" ng-model="company.country" required> <span ng-show="form.country.$dirty && form.country.$error.required" class="help-block">Country is required</span> </div> <div class="form-group" ng-class=""> <label for="email">Email</label> <input type="text" name="email" id="email" class="form-control" ng-model="company.email"> </div> <div class="form-group" ng-class=""> <label for="phone">Phone</label> <input type="text" name="phone" id="phone" class="form-control" ng-model="company.phone"> </div> <div class="form-actions"> <button type="submit" ng-disabled="form.$invalid || dataLoading" class="btn btn-primary">Add New</button> <a href="#/" class="btn btn-link">Cancel</a> </div> <br> <br> </form> </div>'),a.put("views/company.html",'<div class="row" ng-show="dataLoaded"> <div class="col-md-15"> <h2>All Companies</h2> <br> <table ng-table="tableParams" class="table table-condensed table-bordered table-striped" show-filter="false"> <tr ng-repeat="company in data"> <td title="\'Name\'" filter="{ name: \'text\'}">{{company.name}}</td> <td title="\'Address\'" filter="{ address: \'text\'}">{{company.address}}</td> <td title="\'City\'" filter="{ city: \'text\'}">{{company.city}}</td> <td title="\'Country\'" filter="{ country: \'text\'}">{{company.country}}</td> <td title="\'Email\'" filter="{ email: \'text\'}">{{company.email}}</td> <td title="\'Phone\'" filter="{ phone: \'text\'}">{{company.phone}}</td> <td title="\'Actions\'"> <a ng-href="#/company/update/{{company.id}}">Update</a> / <a ng-click="openModal($index)">Owners</a> </td> </tr> </table> </div> </div>'),a.put("views/main.html",'<div class="jumbotron"> <h1>\'Allo, \'Allo!</h1> <p class="lead"> <img src="images/yeoman.8cb970fb.png" alt="I\'m Yeoman"><br> Always a pleasure scaffolding your apps. </p> <p><a class="btn btn-lg btn-success" ng-href="#/">Splendid!<span class="glyphicon glyphicon-ok"></span></a></p> </div> <div class="row marketing"> <h4>HTML5 Boilerplate</h4> <p> HTML5 Boilerplate is a professional front-end template for building fast, robust, and adaptable web apps or sites. </p> <h4>Angular</h4> <p> AngularJS is a toolset for building the framework most suited to your application development. </p> <h4>Karma</h4> <p>Spectacular Test Runner for JavaScript.</p> </div>'),a.put("views/owners.html",'<div style="magin: auto; width: 90%"> <div style="float:none; display:inline"> <div class="ngdialog-message"> <h2>Owners</h2> </div> <a ng-show="showForm==false" ng-click="showAddOwnerForm()">Add New</a> <br> <table ng-show="showForm==false" ng-table="tableParams" class="table table-condensed table-bordered table-striped" show-filter="false"> <tr ng-repeat="owner in owners"> <td title="\'Name\'" filter="{ name: \'text\'}">{{owner.firstName}}</td> <td title="\'Address\'" filter="{ address: \'text\'}">{{owner.lastName}}</td> <td title="\'Email\'" filter="{ email: \'text\'}">{{owner.email}}</td> <td title="\'Phone\'" filter="{ phone: \'text\'}">{{owner.phone}}</td> </tr> </table> <div ng-show="showForm==true"> <form name="form" ng-submit="addOwner()" role="form"> <div class="form-group" ng-class="{\'has-error\': form.firstName.$dirty && form.firstName.$error.required }"> <label for="firstName">First Name</label> <input type="text" name="firstName" id="firstName" class="form-control" ng-model="owner.firstName" required> <span ng-show="form.firstName.$dirty && form.firstName.$error.required" class="help-block">First Name is required</span> </div> <div class="form-group" ng-class="{\'has-error\': form.lastName.$dirty && form.lastName.$error.required }"> <label for="lastName">Last Name</label> <input type="text" name="lastName" id="lastName" class="form-control" ng-model="owner.lastName" required> <span ng-show="form.lastName.$dirty && form.lastName.$error.required" class="help-block">Last Name is required</span> </div> <div class="form-group"> <label for="email">Email</label> <input type="text" name="email" id="email" class="form-control" ng-model="owner.email"> </div> <div class="form-group" ng-class=""> <label for="phone">Phone</label> <input type="text" name="phone" id="phone" class="form-control" ng-model="owner.phone"> </div> <div class="form-actions"> <button type="submit" ng-click="hideOwnerForm()" ng-disabled="form.$invalid" class="btn btn-primary">Add</button> <a ng-click="hideAddOwnerForm()" class="btn btn-link">Cancel</a> </div> <br> <br> </form> </div> </div> </div>'),a.put("views/updatecompany.html",'<div class="col-md-8 col-md-offset-2" ng-hide="{{company.loaded}}"> <h2>Update Company</h2> <br> <form name="form" ng-submit="update()" role="form"> <div class="form-group" ng-class="{ \'has-error\': form.name.$dirty && form.name.$error.required }"> <label for="name">Company Name</label> <input type="text" name="name" id="name" class="form-control" ng-model="company.name" required> <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Company Name is required</span> </div> <div class="form-group" ng-class="{ \'has-error\': form.address.$dirty && form.address.$error.required }"> <label for="address">Address</label> <input type="text" name="address" id="address" class="form-control" ng-model="company.address" required> <span ng-show="form.address.$dirty && form.address.$error.required" class="help-block">Address is required</span> </div> <div class="form-group" ng-class="{ \'has-error\': form.city.$dirty && form.city.$error.required }"> <label for="city">City</label> <input type="text" name="city" id="city" class="form-control" ng-model="company.city" required> <span ng-show="form.city.$dirty && form.city.$error.required" class="help-block">City is required</span> </div> <div class="form-group" ng-class="{ \'has-error\': form.country.$dirty && form.country.$error.required }"> <label for="country">Country</label> <input type="text" name="country" id="country" class="form-control" ng-model="company.country" required> <span ng-show="form.country.$dirty && form.country.$error.required" class="help-block">Country is required</span> </div> <div class="form-group" ng-class=""> <label for="email">Email</label> <input type="text" name="email" id="email" class="form-control" ng-model="company.email"> </div> <div class="form-group" ng-class=""> <label for="phone">Phone</label> <input type="text" name="phone" id="phone" class="form-control" ng-model="company.phone"> </div> <div class="form-actions"> <button type="submit" ng-disabled="form.$invalid || dataLoading" class="btn btn-primary">Update</button> <a href="#/" class="btn btn-link">Cancel</a> </div> <br> <br> </form> </div>')}]);