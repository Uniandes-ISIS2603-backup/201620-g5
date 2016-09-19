(function (ng) {
    var mod = ng.module("librosModule");

    mod.controller("blogsCtrl", ['$scope', '$state', '$stateParams', '$http','librosContext',  
        function ($scope, $state, $stateParams, $http, librosContext ) {

            $scope.blogsContext = '/blogs';
            $scope.blogs = {};
            
            $http.get(blogs + "/" + $stateParams.libroId + $scope.blogsContext).then(function (response) {
                $scope.blogs = response.data;
            }, responseError);

            if ($stateParams.blogId !== null && $stateParams.blogId !== undefined) {

                id = $stateParams.blogId;
                // obtiene el dato del recurso REST
                $http.get(librosContext + "/" + $stateParams.librosId +$scope.blogsContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentblog
                            $scope.currentBlog = response.data;
                        }, responseError);

                // el controlador no recibió un blogId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentBlog = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
               
                   
                };

                $scope.alerts = [];
            }

           
            
            this.saveBlog = function (id) {
                currentblog = $scope.currentblog;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(librosContext + "/" + $stateParams.libroId + $scope.blogsContext, currentblog)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('blogsList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(librosContext + "/" + $stateParams.libroId + $scope.blogsContext + "/" + currentblog.id, currentblog)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('blogsList');
                            }, responseError);
                }
                ;
            };
            
             this.deleteblog = function (blog) {
                return $http.delete(context + "/" + blog.blogId)
                    .then(function () {
                        // cuando termine bien, cambie de estado
                        $state.reload();
                    }, responseError);
            };
            


            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);