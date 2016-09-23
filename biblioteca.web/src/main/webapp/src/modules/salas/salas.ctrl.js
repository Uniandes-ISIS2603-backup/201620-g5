(function (ng) {
    var mod = ng.module("salasModule");

    mod.controller("salasCtrl", ['$scope', '$state', '$stateParams', '$http', 'salasContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.salas = {};
            $http.get(context).then(function(response){
                $scope.salas = response.data;    
            }, responseError);

            // el controlador recibió un salaId ??
            // revisa los parámetros (ver el :salaId en la definición de la ruta)
            if ($stateParams.salaId !== null && $stateParams.salaId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.salaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentSala = response.data;
                    }, responseError);

            // el controlador no recibió un salaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentSala = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    idBiblioteca: undefined,
                    capacidad: undefined
                };
              
                $scope.alerts = [];
            }


            this.saveSala = function (id) {
                currentSala = $scope.currentSala;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post("api/bibliotecas" + "/salas", currentSala)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('salasList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentSala.id, currentSala)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('salasList');
                        }, responseError);
                };
            };
            
            this.deleteSala = function( sala) {
                return $http.delete(context + "/" + sala.salaId)
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

