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
            }).state('reservaCreate', {
                url: '/reservas/create',
                views: {
                    'mainView': {
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas.create.html'
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

            }).state('reservasBiblioEdit', {
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
            });
        }]);
})(window.angular);
