
var app = angular.module('app', ['btford.socket-io'])

    .factory('mySocket', function (socketFactory) {
        return socketFactory();
    })

    .controller('MainController', function ($scope, mySocket) {

        // markers existentes
        var markers = {}

        // Añadimos el mapa
        var mymap = L.map('mapid').setView([39.471571, -6.377001], 13);
        L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(mymap);

        // Nos subscribimos a los cambios
        mySocket.on('locations', function(data){
            var newLocation = JSON.parse(data);
            $scope.locations = newLocation;
            console.log(newLocation.id);
            if(markers[newLocation.id] !== undefined){ // existe el marker, moverlo de sitio
                markers[newLocation.id].setLatLng(newLocation.position)
            } else { // no existe el marker, crearlo y guardarlo
                markers[newLocation.id] = L.marker(newLocation.position).addTo(mymap);
            }
        });
    });