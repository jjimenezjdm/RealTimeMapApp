
var express = require('express')
var app = express()

var http = require('http').Server(app);
var io = require('socket.io')(http);

var redis = require("redis"),
    redisSub = redis.createClient(6379, host = "redis-master");

// Damos acceso a la carpeta donde está la app de Angular
app.use(express.static('public'))

// Nos subscribimos a los cambios de Redis y los publicamos en Socket.IO
redisSub.on("message", function (channel, message) {
    console.log("sub channel " + channel + ": " + message);
    io.sockets.emit('locations', message);
});

redisSub.subscribe("locations");

// Mostramos los clientes conectados a nuestro mapa
io.on('connection', function (socket) {
    console.log(socket.id);
});

// En caso de error de redis
redisSub.on("error", function (err) {
    console.log("Error " + err);
});

// Comenzamos a escuchar por el puerto
http.listen(8080, function () {
    console.log('Map App listening on port 3000!')
})
