(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "bibliotecasModule",
        "librosModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/bibliotecasList');
            $urlRouterProvider.otherwise('/librosList');
        }]);

  
})(window.angular);


