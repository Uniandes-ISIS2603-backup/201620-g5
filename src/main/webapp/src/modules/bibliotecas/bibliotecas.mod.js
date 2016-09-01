(function (ng) {
    var mod = ng.module("bibliotecasModule", ["ngMessages" , "ui.router"]);
    mod.constant("bibliotecasContext", "api/bibliotecas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/bibliotecas/';
            $urlRouterProvider.otherwise("/bibliotecasList");
     
            $stateProvider.state('bibliotecasList', {
                url: '/bibliotecas',
                views: {
                    'mainView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.list.html'
                    }
                }
            }).state('bibliotecaCreate', {
                url: '/bibliotecas/create',
                views: {
                    'mainView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.create.html'
                    }
                }

            }).state('bibliotecaEdit', {
                url: '/bibliotecas/:bibliotecaId',
                param: {
                    bibliotecaId: null
                },
                views: {
                    'mainView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.create.html'
                    }
                }
            });
        }]);
})(window.angular);


