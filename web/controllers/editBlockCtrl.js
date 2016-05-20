app.controller('editBlockCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

    $scope.dormitories = [];
    $scope.blocks = [];

    if ($rootScope.dormitories != null) {
        $scope.dormitories = $rootScope.dormitories;
    }
    else{
        $http.get('GetAllDormitories').then(function(response) {
            $scope.dormitories = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    }

    if ($rootScope.blocks != null) {
        $scope.blocks = $rootScope.blocks;
    }
    else{
        $http.get('GetBlocks').then(function(response) {
            $scope.blocks = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    }

    $scope.getBlocks = function(dormitoryId) {
        $scope.blockInfo = {
            dormitoryId: dormitoryId
        };
        $http.post('GetBlocks', $scope.blockInfo).then(function(response) {
            $scope.blocks = response.data;
            $scope.test = 'test';
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.deleteBlock = function(blockToDel) {
        $scope.delInfo = {
            blockToDel: blockToDel
        };
        $http.post('DeleteBlock', $scope.delInfo).then(function(response) {
            $scope.blocks = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.addInfo = {
        numberD: null,
        numberB: null
    };

    $scope.addBlock = function() {
        $http.post('AddBlock', $scope.addInfo).then(function(response) {
            $scope.addInfo.numberD = null;
            $scope.addInfo.numberB = null;
            $scope.blocks = response.data;
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);

