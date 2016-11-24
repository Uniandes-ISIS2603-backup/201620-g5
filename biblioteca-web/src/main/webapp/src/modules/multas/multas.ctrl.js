(function (ng) {
    var mod = ng.module("multasModule");

    mod.controller("multasCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuariosContext','multasContext', function ($scope, $state, $stateParams, $http,usuariosContext, context) {



            $scope.multas = {};
            $http.get("api/usuarios/"+$stateParams.usuarioId + "/multas").then(function (response) {
                $scope.multas = response.data;
            }, responseError);

            // el controlador recibió un salaId ??
            // revisa los parámetros (ver el :salaId en la definición de la ruta)
            if ($stateParams.multaId !== null && $stateParams.multaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.multaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentMulta = response.data;
                        }, responseError);

                // el controlador no recibió un salaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentMulta = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    idUsuario: '' /*Tipo String*/,
                    idBiblioteca: undefined,
                    idRecurso: undefined,
                    costo: undefined,
                    fecha: ''
                };

                $scope.alerts = [];
            }

            $http.get(usuariosContext + "/" + $stateParams.usuarioId + "/videos").then(function (response) {
                $scope.videos = response.data;
            });
            $http.get(usuariosContext + "/" + $stateParams.usuarioId +"/libros").then(function (response) {
                $scope.libros = response.data;
            });
            $http.get(usuariosContext + "/" + $stateParams.usuarioId +"/salas").then(function (response) {
                $scope.salas = response.data;
            });
            
            this.saveMulta = function (id) {
                currentMulta = $scope.currentMulta;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post("api/usuarios" + $stateParams.usuarioId + "/multas/idRecurso/" + currentMulta.recurso.id + "/" + currentMulta.tipoRecurso + "" , currentMulta)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('multasList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentMulta.id, currentMulta)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('multasList');
                            }, responseError);
                }
                ;
            };

            this.deleteMulta = function (multa) {
                return $http.delete(context + "/" + multa.multaId)
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
                maxDate: new Date(2020, 5, 22),
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
                if (fechaFinal != null)
                {
                    $scope.dateOptions.maxDate = fechaFinal;
                }

            };

            this.open2 = function (fechaInicial) {
                $scope.popup2.opened = true;
                if (fechaInicial != null)
                {
                    $scope.dateOptions2.minDate = fechaInicial;
                }

            };




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
    mod.config(['$httpProvider', function ($httpProvider) {

            // ISO 8601 Date Pattern: YYYY-mm-ddThh:MM:ss
            var dateMatchPattern = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}/;

            var convertDates = function (obj) {
                for (var key in obj) {
                    if (!obj.hasOwnProperty(key))
                        continue;

                    var value = obj[key];
                    var typeofValue = typeof (value);

                    if (typeofValue === 'object') {
                        // If it is an object, check within the object for dates.
                        convertDates(value);
                    } else if (typeofValue === 'string') {
                        if (dateMatchPattern.test(value)) {
                            obj[key] = new Date(value);
                        }
                    }
                }
            }

            $httpProvider.defaults.transformResponse.push(function (data) {
                if (typeof (data) === 'object') {
                    convertDates(data);
                }

                return data;
            });
        }]);

})(window.angular);

