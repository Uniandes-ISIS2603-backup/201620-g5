(function (ng) {
    var mod = ng.module("bibliotecasModule", ["ngMessages" , "ui.router"]);
    mod.constant("bibliotecasContext", "api/bibliotecas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/bibliotecas/';
            $urlRouterProvider.otherwise("/bibliotecasList");
     
     
                $stateProvider.state('bibliotecas',{
                url:'/bibliotecas',
                abstract:true,
                views:{
                    'mainView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.html'
                    }
                }
            }).state('bibliotecasList', {
                url: '/list',
                parent: 'bibliotecas',
                views: {
                    'bibliotecaView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.list.html'
                    }
                }
            }).state('bibliotecaCreate', {
                parent: 'bibliotecas',
                url: '/create',
                views: {
                    'bibliotecaView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.create.html'
                    }
                }

            }).state('bibliotecaEdit', {
                parent: 'bibliotecas',
                url: '/edit/:bibliotecaId',
                param: {
                    bibliotecaId: null
                },
                views: {
                    'bibliotecaView': {
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'bibliotecas.create.html'
                    }
                }
            }).state('bibliotecaPrestar', {
                parent: 'bibliotecas',
               url: '/:bibliotecaId',
                param: {
                bibliotecaId: null
               },
               views: {
                 'bibliotecaView': {
                        
                    },
                    'childsView': {
                        templateUrl: basePath + 'bibliotecas.instance.html'
                    }
                }
             
          })
            ;
        }]);
})(window.angular);


