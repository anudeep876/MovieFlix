(function() {
    'use strict';

    angular.module('movieFlixApp').controller('homeController', homeController);

    homeController.$inject = ['$scope', '$window', '$state'];
    function homeController($scope, $window, $state) {
        var self = $scope;
        self.message = "Home Page";
        self.showLoginPage = function () {
            $state.go('login', {});
        };
    }
})();