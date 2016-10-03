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
                    'libroInstanceView': {
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'blogs.create.html'
                    }
                }

            })
                .state('blogGet', {
                url: '/blogs/ver/:id',
                parent: 'libroEdit',
                views: {
                    'libroInstanceView': {
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'blogs.ver.html'
                    }
                }

            })
            .state('blogEdit', {
                url: '/blogs/edit/:id',
                parent: 'libroEdit',
                param: {
                    blogId: null
                },
                views: {
                    'libroInstanceView': {
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'blogs.create.html'
                    }
                }
            });
        }]);
})(window.angular);


