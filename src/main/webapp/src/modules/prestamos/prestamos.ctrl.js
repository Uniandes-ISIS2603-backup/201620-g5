(function (ng) {
    var mod = ng.module("usuariosModule");

    mod.controller("prestamosCtrl", ['$scope', '$state', '$stateParams', '$http','usuariosContext',  
        function ($scope, $state, $stateParams, $http, usuariosContext ) {

            // inicialmente el listado de prestamos
            //  está vacio
            $scope.prestamosContext = '/prestamos';
            $scope.prestamos = {};
            // carga las prestamos
            $http.get(prestamosContext + "/" + $stateParams.usuarioId + $scope.prestamosContext).then(function (response) {
                $scope.prestamos = response.data;
            }, responseError);

            // el controlador recibió un prestamoId ??
            // revisa los parámetros (ver el :prestamoId en la definición de la ruta)
            if ($stateParams.prestamoId !== null && $stateParams.prestamoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.prestamoId;
                // obtiene el dato del recurso REST
                $http.get(usuariosContext + "/" + $stateParams.usuarioId +$scope.prestamosContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentPrestamo
                            $scope.currentPrestamo = response.data;
                        }, responseError);

                // el controlador no recibió un prestamoId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentPrestamo = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
               
                   
                };

                $scope.alerts = [];
            }

           
            
            this.savePrestamo = function (id) {
                currentPrestamo = $scope.currentPrestamo;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(usuariosContext + "/" + $stateParams.usuarioId + $scope.prestamosContext, currentPrestamo)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('prestamosList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(usuariosContext + "/" + $stateParams.usuarioId + $scope.prestamosContext + "/" + currentPrestamo.id, currentPrestamo)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('prestamosList');
                            }, responseError);
                }
                ;
            };
            
             this.deletePrestamo = function (prestamo) {
                return $http.delete(context + "/" + prestamo.prestamoId)
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