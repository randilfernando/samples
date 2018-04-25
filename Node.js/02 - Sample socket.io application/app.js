var express = require('express');
var app = express();

//Since jquery is using inside html files and jquery locally installed
app.use(express.static(__dirname + '/node_modules'));
app.use(express.static(__dirname + '/assets'));

app.get('/', function (req, res, next) {
    res.sendFile(__dirname + '/index.html');
});

var server = app.listen(process.env.PORT || 4200, function (err) {
    if (!err) {
        console.log(`Server is running on port 4200`); //Use backtick to call "variable" inside string
    }
});

var io = require('socket.io').listen(server);

io.on('connection', function (client) {
    client.on('join', function (data) {
        console.log(data);
    });

    client.on('messages', function (data) {
        console.log(data);
        client.emit('broad', data);
        client.broadcast.emit('broad', data);
    });
})