(function () {
    'use strict';
    angular.module('movieFlixApp').controller('userController', userController);
    userController.$inject = ['$scope', '$window', '$state', 'authService', 'movieService', 'userService', 'commentService'];
    function userController($scope, $window, $state, authService, movieService, userService, commentService) {
        var self = $scope;
        if (!authService.isAuthenticated()) {
            $state.go('login', {});
        }
        self.movieDetailsPopup = true;
        self.user = authService.getUser();
        self.currentItem = {};
        self.addUpdateMovie;
        self.viewUser;
        self.movieComment;
        self.resetAddUpdateMovie = function () {
            self.addUpdateMovie = {"Id": "", "Language": "", "Runtime": "", "Type": "", "Country": "", "Year": "", "Writer": "", "Poster": "", "Title": "", "Awards": "", "Actors": "", "Released": "", "Director": "", "Plot": "", "imdbID": "", "Metascore": "", "Rated": "", "Genre": "", "imdbRating": "", "imdbVotes": ""};
        };
        self.setAddUpdateMovie = function (movie) {
            self.addUpdateMovie = movie;
        };
        self.resetViewUser = function () {
            self.addUpdateMovie = {"id": "", "userName": "", "userEmail": "", "userAccountType": ""};
        };
        self.setViewUser = function (user) {
            self.viewUser = user;
        };

        self.resetMovieComment = function () {
            self.movieComment = {"id": "", "message": "", "user": {}, "movie": {}};
        };
        self.setMovieComment = function (comment) {
            self.movieComment = comment;
        };

        self.searchInputs = {
            movieSearch: "",
            movieId: "",
            userId: ""
        };
        self.operations = {
            movieOperation: "",
            userOperation: ""
        };

        self.openMovieDetailsPopup = function (itemId) {
            self.currentItem = self.findMovieById(itemId);
            document.getElementById("mySidenav").style.width = "100%";
        };
        self.closeMovieDetailsPopup = function () {
            document.getElementById("mySidenav").style.width = "0%";
        };

        self.openAddUpdateMoviePopup = function () {
            document.getElementById('myModal').style.width = "100%";
        };
        self.closeAddUpdateMoviePopup = function () {
            document.getElementById('myModal').style.width = "0%";
        };

        self.openViewUserPopup = function () {
            document.getElementById('viewUserModal').style.width = "100%";
        };

        self.closeViewUserPopup = function () {
            document.getElementById('viewUserModal').style.width = "0%";
        };
        self.sortMoviesByRating = function () {
            self.sortMoviesBySelection("rating");
        };
        self.sortMoviesByVotes = function () {
            self.sortMoviesBySelection("votes");
        };
        self.sortMoviesByYear = function () {
            self.sortMoviesBySelection("year");
        };
        self.doSearch = function () {
            if (!self.searchInputs.movieSearch) {
                return;
            }

            self.findByType(self.searchInputs.movieSearch, "name");
        };
        self.topRatedMovies = function () {
            self.findByType("MOVIE", "ASC");
        };
        self.topRatedTv = function () {
            self.findByType("TV", "ASC");
        };

        self.findMovieById = function (movieId) {
            for (var movieInd in self.movies) {
                if (movieId === self.movies[movieInd].id) {
                    self.resetMovieComment();
                    commentService.getUserComments(movieId, self.user.userId).then(function (d) {
                        if (!d) {
                            return;
                        }
                        self.setMovieComment(d[0]);
                        console.log(d);
                    }, function (errResponse) {
                        console.error('Error while Fething movies');
                    });
                    return self.movies[movieInd];
                }
            }
        };

        self.loadAllMovies = function () {
            movieService.getAllMovies()
                    .then(function (d) {
                        if (!d) {
                            self.movies = [];
                            return;
                        }
                        self.movies = d;
                    }, function (errResponse) {
                        console.error('Error while Fething movies');
                    });
        };

        self.getMovie = function (id) {
            movieService.getMovie(id)
                    .then(function (d) {
                        if (!d) {
                            self.resetAddUpdateMovie();
                        }
                        self.setAddUpdateMovie(d);

                    }, function (errResponse) {
                        console.error('Error while Fething movie');
                        self.resetAddUpdateMovie();
                    });
        };

        self.getUser = function (id) {
            userService.getUser(id)
                    .then(function (d) {
                        self.resetViewUser();
                        if (!d) {
                            return;
                        }
                        self.setViewUser(d);
                    }, function (errResponse) {
                        self.resetViewUser();
                        console.error('Error while Fething User');
                    });
        };
        self.sortMoviesBySelection = function (sortCol) {
            movieService.sortBySelection(sortCol)
                    .then(function (d) {
                        if (!d) {
                            self.movies = [];
                            return;
                        }
                        self.movies = d;
                    }, function (errResponse) {
                        console.error('Error while Fething movies');
                    });
        };
        self.findByType = function (type, sort) {
            movieService.findByType(type, sort)
                    .then(function (d) {
                        if (!d) {
                            self.movies = [];
                            return;
                        }
                        self.movies = d;
                    }, function (errResponse) {
                        console.error('Error while Fething movies');
                    });
        };

        self.onMovieCommentBtnClick = function () {
            var comment = {
                message: self.movieComment.message,
                user: {
                    id: self.user.userId
                },
                movie: {
                    id: self.currentItem.id
                }
            };
            self.createComment(comment);

        };

        self.createComment = function (comment) {
            commentService.createComment(comment)
                    .then(function (d) {
                        if (!d) {
                            console.log("Failed to add comment");
                            return;
                        }
                        console.log("Comment added succesfully ");
                    }, function (errResponse) {
                        console.error("Failed to add comment");
                    });
        };

        /*Admin Starts*/
        self.onMovieAddUpdateBtnClick = function () {
            if (self.searchInputs.movieId === "") {
                self.resetAddUpdateMovie();
                self.operations.movieOperation = "ADD";
            } else {
                self.getMovie(self.searchInputs.movieId);
                self.operations.movieOperation = "UPDATE";
            }
            self.openAddUpdateMoviePopup();
        };
        self.onUserViewBtnClick = function () {
            if (!self.searchInputs.userId) {
                return;
            } else {
                self.getUser(self.searchInputs.userId);
            }
            self.openViewUserPopup();
        };

        self.onAddMovieButtonClick = function () {
            self.createMovie(self.addUpdateMovie);
        };
        self.onUpdateMovieButtonClick = function () {
            self.updateMovie(self.addUpdateMovie);
        };
        self.onDeleteMovieButtonClick = function () {
            if (!self.searchInputs.movieId) {
                return;
            }
            self.deleteMovie(self.searchInputs.movieId);
        };

        self.createMovie = function (movie) {
            movieService.createMovie(movie)
                    .then(function (d) {
                        self.resetAddUpdateMovie();
                        if (!d) {
                            console.log("Failed to add movie");
                        }
                        self.loadAllMovies();
                        console.log("Movie added succesfully ");
                    }, function (errResponse) {
                        console.error("Failed to add movie");
                        self.resetAddUpdateMovie();
                    });
            self.closeAddUpdateMoviePopup();
        };
        self.updateMovie = function (movie) {
            movieService.updateMovie(movie)
                    .then(function (d) {
                        self.resetAddUpdateMovie();
                        if (!d) {
                            console.log("Failed to update movie");
                        }
                        self.loadAllMovies();
                    }, function (errResponse) {
                        console.error("Failed to update movie");
                        self.resetAddUpdateMovie();
                    });
            self.closeAddUpdateMoviePopup();
        };
        self.deleteMovie = function (movieId) {
            movieService.deleteMovie(movieId)
                    .then(function (d) {
                        if (!d) {
                            console.log("Failed to delete movie");
                        }
                        self.loadAllMovies();
                    }, function (errResponse) {
                        console.error("Failed to delete movie");
                    });
        };
        self.doLogout = function () {
            authService.setUser(null);
            $state.go("login", {});
        };


        self.loadAllMovies();
    }
})();