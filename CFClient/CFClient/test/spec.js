describe('userService test', function () {

    var userService, httpBackend, data, user = {"userName": "aa", "password": "a"};

    beforeEach(module('movieFlixApp'));

    beforeEach(inject(function ($injector) {
        userService = $injector.get('userService');
        httpBackend = $injector.get('$httpBackend');
        httpBackend.whenPOST('http://localhost:2727/movie/api/auth/login').respond({"userName": "aa"});
    })); //server may need to be changed

    it('should return user when successfully authenticated', function () {
        userService.authUser(user).then(function (result) {
            expect(result.userName).toEqual(user.userName);
        });
        httpBackend.flush();
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingRequest();
        httpBackend.verifyNoOutstandingExpectation();
    });

});