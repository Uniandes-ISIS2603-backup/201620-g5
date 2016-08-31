(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "bibliotecasModule",
        "librosModule",
        "videosModule",
        "reservasModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/bibliotecasList');
            $urlRouterProvider.otherwise('/librosList');
            $urlRouterProvider.otherwise('/videosList');
<<<<<<< HEAD
            $urlRouterProvider.otherwise('/reservasList')

            
=======
>>>>>>> 1a27cb80becb2da3c469c1a4cce5f6756a425bc5
        }]);
  
})(window.angular);


