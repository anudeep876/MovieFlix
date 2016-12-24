(function () {
    'use strict';
    angular.module('movieFlixApp')
            .service('movieService', movieService);
    movieService.$inject = ['$http', '$q', 'CONFIG'];
    function movieService($http, $q, CONFIG) {
        var self = this;
        self.createMovie = createMovie;
        self.updateMovie = updateMovie;
        self.deleteMovie = deleteMovie;
        self.getMovie = getMovie;
        self.getAllMovies = getAllMovies;
        self.getUserMovies = getUserMovies;
        self.findByType = findByType;
        self.sortBySelection = sortBySelection;
        self.findByGenre = findByGenre;
        self.findByDirector = findByDirector;
        self.findByimdbId = findByimdbId;
        self.findByYear = findByYear;
        function createMovie(movie) {
            return $http.post(CONFIG.API_HOST + '/movies', movie)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function updateMovie(movie) {
            return $http.put(CONFIG.API_HOST + '/movies/' + movie.id, movie)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function deleteMovie(movieId) {
            return $http.delete(CONFIG.API_HOST + '/movies/' + movieId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getMovie(movieId) {
            return $http.get(CONFIG.API_HOST + '/movies/' + movieId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getAllMovies() {
            return $http.get(CONFIG.API_HOST + '/movies')
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getUserMovies() {
            return $http.get(CONFIG.API_HOST + '/movies')
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function findByType(type, sort) {
            return $http.get(CONFIG.API_HOST + '/movies?type=' + type + '&sort=' + sort)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function sortBySelection(sortCol) {
            return $http.get(CONFIG.API_HOST + '/movies/sortByCol?sortCol=' + sortCol)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function findByGenre(genre, sort) {
            return $http.get(CONFIG.API_HOST + '/movies?genre=' + genre + '&sort=' + sort)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function findByDirector(director, sort) {
            return $http.get(CONFIG.API_HOST + '/movies?director=' + director + '&sort=' + sort)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function findByimdbId(imdbId, sort) {
            return $http.get(CONFIG.API_HOST + '/movies?imdbId=' + imdbId + '&sort=' + sort)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function findByYear(year, sort) {
            return $http.get(CONFIG.API_HOST + '/movies?year=' + year + '&sort=' + sort)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
    }
})();