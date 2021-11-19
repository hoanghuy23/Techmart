let app = angular.module("admin-app", ["ngRoute", 'treeGrid']);

app.run(function($rootScope, $http, $timeout) {
    $rootScope.invoiceDetail = null;
    $rootScope.photos = [];
    $rootScope.tempProductId = null;
})

app.config(function($routeProvider, $locationProvider) {

    $routeProvider
        .when("/product", {
            templateUrl: "product/index.html",
            controller: "product-ctrl"
        })
        .when("/category", {
            templateUrl: "category/index.html",
            controller: "category-ctrl"
        })
        .when("/authorize/:Admin", {
            templateUrl: "authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .when("/inventory", {
            templateUrl: "statistic/inventory.html",
            controller: "statisticc-ctrl"
        })
//        .when("/revenue", {
//            templateUrl: "statistic/revenue.html",
//            controller: "statistic-ctrl"
//        })
        .when("/inventory-report", {
            templateUrl: "product/inventory-report.html",
            controller: "report-ctrl"
        })
        .when("/account-management", {
            templateUrl: "account/index.html",
            controller: "account-ctrl"
        })
        .when("/invoice-report", {
            templateUrl: "invoice/index.html",
            controller: "invoice-ctrl"
        })
        .when("/invoice-details", {
            templateUrl: "invoice/invoice-details.html",
            controller: "invoice-detail-ctrl"
        })
        .when("/image", {
            templateUrl: "product/_image.html",
            controller: "image-ctrl"
        })
        .otherwise({
            templateUrl: "dashboard/dashboard.html"
        });
})


app.controller("admin-app", function($scope, $http) {
    $scope.amountOfProducts = 0;
    $scope.amountOfAccounts = 0;
    $scope.amountOfInvoices = 0;

    $scope.count = function() {
        //load count product
        $http.get("/rest/products/count").then(resp => {
            $scope.amountOfProducts = resp.data;
        })
    }

    $scope.countAccounts = function() {
        //load count product
        $http.get("/rest/accounts/count").then(resp => {
            $scope.amountOfAccounts = resp.data;
        })
    }

    $scope.countInvoices = function() {
        //load count product
        $http.get("/rest/orders/count").then(resp => {
            $scope.amountOfInvoices = resp.data;
        })
    }

    // $scope.countInvoices();
    // $scope.countAccounts();
    // $scope.count();


})