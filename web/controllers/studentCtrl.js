app.controller('studentCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    if ($rootScope.person == null){
        $scope.user = {
            userId: sessionStorage.getItem('person')
        };
        $http.post('GetUser', $scope.user).then(function(response) {
            $rootScope.person = {
                userId: response.data.userId,
                role: response.data.role,
                login: response.data.login,
                student_status: null,
                student_status_text: null,
                first_name: null,
                mid_name: null,
                last_name: null,
                birthday: null,
                group: null
            };

            if (response.data.student != null) {
                $rootScope.person.student_status = response.data.student.studentStatus;
                $rootScope.person.student_status_text = getStatusText(response.data.student.studentStatus);
                $rootScope.person.first_name = response.data.student.firstName;
                $rootScope.person.mid_name = response.data.student.midName;
                $rootScope.person.last_name = response.data.student.lastName;
                $rootScope.person.birthday = getDateFromString(response.data.student.dateOfBirth);
                $rootScope.person.group = response.data.student.groupNumber;
            }

            $scope.person = $rootScope.person;

        }, function errorCallback(response){
            $location.path('/');
        });
    }
    else {
        $scope.person = $rootScope.person;
    }

    $scope.filledStatement = false;

    $scope.docsData = {
        dean: null,
        group: null,
        lastname: null,
        firstname: null,
        midname: null,
        region: null,
        city: null,
        street: null,
        house: null,
        flat: null,
        training: null,
        mobile_phone: null,
        home_phone: null,
        mother: null,
        father: null,
        filling_date: null,
        faculty: null,
        chair: null,
        passportSeries: null,
        passportNumber: null,
        passport: null,
        passportDateOfIssue: null
    };

    $scope.apply = function() {
        $scope.docsData.passport = $scope.docsData.passportSeries + ' ' + $scope.docsData.passportNumber;
        $http.post('StudentDocuments', $scope.docsData).then(function(response) {
            $rootScope.person.student_status = response.data;
            $rootScope.person.student_status_text = getStatusText(response.data);
            $scope.person = $rootScope.person;
            $location.path('/student');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);

