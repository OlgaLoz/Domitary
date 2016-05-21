app.controller('deaneryCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

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
    $scope.status = sessionStorage.getItem('status');

    if ($scope.status != null) {
        $scope.statusInfo = {
            status: $scope.status
        };
        $http.post('SearchByStatusToDeanery', $scope.statusInfo).then(function(response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
        }, function errorCallback(response){
            $location.path('/');
        });
    }
    else {
        $http.get('SearchAllToDeanery').then(function (response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
        }, function errorCallback(response) {
            $location.path('/');
        });
    }

    $scope.searchAll = function() {
        $http.get('SearchAllToDeanery').then(function(response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
            sessionStorage.removeItem('status');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.searchInfo = {
        lastName: null
    };

    $scope.searchByLastName = function() {
        $http.post('SearchByLastNameToDeanery', $scope.searchInfo).then(function(response) {
            $scope.students = response.data.students;
            sessionStorage.removeItem('status');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.searchByStatus = function(status) {
        $scope.statusInfo = {
            status: status
        };
        $http.post('SearchByStatusToDeanery', $scope.statusInfo).then(function(response) {
            $scope.searchInfo.lastName = null;
            $scope.students = response.data.students;
            $scope.status = $scope.statusInfo.status;
            sessionStorage.setItem('status', $scope.status);
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);
