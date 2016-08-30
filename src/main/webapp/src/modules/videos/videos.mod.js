(function (ng) {
    var mod = ng.module("videosModule", ["ngMessages"]);
    mod.constant("videosContext", "api/videos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/videos/';
            $urlRouterProvider.otherwise("/videosList");
     
            $stateProvider.state('videosList', {
                url: '/videos',
                views: {
                    'mainView': {
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'videos.list.html'
                    }
                }
            }).state('videoCreate', {
                url: '/videos/create',
                views: {
                    'mainView': {
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'videos.create.html'
                    }
                }

            }).state('videoEdit', {
                url: '/videos/:videoId',
                param: {
                    videoId: null
                },
                views: {
                    'mainView': {
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'videos.create.html'
                    }
                }
            });
        }]);
})(window.angular);