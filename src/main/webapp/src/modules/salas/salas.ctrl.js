(function (ng) {
    var mod = ng.module("salasModule");

    mod.controller("salasCtrl", ['$scope', '$state', '$stateParams', '$http', 'salasContext', function ($scope, $state, $stateParams, $http, context) {


            $scope.salas = {};
            $http.get(context).then(function(response){
                $scope.salas = response.data;    
            }, responseError);

            if ($stateParams.salaId !== null && $stateParams.salaId !== undefined) {
                

                id = $stateParams.salaId;
                $http.get(context + "/" + id)
                    .then(function (response) {
                        $scope.currentSala = response.data;
                    }, responseError);

            } else
            {

                $scope.currentSala = {
                    id: undefined,
                    nombre: '',
                    
                };
              
                $scope.alerts = [];
            }


            this.saveSala = function (id) {
                currentSala = $scope.currentSala;
                
                
                if (id == null) {

                    return $http.post(context, currentSala)
                        .then(function () {
                            $state.go('salasList');
                        }, responseError);
                        
                } else {
                    
                    return $http.put(context + "/" + currentSala.id, currentSala)
                        .then(function () {
                            $state.go('salasList');
                        }, responseError);
                };
            };
            
            this.deleteSala = function(sala) {
                return $http.delete(context + "/" + sala.salaId)
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
