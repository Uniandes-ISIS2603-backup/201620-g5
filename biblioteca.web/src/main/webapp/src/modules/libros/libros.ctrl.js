(function (ng) {
    var mod = ng.module("librosModule");

    mod.controller("librosCtrl", ['$scope', '$state', '$stateParams', '$http', 'librosContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.libros = {};
            $http.get(context).then(function(response){
                $scope.libros = response.data;    
            }, responseError);

            // el controlador recibió un libroId ??
            // revisa los parámetros (ver el :libroId en la definición de la ruta)
            if ($stateParams.libroId !== null && $stateParams.libroId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.libroId;
                console.log(id);
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentLibro = response.data;
                    }, responseError);

            // el controlador no recibió un libroId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentLibro = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '',
                };
              
                $scope.alerts = [];
            }


            this.saveLibro = function (id) {
                currentLibro = $scope.currentLibro;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentLibro)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('librosList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentLibro.id, currentLibro)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('librosList');
                        }, responseError);
                };
            };
            
            this.deleteLibro = function(libro) {
                return $http.delete(context + "/" + libro.libroId)
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
