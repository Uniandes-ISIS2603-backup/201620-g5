(function (ng) {
    var mod = ng.module("bibliotecasModule");

    mod.controller("bibliotecasCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de bibliotecas está vacio
            $scope.bibliotecas = {};
            // carga las bibliotecas
            $http.get(context).then(function(response){
                $scope.bibliotecas = response.data;    
            }, responseError);

            // el controlador recibió un bibliotecaId ??
            // revisa los parámetros (ver el :bibliotecaId en la definición de la ruta)
            if ($stateParams.bibliotecaId !== null && $stateParams.bibliotecaId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.bibliotecaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentBiblioteca = response.data;
                    }, responseError);

            // el controlador no recibió un cityId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentBiblioteca = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    recursos: undefined /*Tipo Recurso*/,
                    prestamos: undefined /*Tipo Prestamo*/,
                    multas: undefined /*Tipo Multa*/,
                    reservas: undefined /*Tipo Recurso*/
                };
              
                $scope.alerts = [];
            }


            this.saveBiblioteca = function (id) {
                currentBiblioteca = $scope.currentBiblioteca;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentBiblioteca)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('bibliotecasList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentBiblioteca.id, currentBiblioteca)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('bibliotecasList');
                        }, responseError);
                };
            };

            this.deleteBiblioteca = function( biblioteca) {
                return $http.delete(context + "/" + biblioteca.bibliotecaId)
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


