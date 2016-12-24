(function () {
    'use strict';
    angular.module('movieFlixApp')
            .service('authService', authService);
    authService.$inject = [];
    function authService() {
        var self = this;
        self.user = null;
        self.setUser = setUser;
        self.getUser = getUser;
        self.isAuthenticated = isAuthenticated;
        function setUser(user) {
            self.user = user;
        }
        function getUser() {
            return self.user;
        }
        function isAuthenticated() {
            return self.user ? true : false;
        }
    }
})();