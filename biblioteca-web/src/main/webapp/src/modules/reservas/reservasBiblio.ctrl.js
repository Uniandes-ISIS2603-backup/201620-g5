(function (ng) {
    var mod = ng.module("bibliotecasModule");

    mod.controller("reservasBiblioCtrl", ['$scope', '$state', '$stateParams', '$http','bibliotecasContext', 'usuariosContext', 'videosContext','librosContext','salasContext',
        function ($scope, $state, $stateParams, $http, bibliotecasContext, usuariosContext, videosContext, librosContext, salasContext ) {
            
    
            // inicialmente el listado de reservas
            //  está vacio
            $scope.reservasContext = '/reservas';
            librosContext='/libros';
            videosContext='/videos';
            salasContext='/salas';
            $scope.reservas = {};
            // carga las reservas
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId + $scope.reservasContext).then(function (response) {
                $scope.reservas = response.data;
            }, responseError);

            // el controlador recibió un reservaId ??
            // revisa los parámetros (ver el :reservaId en la definición de la ruta)
            if ($stateParams.reservaId !== null && $stateParams.reservaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.reservaId;
                console.log(id);
                // obtiene el dato del recurso REST
                $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId +$scope.reservasContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentReserva
                            $scope.currentReserva = response.data;
                        }, responseError);

                // el controlador no recibió un reservaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentReserva = {
                    id: undefined //Tipo Long. El valor se asigna en el backend,
                    
                   
                };

                $scope.alerts = [];
            }
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId + videosContext).then(function (response) {
                $scope.videos = response.data;
            });
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId + librosContext).then(function (response) {
                $scope.libros = response.data;
            });
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId + salasContext).then(function (response) {
                $scope.salas = response.data;
            });
            
            $http.get(usuariosContext).then(function (response) {
                $scope.usuarios = response.data;
            });
           
            
            this.saveReserva = function (id) {
                $scope.currentReserva.fecha = $scope.dateTo.format('DD/MM/YYYY hh:mm A');
                console.log($scope.currentReserva);
                currentReserva = $scope.currentReserva;
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(bibliotecasContext + "/" + $stateParams.bibliotecaId + "/tipoRecurso/"+ currentReserva.tipoRecurso + "/recurso/"+ currentReserva.recurso.id + "/usuario/" + currentReserva.usuario.id +  $scope.reservasContext, currentReserva)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('reservasBiblioList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(bibliotecasContext + "/" + $stateParams.bibliotecaId + $scope.reservasContext + "/" + currentReserva.id, currentReserva)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('reservasBiblioList');
                            }, responseError);
                }
                ;
            };
            
             this.deleteReserva = function (reserva) {
                return $http.delete(bibliotecasContext + "/" + $stateParams.bibliotecaId + $scope.reservasContext + "/" + reserva.reservaId)
                    .then(function () {
                        // cuando termine bien, cambie de estado
                        $state.reload();
                    }, responseError);
            };
            


            // -----------------------------------------------------------------
            // Funciones para manejar las fechas

            $scope.popup = {
                opened: false
            };
             $scope.popup2 = {
                opened: false
            };
           
            $scope.dateOptions = {
                dateDisabled: false,
                formatYear: 'yyyy',
                maxDate: new Date(2020,5,22),
                minDate: new Date(),
                startingDay: 1
            };

             $scope.dateOptions2 = {
                dateDisabled: false,
                formatYear: 'yy',
                maxDate: new Date(2020, 5, 22),
                minDate: new Date(),
                startingDay: 1
            };

            this.today = function () {
                $scope.dt = new Date();
            };
            this.today();

            this.clear = function () {
                $scope.dt = null;
            };
            this.setDate = function (year, month, day) {
                $scope.dt = new Date(year, month, day);
            };

            this.open = function (fechaFinal) {
                
                $scope.popup.opened = true;
                if(fechaFinal != null)
                {
                    $scope.dateOptions.maxDate = fechaFinal;
                }
                
            };
            
             this.open2 = function (fechaInicial) {
                $scope.popup2.opened = true;
                if(fechaInicial != null)
                {
                    $scope.dateOptions2.minDate = fechaInicial;
                }
                
            };




            // Funciones para manejar los mensajes en la aplicación


             $scope.dateFrom = moment().add(-1, 'd');
            $scope.dateTo = moment();
            
            $scope.optionsFrom = {format: 'DD/MM/YYYY hh:mm A'};
            $scope.optionsTo = {format: 'DD/MM/YYYY hh:mm A'};
           $(function () {
            $('#fechaInicial').datetimepicker({
                format: 'DD/MM/YYYY hh:mm A'
                });

              
            });
            
            this.update = function (dateFromm, dateToo) {
                $scope.optionsFrom.maxDate = dateToo;
                $scope.optionsTo.minDate = dateFromm;
            };
            this.update($scope.dateFrom, $scope.dateTo);

            

            
            $(function () {
            $('#fechaFinal').datetimepicker({
                format: 'DD/MM/YYYY hh:mm A'
                });

               
            });
        

            // Funciones para manejar los mensajes en la aplicación


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
