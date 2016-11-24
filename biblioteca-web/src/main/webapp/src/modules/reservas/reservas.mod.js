(function (ng) {
    var mod = ng.module("reservasModule", ["ngMessages", "ui.router"]);
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservasList");
     
            $stateProvider.state('reservasList', {
                url: '/reservas',
                views: {
                    'mainView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.list.html'
                    }
                }
            }).state('reservasLibroList', {
                url: '/reservas',
                parent: 'libroEdit',
                views: {
                    'libroInstanceView': {
                        controller: 'reservasLibroCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.list.html'
                    }
                }
            }).state('reservaCreate', {
                url: '/reservas/create',
                parent: 'bibliotecaPrestar',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'reservasBiblioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasBiblio.create.html'
                    }
                }
            }).state('reservasBiblioList', {
                url: '/reservas',
                parent: 'bibliotecaPrestar',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'reservasBiblioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasBiblio.list.html'
                    }
                }

            }).state('reservaBiblioEdit', {
                url: '/reservas/edit/:reservaId',
                parent: 'bibliotecaPrestar',
                param: {
                    reservaId: null
                },
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'reservasBiblioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasBiblio.create.html'
                    }
                }
            }).state('reservaEdit', {
                url: '/reservas/:reservaId',
                param: {
                    reservaId: null
                },
                views: {
                    'mainView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.create.html'
                    }
                }
            });
        }]);
})(window.angular);
