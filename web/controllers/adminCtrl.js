app.controller('adminCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){

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

    $scope.dormitories = [];
    $scope.blocks = [];

    $scope.showDormitories = function() {
        $http.get('DeleteDormitory').then(function(response) {
            $rootScope.dormitories = response.data;
            $location.path('/editDormitory');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

    $scope.showBlocks = function() {
        $http.get('GetAllDormitories').then(function(response) {
            $rootScope.dormitories = response.data;
            $location.path('/editBlock');
        }, function errorCallback(response){
            $location.path('/');
        });

        $http.get('GetBlocks').then(function(response) {
            $rootScope.blocks = response.data;
            $location.path('/editBlock');
        }, function errorCallback(response){
            $location.path('/');
        });
    };

}]);
