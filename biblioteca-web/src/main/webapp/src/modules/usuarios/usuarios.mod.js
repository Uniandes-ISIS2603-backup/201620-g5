(function (ng) {
    var mod = ng.module("usuariosModule", ["ngMessages", "ui.router"]);
    mod.constant("usuariosContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            $urlRouterProvider.otherwise("/usuariosList");
     
            $stateProvider.state('usuarios',{
                url:'/usuarios',
                abstract:true,
                views:{
                    'mainView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios.html'
                    }
                }
            }).state('usuariosList', {
                url: '/list',
                parent:'usuarios',
                views: {
                    'usuarioView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios.list.html'
                    }
                }
            }).state('usuarioCreate', {
                parent:'usuarios',
                url: '/create',
                views: {
                    'usuarioView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios.create.html'
                    }
                }

            }).state('usuarioEdit', {
                parent:'usuarios',
                url: '/:usuarioId',
                param: {
                    usuarioId: null
                },
                views: {
                    'usuarioView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios.create.html'
                    },
                    'childsView': {
                        templateUrl: basePath + 'usuarios.instance.html'
                    }
                }
            });
        }]);
})(window.angular);
