(function () {
    'use strict';

    var movieFlixApp = angular.module('movieFlixApp', ['ui.router']);
    movieFlixApp.config(configMain);
//    movieFlixApp.run(configRun);
    configMain.$inject = ['$stateProvider', '$urlRouterProvider', '$httpProvider'];
//    configRun.$inject = ['$http'];
    function configMain($stateProvider, $urlRouterProvider, $httpProvider) {

//        $urlRouterProvider.otherwise('/app');

        $stateProvider
                .state('app', {
                    url: '/app',
                    templateUrl: 'app/home/view/home.view.html',
                    controller: 'homeController',
                    controlleerAs: 'hc'
                })
                .state('login', {
                    url: '/login',
                    templateUrl: 'app/login/view/login.view.html',
                    controller: 'loginController',
                    controlleerAs: 'lc'
                })
                .state('user', {
                    url: '/user',
                    templateUrl: 'app/user/view/user.view.html',
                    controller: 'userController',
                    controlleerAs: 'uc'
                })
                .state('admin', {
                    url: '/admin',
                    templateUrl: 'app/user/view/admin.view.html',
                    controller: 'userController',
                    controlleerAs: 'uc'
                });
//        $httpProvider.interceptors.push(interceptor);
    }

    var interceptor = function () {
        return {
            'request': function (config) {
                console.log(config);
                config.headers['Authorization'] = 'Bearer YmVlcDpib29w';
            }
        }
    };

//    function configRun($http) {
//        $http.defaults.headers.common.Authorization = 'Bearer YmVlcDpib29w';
//    }

    angular.module('movieFlixApp').directive('aGreatEye', function () {
        return {
            restrict: 'E',
            replace: true,
            template: '<h1>lidless, wreathed in flame, {{1 + 1}} times</h1>'
        };
    });
    angular.module('movieFlixApp').directive('paginator', ['$filter', '$parse',
        function ($filter, $parse) {
            return {
                restrict: 'EA',
                scope: true,
                link: function link($scope, $element, $attrs, $controller) {
                    var itemsPerPage = $attrs.itemsPerPage * 1, // cast to number
                            pagesPerGroup = $attrs.pagesPerGroup * 1, // cast to number
                            itemsPerGroup = itemsPerPage * pagesPerGroup,
                            parentScope = $scope.$parent || $scope,
                            itemsGet = $parse($attrs.items);

                    $scope.items = itemsGet(parentScope);

                    $scope.$watch(function () {
                        var parentValue = itemsGet(parentScope);

                        if (parentValue !== $scope.items) {
                            $scope.items = parentValue;
                        }

                        return [];
                    });

                    $scope.itemsPerPage = itemsPerPage;
                    var groups = [];
                    var currentGroup = 0;
                    var currentPage = 0;
                    $scope.sorting = 'title';

                    function numItemsPreviousGroups() {
                        return itemsPerPage * pagesPerGroup * currentGroup;
                    }

                    function numItemsPreviousPages() {
                        return itemsPerPage * currentPage;
                    }

                    function firstItemCurrentPage() {
                        return angular.isDefined($scope.items) && $scope.items.length > 0 ? 1 : 0;
                    }

                    function numItemsCurrentPage() {
                        if (groups[currentGroup] && groups[currentGroup][currentPage]) {
                            return groups[currentGroup][currentPage].length;
                        } else {
                            return 0;
                        }
                    }

                    function numPagesPreviousGroups() {
                        return pagesPerGroup * currentGroup;
                    }

                    $scope.firstItemNumber = function () {
                        return numItemsPreviousGroups() + numItemsPreviousPages() + firstItemCurrentPage();
                    };

                    $scope.lastItemNumber = function () {
                        return numItemsPreviousGroups() + numItemsPreviousPages() + numItemsCurrentPage();
                    };

                    $scope.itemsCount = function () {
                        return $scope.items ? $scope.items.length : [];
                    };

                    $scope.paginatedItems = function () {
                        if (groups[currentGroup] && groups[currentGroup][currentPage]) {
                            return groups[currentGroup][currentPage];
                        } else {
                            return [];
                        }
                    };

                    $scope.pagesInGroup = function () {
                        var result = [];

                        if (groups[currentGroup]) {
                            angular.forEach(groups[currentGroup], function (val, idx) {
                                result.push({
                                    index: pagesPerGroup * currentGroup + idx + 1,
                                    active: currentPage === idx
                                });
                            });
                        }

                        return result;
                    };

                    function calculateGroups() {
                        groups = [];
                        //$filter('orderBy')($scope.items, $scope.sorting)
                        angular.forEach($scope.items, function (item, i) {
                            var groupId = Math.floor(i / itemsPerGroup),
                                    pageId = Math.floor(i / itemsPerPage) - groupId * pagesPerGroup;

                            if (i % itemsPerGroup === 0) {
                                groups[groupId] = [];
                            }

                            if (i % itemsPerPage === 0) {
                                groups[groupId][pageId] = [];
                            }

                            groups[groupId][pageId].push(item);
                        });

                        if (groups.length === 0) {
                            currentGroup = 0;
                            currentPage = 0;
                        } else {
                            if (currentGroup > groups.length - 1) {
                                currentGroup = groups.length - 1;
                                currentPage = groups[currentGroup].length - 1;
                            } else if (currentPage > groups[currentGroup].length - 1) {
                                currentPage = groups[currentGroup].length - 1;
                            }
                        }
                    }

                    $scope.$watch('items', function (newVal, oldVal) {
                        if (angular.isDefined(newVal)) {
                            calculateGroups();
                        }
                    });

//                    $scope.$watch('sorting', function (newVal, oldVal) {
//                        if (!angular.equals(newVal, oldVal)) {
//                            calculateGroups();
//                        }
//                    });

                    $scope.hasNextGroup = function () {
                        return groups.length > 0 && currentGroup < groups.length - 1;
                    };

                    $scope.gotoNextGroup = function () {
                        if ($scope.hasNextGroup) {
                            currentGroup++;
                            currentPage = 0;
                        }
                    };

                    $scope.hasPrevGroup = function () {
                        return groups.length > 0 && currentGroup > 0;
                    };

                    $scope.gotoPrevGroup = function () {
                        if ($scope.hasPrevGroup) {
                            currentGroup--;
                            currentPage = pagesPerGroup - 1;
                        }
                    };

                    function hasNextPageInCurrentGroup() {
                        return groups.length > 0 && currentPage < groups[currentGroup].length - 1;
                    }

                    $scope.hasNextPage = function () {
                        return groups.length > 0 && $scope.hasNextGroup() || hasNextPageInCurrentGroup();
                    };

                    $scope.gotoNextPage = function () {
                        if (hasNextPageInCurrentGroup()) {
                            currentPage++;
                        } else {
                            $scope.gotoNextGroup();
                        }
                    };

                    $scope.hasPrevPage = function () {
                        return groups.length > 0 && $scope.hasPrevGroup() || currentPage > 0;
                    };

                    $scope.gotoPrevPage = function () {
                        if (currentPage > 0) {
                            currentPage--;
                        } else {
                            $scope.gotoPrevGroup();
                        }
                    };

                    $scope.gotoPage = function (pageIdx) {
                        currentPage = pageIdx - 1 - pagesPerGroup * currentGroup;
                    };
                }
            };
        }]);
})();