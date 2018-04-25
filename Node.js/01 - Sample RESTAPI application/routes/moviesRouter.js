var express = require('express');
/**
 * movie controller will act as a mediator between request and models
 * controllers will execute model functions and send results back to the client
 */
var moviesController = require('./../controllers/moviesController');

/**
 * this will create a router object which we can use in app.use()
 * we included this router object in app.js
 */
var moviesRouter = express.Router();

/**
 * we can define routes for our router object
 * since we pass all the request types to router we need to filter and map them
 * here when we are mapping we need to provide a url
 * since in the app.js we mapped all the urls start with '/movies'
 * '' => '/movies' + '' = '/movies'
 * ':id' => '/movies' + ':id' = '/movies/:id' 
 * get, post, patch, put, delete functions used to map relevant http requests
 * eg: moviesRouter.route('').get(moviesController.get) will map GET request to '/movies' to moviesController.get method
 */
moviesRouter.route('')
    .get(moviesController.get)
    .post(moviesController.add);

moviesRouter.route('/:id')
    .get(moviesController.getById)
    .put(moviesController.update)
    .patch(moviesController.update)
    .delete(moviesController.remove);

module.exports = moviesRouter;