(function () {
    'use strict';
    angular.module('movieFlixApp')
            .service('ratingService', ratingService);
    ratingService.$inject = ['$http', '$q', 'CONFIG'];
    function ratingService($http, $q, CONFIG) {
        var self = this;
        self.createRating = createRating;
        self.updateRating = updateRating;
        self.deleteRating = deleteRating;
        self.getRating = getRating;
        self.getAllRatings = getAllRatings;

        function createRating(comment) {
            return $http.post(CONFIG.API_HOST + '/ratings', comment)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function updateRating(rating) {
            return $http.put(CONFIG.API_HOST + '/ratings/' + rating.id, rating)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function deleteRating(ratingId) {
            return $http.delete(CONFIG.API_HOST + '/ratings/' + ratingId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getRating(rating) {
            return $http.get(CONFIG.API_HOST + '/ratings/' + rating)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getAllRatings() {
            return $http.get(CONFIG.API_HOST + '/ratings')
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
    }
})();