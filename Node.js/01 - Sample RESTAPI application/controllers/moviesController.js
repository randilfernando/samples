var Movie = require('./../models/movieModel');

/**
 * above are normal javascriot functions
 * only difference is req and res objects
 * since we binded these functiosn to http methods express will pass http request and http response objects
 * we can use http request (req) object to access request atrtibutes
 * eg: req.body, req.params, req.query
 * we can use http response (res) object to set modify http response object
 * eg: res.status(200), res.send({success: true})
 */

var get = function (req, res) {
    const movies = Movie.find();
    res.status(200);
    res.send(movies);
}

var getById = function (req, res) {
    const movie = Movie.findById(+req.params.id);
    if (movie) {
        res.status(200);
        res.send(movie);
    } else {
        res.status(404);
        res.send();
    }
}

var add = function (req, res) {
    const _id = Movie.add(req.body);
    res.status(201);
    res.send({
        _id: _id
    })
}

var update = function (req, res) {
    Movie.update(req.params.id, req.body);
    res.status(204);
    res.send();
}

var remove = function (req, res) {
    Movie.remove(req.params.id);
    res.status(204);
    res.send();
}

module.exports = {
    get: get,
    add: add,
    getById: getById,
    update: update,
    remove: remove
}