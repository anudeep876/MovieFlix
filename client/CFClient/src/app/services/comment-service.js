(function () {
    'use strict';
    angular.module('movieFlixApp')
            .service('commentService', commentService);
    commentService.$inject = ['$http', '$q', 'CONFIG'];
    function commentService($http, $q, CONFIG) {
        var self = this;
        self.createComment = createComment;
        self.updateComment = updateComment;
        self.deleteComment = deleteComment;
        self.getComments = getComments;
        self.getAllComments = getAllComments;
        self.getUserComments = getUserComments;
        self.getOtherUserMovieComments = getOtherUserMovieComments;

        function createComment(comment) {
            return $http.post(CONFIG.API_HOST + '/comments', comment)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function updateComment(comment) {
            return $http.put(CONFIG.API_HOST + '/comments/' + comment.id, comment)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function deleteComment(commentId) {
            return $http.delete(CONFIG.API_HOST + '/comments/' + commentId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getComments(commentId) {
            return $http.get(CONFIG.API_HOST + '/comments/' + commentId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getAllComments() {
            return $http.get(CONFIG.API_HOST + '/comments')
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getUserComments(movieId, userId) {
            return $http.get(CONFIG.API_HOST + '/comments?movie=' + movieId + '&user=' + userId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
        function getOtherUserMovieComments(movieId, userId) {
            return $http.post(CONFIG.API_HOST + '/comments?movie=' + movieId + '&user=' + userId)
                    .then(function (response) {
                        return response.data;
                    }, function (response) {
                        return $q.reject('ERROR: ' + response.statusText);
                    });
        }
    }
})();