(function (ng) {
    var mod = ng.module("librosModule", ["ngMessages", "ui.router"]);
    mod.constant("librosContext", "api/libros");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/libros/';
            $urlRouterProvider.otherwise("/librosList");
     
            $stateProvider.state('libros',{
                url:'/libros',
                abstract:true,
                views:{
                    'mainView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.html'
                    }
                }
            }).state('librosList', {
                url: '/list',
                parent:'libros',
                views: {
                    'libroView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.list.html'
                    }
                }
            }).state('libroCreate', {
                parent:'libros',
                url: '/create',
                views: {
                    'libroView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.create.html'
                    }
                }

            }).state('libroEdit', {
                parent:'libros',
                url: '/:libroId',
                param: {
                    libroId: null
                },
                views: {
                    'libroView': {
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'libros.create.html'
                    },
                    'childsView': {
                        templateUrl: basePath + 'libros.instance.html'
                    }
                }
            });
        }]);
})(window.angular);