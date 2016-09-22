(function (ng) {
    var mod = ng.module("salasModule", []);
    mod.constant("salasContext", "api/salas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/salas/';
            $urlRouterProvider.otherwise("/salasList");
     
            $stateProvider.state('salasList', {
                url: '/salas',
                parent: 'bibliotecaEdit',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.list.html'
                    }
                }
            }).state('salaCreate', {
                url: '/salas/create',
                parent: 'bibliotecaEdit',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }

            }).state('salaDisponibilidad', {
                url: '/salas/create',
                parent: 'bibliotecaEdit',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }

            }).state('salaEdit', {
                url: '/salas/:salaId',
                parent: 'bibliotecaEdit',
                param: {
                    salaId: null
                },
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }
            });
        }]);
})(window.angular);