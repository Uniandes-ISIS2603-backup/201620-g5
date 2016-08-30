(function (ng) {
    var mod = ng.module("usuariosModule");

    mod.controller("usuariosCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuariosContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.usuarios = {};
            $http.get(context).then(function(response){
                $scope.usuarios = response.data;    
            }, responseError);

            // el controlador recibió un usuarioId ??
            // revisa los parámetros (ver el :usuarioId en la definición de la ruta)
            if ($stateParams.usuarioId !== null && $stateParams.usuarioId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.usuarioId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentusuario = response.data;
                    }, responseError);

            // el controlador no recibió un usuarioId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentusuario = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    lastname: '',
                    password: '',
                    direction: ''
                };
              
                $scope.alerts = [];
            }


            this.saverecord = function (id) {
                currentusuario = $scope.currentusuario;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentusuario)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('usuariosList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentusuario.id, currentusuario)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('usuariosList');
                        }, responseError);
                };
            };



            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

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