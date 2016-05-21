app.controller('governorCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    if ($scope.person == null){
        $scope.user = {
            userId: sessionStorage.getItem('person')
        };
        $http.post('GetUser', $scope.user).then(function(response) {
            $rootScope.person = {
                userId: response.data.userId,
                role: response.data.role,
                login: response.data.login
            };

            $scope.person = $rootScope.person;

        }, function errorCallback(response){
            $location.path('/');
        });
    }

    $scope.students = [];
    $scope.isSettled = null;
    $scope.status = sessionStorage.getItem('status');

    if ($scope.status != null) {
        $scope.statusInfo = {
            status: $scope.status
        };
        $http.post('SearchByStatusByGovernor', $scope.statusInfo).then(function(response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
            $scope.isSettled = response.data.isSettled;
        }, function errorCallback(response){
            $location.path('/');
        });
    }
    else {
        $http.get('SearchAllToGovernor').then(function (response) {
            $scope.students = response.data.students;
            $scope.status = response.data.status;
            $scope.isSettled = response.data.isSettled;
            sessionStorage.setItem('status', $scope.status);
        }, function errorCallback(response) {
            $location.path('/');
        });
    }

    $scope.searchInfo = {
        lastName: null
    };

    $scope.searchByLastName = function() {
        $http.post('SearchByLastNameByGovernor', $scope.searchInfo).then(function(response) {
            $scope.students = response.data.students;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.searchByStatus = function(status) {
        $scope.statusInfo = {
            status: status
        };
        $http.post('SearchByStatusByGovernor', $scope.statusInfo).then(function(response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
            $scope.status = response.data.status;
            $scope.isSettled = response.data.isSettled;
            sessionStorage.setItem('status', $scope.status);
            $location.path('/governor');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.checkInfo = {
        checkers: [],
        uncheckers: []
    };

    $scope.checkStudents = function() {
        $http.post('CheckStudentsByGovernor', $scope.checkInfo).then(function(response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.downloadDocs = function(status) {
        $scope.statusInfo = {
            status: status
        };
        $http.post('DownloadDocsByGovernor', $scope.statusInfo).then(function(response) {
            $rootScope.students = response.data.students;
            $rootScope.status = response.data.status;
            sessionStorage.setItem('status', $rootScope.status);
            $location.path('/studentsDocumentsPage');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);

