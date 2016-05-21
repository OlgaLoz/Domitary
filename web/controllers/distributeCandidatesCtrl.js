app.controller('distributeCandidatesCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    $http.get('CheckStudentsByDeanery').then(function(response) {
        $scope.students = response.data.students;
        $scope.rooms = response.data.rooms;
    }, function errorCallback(response){
        $location.path('/');
    });

    $scope.distributionInfo = {
        student: null,
        room: null
    };

    $scope.checkStudents = function() {
        $http.post('CheckStudentsByDeanery', $scope.distributionInfo).then(function(response) {
            $scope.students = response.data.students;
            $scope.rooms = response.data.rooms;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);
