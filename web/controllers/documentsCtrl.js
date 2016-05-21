app.controller('documentsCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    $scope.students = [];
    $scope.status = null;

    if ($rootScope.students != null) {
        $scope.students = $rootScope.students;
        $scope.status = $rootScope.status;
    }
    else{
        $scope.statusInfo = {
            status: sessionStorage.getItem('status')
        };
        $http.post('DownloadDocsByGovernor', $scope.statusInfo).then(function(response) {
            $scope.students = response.data.students;
            $scope.status = sessionStorage.getItem('status');
        }, function errorCallback(response){
            $location.path('/');
        });
    }

}]);
