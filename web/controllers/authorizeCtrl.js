app.controller('authorizeCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    $scope.credentials = {
        login: null,
        password: null
    };

    $scope.authorize = function (){
        $http.post('Authorization', $scope.credentials).then(function(response) {

            $rootScope.person = {
                userId: response.data.userId,
                role: response.data.role,
                login: null,
                student_status: null,
                student_status_text: null,
                first_name: null,
                mid_name: null,
                last_name: null,
                birthday: null,
                group: null
            };

            switch (response.data.role) {
                case 'Student':
                    $rootScope.person.login = response.data.login;
                    $rootScope.person.student_status = response.data.student.studentStatus;
                    $rootScope.person.student_status_text = getStatusText(response.data.student.studentStatus);
                    $rootScope.person.first_name = response.data.student.firstName;
                    $rootScope.person.mid_name = response.data.student.midName;
                    $rootScope.person.last_name = response.data.student.lastName;
                    $rootScope.person.birthday = response.data.student.dateOfBirth;
                    $rootScope.person.group = response.data.student.groupNumber;
                    $location.path('/student');
                    break;
                case 'Doctor':
                    $location.path('/doctor');
                    break;
                case 'Governor':
                    $location.path('/governor');
                    break;
                case 'DeaneryWorker':
                    $location.path('/deanery');
                    break;
                case 'Admin':
                    $location.path('/admin');
                    break;
                default:
                    $location.path('/');
                    break;
            }

            $scope.person = $rootScope.person;
            sessionStorage.setItem('person', $scope.person.userId);

        }, function errorCallback(response){
            errorMessage(response.data, $scope);
        });
    };

    $scope.logout = function (){
        $http.get('Logout').then(function() {
            sessionStorage.removeItem('person');
            sessionStorage.removeItem('status');
            $rootScope.person = null;
            $scope.person = null;
            $location.path('/');
        });
    };

    $scope.clearErrorField = function() {
        $scope.errorMessage = null;
    }

}]);

