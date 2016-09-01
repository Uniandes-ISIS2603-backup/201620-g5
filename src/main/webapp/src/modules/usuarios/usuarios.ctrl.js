(function (ng) {
    var mod = ng.module("usuariosModule");

    mod.controller("usuariosCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuariosContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.usuarios = {};
            $http.get(context).then(function(response){
                $scope.usuarios = response.data;    
            }, responseError);

            if ($stateParams.usuarioId !== null && $stateParams.usuarioId !== undefined) {
                

                id = $stateParams.usuarioId;
                $http.get(context + "/" + id)
                    .then(function (response) {
                        $scope.currentUsuario = response.data;
                    }, responseError);

            } else
            {

                $scope.currentUsuario = {
                    id: undefined,
                    nombre: '',
                    apellido: '',
                    login: '',
                    contrasenha: '',
                    direccion: '',
                };
              
                $scope.alerts = [];
            }


            this.saveUsuario = function (id) {
                currentUsuario = $scope.currentUsuario;
                
                
                if (id == null) {

                    return $http.post(context, currentUsuario)
                        .then(function () {
                            $state.go('usuariosList');
                        }, responseError);
                        
                } else {
                    
                    return $http.put(context + "/" + currentUsuario.id, currentUsuario)
                        .then(function () {
                            $state.go('usuariosList');
                        }, responseError);
                };
            };
            
            this.deleteUsuario = function( usuario) {
                return $http.delete(context + "/" + usuario.usuarioId)
                    .then(function () {
                        $state.reload();
                    }, responseError);
            };


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
