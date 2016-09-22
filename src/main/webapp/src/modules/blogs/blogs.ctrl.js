(function (ng) {
    var mod = ng.module("librosModule");

    mod.controller("blogsCtrl", ['$scope', '$state', '$stateParams', '$http','librosContext', 'usuariosContext', 'videosContext','librosContext','salasContext',
        function ($scope, $state, $stateParams, $http, librosContext, usuariosContext, videosContext, librosContext, salasContext ) {
            
    
            // inicialmente el listado de blogs
            //  está vacio
            $scope.blogsContext = '/blogs';
            $scope.blogs = {};
            // carga las blogs
            $http.get(librosContext + "/" + $stateParams.libroId + $scope.blogsContext).then(function (response) {
                $scope.blogs = response.data;
            }, responseError);

            // el controlador recibió un blogId ??
            // revisa los parámetros (ver el :blogId en la definición de la ruta)
            if ($stateParams.blogId !== null && $stateParams.blogId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.blogId;
                // obtiene el dato del recurso REST
                $http.get(librosContext + "/" + $stateParams.libroId +$scope.blogsContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentblog
                            $scope.currentblog = response.data;
                        }, responseError);

                // el controlador no recibió un blogId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentblog = {
                    id: undefined //Tipo Long. El valor se asigna en el backend,
                    
                   
                };

                $scope.alerts = [];
            }
           
            
            this.saveblog = function (id) {
                currentblog = $scope.currentblog;
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(librosContext + "/" + $stateParams.libroId + $scope.blogsContext, currentblog)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('blogsList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(librosContext + "/" + $stateParams.libroId + $scope.blogsContext + "/" + currentblog.id, currentblog)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('blogsList');
                            }, responseError);
                }
                ;
            };
            
             this.deleteblog = function (blog) {
                return $http.delete(librosContext + "/" + $stateParams.libroId + $scope.blogsContext + "/" + blog.blogId)
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
         if (!obj.hasOwnProperty(key)) continue;

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
