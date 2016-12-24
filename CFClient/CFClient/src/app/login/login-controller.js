(function () {
    'use strict';

    angular.module('movieFlixApp').controller('loginController', loginController);

    loginController.$inject = ['$scope', '$window', '$state', 'authService', 'userService'];
    function loginController($scope, $window, $state, authService, userService) {
        var self = $scope;
        self.message = "Login Page";
        self.formCtrl = {
            login: {
                username: "",
                password: ""
            },
            signup: {
                username: "",
                password: "",
                email: ""
            }
        };

        self.opentab = function (evt, tabselected) {
            var i, x, y;
            x = document.getElementsByClassName("menu");
            for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
            }
            y = document.getElementsByClassName("x");
            for (i = 0; i < y.length; i++) {
                y[i].className = y[i].className.replace(" active", "");
            }
            document.getElementById(tabselected).style.display = "block";
            evt.currentTarget.className += " active";
        };
        self.doLogin = function (evt) {
            var user = {
                userName: self.formCtrl.login.username,
                password: self.formCtrl.login.password
            };
            userService.authUser(user)
                    .then(
                            function (d) {
                                if (!d) {
                                    return;
                                }
                                if (!d.token) {
                                    return;
                                }
                                authService.setUser(d);
                                if (d.userRole === "ADMIN") {
                                    $state.go('admin', {});
                                } else {
                                    $state.go('user', {});
                                }

                            },
                            function (errResponse) {
                                console.error('Error while Authenticating User');
                            }
                    );
        };
        self.doSignup = function (evt) {
            var user = {
                userName: self.formCtrl.signup.username,
                password: self.formCtrl.signup.password,
                userEmail: self.formCtrl.signup.email,
                userAccountType: "USER"
            };
            var response = userService.createUser(user)
                    .then(
                            function (d) {
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while Creating User');
                            }
                    );

        };
    }
})();