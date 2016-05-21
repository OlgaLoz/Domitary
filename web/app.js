var app = angular.module('myApp', ['ngRoute', 'checklist-model']);

app.config(['$routeProvider', function($routeProvide){
    $routeProvide
        .when('/', {
            templateUrl: 'index.html'
        })
        .when('/student', {
            templateUrl: 'pages/student/indexStudent.html',
            controller:  'studentCtrl'
        })
        .when('/doctor', {
            templateUrl: 'pages/doctor/indexDoctor.html',
            controller:  'doctorCtrl'
        })
        .when('/governor', {
            templateUrl: 'pages/governor/indexGovernor.html',
            controller: 'governorCtrl'
        })
        .when('/deanery', {
            templateUrl: 'pages/deanery/indexDeaneryWorker.html'
        })
        .when('/admin', {
            templateUrl: 'pages/admin/indexAdmin.html',
            controller: 'adminCtrl'
        })
        .when('/documentsPage', {
            templateUrl: 'pages/student/documentsPage.html',
            controller:  'studentCtrl'
        })
        .when('/editDormitory', {
            templateUrl: 'pages/admin/editDormitory.html',
            controller:  'editDormitoryCtrl'
        })
        .when('/editBlock', {
            templateUrl: 'pages/admin/editBlock.html',
            controller:  'editBlockCtrl'
        })
        .when('/studentsDocumentsPage', {
            templateUrl: 'pages/governor/documentsPage.html',
            controller:  'documentsCtrl'
        })
        .otherwise({
            redirectTo :'/'
        })
}]);
