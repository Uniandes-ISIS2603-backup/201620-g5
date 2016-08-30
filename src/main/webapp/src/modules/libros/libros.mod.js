(function (ng) {
    var mod = ng.module("librosModule", ["ngMessages"]);
    mod.constant("librosContext", "api/libros");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/libros/';
            $urlRouterProvider.otherwise("/librosList");
     
            $stateProvider.state('librosList', {
                url: '/libros',
                views: {
                    'mainView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.list.html'
                    }
                }
            }).state('libroCreate', {
                url: '/libros/create',
                views: {
                    'mainView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.create.html'
                    }
                }

            }).state('libroEdit', {
                url: '/libros/:libroId',
                param: {
                    libroId: null
                },
                views: {
                    'mainView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.create.html'
                    }
                }
            });
        }]);
})(window.angular);