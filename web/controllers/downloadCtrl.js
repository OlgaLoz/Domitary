app.controller('downloadCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location) {

    $scope.download = function (student_status, doc_type) {
        $scope.downloadInfo = {
            status: student_status,
            docType: doc_type
        };
        $http.post('Download', $scope.downloadInfo).then(function(response) {
            window.open(response.data);
        }, function errorCallback(response){
            $location.path('/');
        });
    }

}]);