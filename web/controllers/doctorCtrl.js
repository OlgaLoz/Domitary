app.controller('doctorCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

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

    $http.get('FindUsersToDoctor').then(function(response) {
        $scope.students = response.data;
    }, function errorCallback(response){
        $location.path('/');
    });

    $scope.searchInfo = {
        lastNameInput: null
    };

    $scope.findSingleUserToDoctor = function() {
        $http.post('FindSingleUserToDoctor', $scope.searchInfo).then(function(response) {
            $scope.students = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.findUsersToDoctor = function() {
        $http.get('FindUsersToDoctor').then(function(response) {
            $scope.searchInfo.lastNameInput = null;
            $scope.students = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.checkInfo = {
        checkers: [],
        uncheckers: []
    };

    $scope.checkStudents = function() {
        $http.post('CheckStudentsByDoctor', $scope.checkInfo).then(function(response) {
            $scope.students = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);
