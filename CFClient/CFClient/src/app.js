(function () {
    'use strict';
    angular.module('MyApp', [])
            .filter('reverse', [function () {
                    return function (string) {
                        return string.split('').reverse().join('');
                    }
                }])
})();