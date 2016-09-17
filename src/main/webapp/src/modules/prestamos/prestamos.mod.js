(function () {
    var mod = angular.module('prestamosModule',[]);
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
                parent: 'bibliotecaPrestar',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'prestamosBiblioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'prestamosBiblio.create.html'
                    }
                }

            })
            .state('prestamosBiblioList', {
                url: '/prestamos',
                parent: 'bibliotecaPrestar',
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'prestamosBiblioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'prestamosBiblio.list.html'
                    }
                }

            }).state('prestamoEdit', {
                url: '/prestamos/edit/:prestamoId',
                parent: 'usuarioEdit',
                param: {
                    prestamoId: null
                },
                views: {
                    'usuarioInstanceView': {
                        controller: 'prestamosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'prestamos.create.html'
                    }
                }
            }).state('prestamoBiblioEdit', {
                url: '/prestamos/edit/:prestamoId',
                parent: 'bibliotecaPrestar',
                param: {
                    prestamoId: null
                },
                views: {
                    'bibliotecaInstanceView': {
                        controller: 'prestamosBiblioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'prestamosBiblio.create.html'
                    }
                }
            });
        }]);
})(window.angular);