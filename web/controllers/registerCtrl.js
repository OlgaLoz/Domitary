app.controller('registerCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    $scope.credentials = {
        login: null,
        password: null,
        passwordConfirmation: null,
        firstName: null,
        midName: null,
        lastName: null,
        birthday: null,
        group: null
    };

    $scope.register = function (){
        $http.post('Registration', $scope.credentials).then(function(response) {

            $rootScope.person = {
                login: response.data.login,
                student_status: response.data.student.studentStatus,
                student_status_text: getStatusText(response.data.student.studentStatus),
                first_name: response.data.student.firstName,
                mid_name: response.data.student.midName,
                last_name: response.data.student.lastName,
                birthday: response.data.student.dateOfBirth,
                group: response.data.student.groupNumber
            };

            $scope.person = $rootScope.person;
            $location.path('/student');

        }, function errorCallback(response){
            errorMessage(response.data, $scope);
        });
    };

    $scope.clearErrorField = function() {
        $scope.errorMessage = null;
    }

}]);

