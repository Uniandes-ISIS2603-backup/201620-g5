(function (ng) {
    
    var mod = angular.module('prestamosModule',[]
    );
    mod.constant("prestamosContext", "/prestamos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/prestamos/';
            $urlRouterProvider.otherwise("/prestamosList");
     
            $stateProvider.state('prestamosList', {
                url: '/prestamos',
                parent: 'usuarioEdit',
                views: {
                    'usuarioInstanceView': {
                        controller: 'prestamosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'prestamos.list.html'
                    }
                }
            }).state('prestamoCreate', {
                url: '/prestamos/create',
                parent: 'usuarioEdit',
                views: {
                    '}usuarioInstanceView': {
                        controller: 'prestamosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'prestamos.create.html'
                    }
                }

            });
        }]);
})(window.angular);