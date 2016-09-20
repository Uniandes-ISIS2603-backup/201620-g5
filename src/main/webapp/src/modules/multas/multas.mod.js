(function (ng) {
    var mod = ng.module("multasModule", []);
    mod.constant("multasContext", "api/multas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/multas/';
            $urlRouterProvider.otherwise("/multasList");
     
            $stateProvider.state('multasList', {
                url: '/multas',
                parent: 'usuarioEdit',
                views: {
                    'usuarioInstanceView': {
                        controller: 'multasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'multas.list.html'
                    }
                }
            }).state('multaCreate', {
                url: '/multas/create',
                parent: 'usuarioEdit',
                views: {
                    'usuarioInstanceView': {
                        controller: 'multasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'multas.create.html'
                    }
                }

            }).state('multaEdit', {
                url: '/multas/:multaId',
                parent: 'usuarioEdit',
                param: {
                    multaId: null
                },
                views: {
                    'usuarioInstanceView': {
                        controller: 'multasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'multas.create.html'
                    }
                }
            });
        }]);
})(window.angular);