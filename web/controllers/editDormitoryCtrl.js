app.controller('editDormitoryCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    if ($rootScope.dormitories != null) {
        $scope.dormitories = $rootScope.dormitories;
    }
    else{
        $http.get('DeleteDormitory').then(function(response) {
            $scope.dormitories = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    }

    $scope.deleteDormitory = function(dormitoryToDel) {
        $scope.delInfo = {
            dormitoryToDel: dormitoryToDel
        };
        $http.post('DeleteDormitory', $scope.delInfo).then(function(response) {
            $scope.dormitories = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.addInfo = {
        numberD: null,
        maxBlock: null,
        address: null
    };

    $scope.addDormitory = function() {
        $http.post('AddDormitory', $scope.addInfo).then(function(response) {
            $scope.addInfo.numberD = null;
            $scope.addInfo.maxBlock = null;
            $scope.addInfo.address = null;
            $scope.dormitories = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);
