(function (ng) {
    var mod = ng.module("bibliotecasModule");

    mod.controller("prestamosBiblioCtrl", ['$scope', '$state', '$stateParams', '$http','bibliotecasContext', 'usuariosContext', 'videosContext','librosContext','salasContext',
        function ($scope, $state, $stateParams, $http, bibliotecasContext, usuariosContext, videosContext, librosContext, salasContext ) {
            
           
            // inicialmente el listado de prestamos
            //  está vacio
            $scope.prestamosContext = '/prestamos';
            librosContext = '/libros';
            videosContext = '/videos';
            salasContext = '/salas';
            $scope.prestamos = {};
            // carga las prestamos
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId + $scope.prestamosContext).then(function (response) {
                $scope.prestamos = response.data;
            }, responseError);

            // el controlador recibió un prestamoId ??
            // revisa los parámetros (ver el :prestamoId en la definición de la ruta)
            if ($stateParams.prestamoId !== null && $stateParams.prestamoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.prestamoId;
                console.log(id);
                // obtiene el dato del recurso REST
                $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId +$scope.prestamosContext + "/" + id)
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
                    id: undefined,//Tipo Long. El valor se asigna en el backend,

                };

                $scope.alerts = [];
            }
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId + videosContext).then(function (response) {
                $scope.videos = response.data;
            });
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId +librosContext).then(function (response) {
                $scope.libros = response.data;
            });
            $http.get(bibliotecasContext + "/" + $stateParams.bibliotecaId +salasContext).then(function (response) {
                $scope.salas = response.data;
            });
            
            $http.get(usuariosContext).then(function (response) {
                $scope.usuarios = response.data;
            });
           
            
            this.savePrestamo = function (id) {
                $scope.currentPrestamo.fechaInicial = $scope.dateTo.format('DD/MM/YYYY hh:mm A');
                $scope.currentPrestamo.fechaFinal = $scope.dateFrom.format('DD/MM/YYYY hh:mm A');
                console.log($scope.currentPrestamo);
                currentPrestamo = $scope.currentPrestamo;
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == undefined) {

          
                    // ejecuta POST en el recurso REST
                    return $http.post(bibliotecasContext + "/" + $stateParams.bibliotecaId + "/tipoRecurso/"+ currentPrestamo.tipoRecurso + "/recurso/"+ currentPrestamo.recurso.id + "/usuario/" + currentPrestamo.usuario.id +  $scope.prestamosContext, currentPrestamo)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('prestamosBiblioList');
                            }, responseError);
                      

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(bibliotecasContext + "/" + $stateParams.bibliotecaId + $scope.prestamosContext + "/" + currentPrestamo.id, currentPrestamo)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('prestamosBiblioList');
                            }, responseError);
                }
                ;
            };
            
             this.deletePrestamo = function (prestamo) {
                return $http.delete(bibliotecasContext + "/" + $stateParams.bibliotecaId + $scope.prestamosContext + "/" + prestamo.prestamoId)
                    .then(function () {
                        // cuando termine bien, cambie de estado
                        $state.reload();
                    }, responseError);
            };
            
            // -----------------------------------------------------------------
            // Funciones para manejar las fechas

           
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
   // mod.config(['$httpProvider', function ($httpProvider) {

    // ISO 8601 Date Pattern: YYYY-mm-ddThh:MM:ss
   // var dateMatchPattern = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}/;

  // var convertDates = function (obj) {
      //for (var key in obj) {
       //  if (!obj.hasOwnProperty(key)) continue;

      //   var value = obj[key];
      //   var typeofValue = typeof (value);

      //   if (typeofValue === 'object') {
            // If it is an object, check within the object for dates.
      //      convertDates(value);
     //    } else if (typeofValue === 'string') {
      //      if (dateMatchPattern.test(value)) {
      //         obj[key] = new Date(value);
     //       }
     //    }
    //  }
  // };

  // $httpProvider.defaults.transformResponse.push(function (data) {
  //    if (typeof (data) === 'object') {
   //      convertDates(data);
   //   }

  //    return data;
  // });
//}]);
})(window.angular);
