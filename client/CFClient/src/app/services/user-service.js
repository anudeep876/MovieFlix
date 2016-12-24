(function () {
    'use strict';
    angular.module('movieFlixApp')
            .service('userService', userService);
    userService.$inject = ['$http', '$q', 'CONFIG'];
    function userService($http, $q, CONFIG) {
        var self = this;
        self.authUser = authUser;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.deleteUser = deleteUser;
        self.getUser = getUser;
        function authUser(user) {
            return $http.post(CONFIG.API_HOST + '/auth/login', user)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function createUser(user) {
            return $http.post(CONFIG.API_HOST + '/users', user)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function updateUser(user) {
            return $http.put(CONFIG.API_HOST + '/users/' + user.id, user)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function deleteUser(userId) {
            return $http.delete(CONFIG.API_HOST + '/users/' + userId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getUser(userId) {
            return $http.get(CONFIG.API_HOST + '/users/' + userId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
    }
})();