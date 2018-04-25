/**
 * minimalist node.js framework here we use express to create our application
 * for further information and documentation about express.js please visit https://expressjs.com/
 * body-parser package used to parse http body
 * in this application we use json middleware in body-parser library to parse our json requests
 * since this is a REST API all the requests and responses are passed via JSON objects
 * node uses CommonJS module load format to import and export modules
 */
var express = require('express');
var bodyParser = require('body-parser');

/**
 * router implementation please see: routes/movieRouter.js
 * movieRouter contains all the mapping between routes and relevant functions
 */
var moviesRouter = require('./routes/moviesRouter');

/**
 * this will create the express application
 * we can use app.use to identify express to use functions
 * here we define express to use bodyParser.json() function for all requests
 * whenever there is a request to our application it first go through the bodyParser.json() function
 */
var app = express();
app.use(bodyParser.json());

/**
 * this will describe about how the middlewares working
 * in node we can define a middleware like this (eg: bodyParser.json())
 * middleware has 3 arguments req, res and next
 * next arguments will contain the next function that need to be called (eg: function to handle http request)
 */
app.use(function (req, res, next) {
    console.log(req.method + ":" + req.url);
    next();
});

/**
 * rather than defining middlewares for all the requests we can define the scope
 * in here rather than providing a middleware we provide a express.Router object
 * whenever a requst mage to url starting with '/movies' the request is passes to moviesRouter
 * this step is not checking the request method inside router we can filter and map different request methods
 */
app.use('/movies', moviesRouter);

/**
 * finally after creating the application and defining the routes application is started
 * callback function is passed notify after the application is started (since node.js is asynchronous we need to pass a call)
 */
app.listen(3000, function () {
    console.log('Server is running on localhost:3000');
});