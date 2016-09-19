(function () {
    var mod = angular.module('blogsModule',[]);
    mod.constant("blogsContext", "/blogs");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/blogs/';
            $urlRouterProvider.otherwise("/blogsList");
     
            $stateProvider.state('blogsList', {
                url: '/blogs',
                parent: 'libroEdit',
                views: {
                    'libroInstanceView': {
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'blogs.list.html'
                    }
                }
            }).state('blogCreate', {
                url: '/blogs/create',
                parent: 'libroEdit',
                views: {
                    '}libroInstanceView': {
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'blogs.create.html'
                    }
                }

            });
        }]);
})(window.angular);


