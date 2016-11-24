(function (ng) {
    var mod = ng.module("videosModule");

    mod.controller("videosCtrl", ['$scope', '$state', '$stateParams', '$http', 'videosContext','bibliotecasContext', function ($scope, $state, $stateParams, $http, context, bibliotecasContext) {

            $scope.videos = {};
            $http.get(context).then(function (response) {
                $scope.videos = response.data;
            }, responseError);

            // el controlador recibió un videoId ??
            // revisa los parámetros (ver el :videoId en la definición de la ruta)
            if ($stateParams.videoId !== null && $stateParams.videoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.videoId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentVideo = response.data;
                        }, responseError);

                // el controlador no recibió un videoId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentVideo = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }

            $http.get(bibliotecasContext).then(function (response) {
                $scope.bibliotecas = response.data;
            });

            this.saveVideo = function (id) {
                currentVideo = $scope.currentVideo;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentVideo)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('videosList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentVideo.id, currentVideo)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('videosList');
                            }, responseError);
                }
                ;
            };

            this.deleteVideo = function (video) {
                return $http.delete(context + "/" + video.videoId)
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

