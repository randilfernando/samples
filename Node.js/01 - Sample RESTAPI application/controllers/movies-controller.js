const Movie = require('../models/movie-model').get();

/**
 * above are normal javascriot functions
 * only difference is req and res objects
 * since we binded these functiosn to http methods express will pass http request and http response objects
 * we can use http request (req) object to access request atrtibutes
 * eg: req.body, req.params, req.query
 * we can use http response (res) object to set modify http response object
 * eg: res.status(200), res.send({success: true})
 */

const get = function (req, res) {
    Movie.findAll()
        .then(movies => {
            res.status(200);
            res.send(movies);
        })
        .catch(err => {
            res.status(500);
            res.send(err);
        });
};

const getById = function (req, res) {
    Movie.findById(+req.params.id)
        .then(movie => {
            if (movie) {
                res.status(200);
                res.send(movie);
            } else {
                res.status(404);
                res.send();
            }
        })
        .catch(err => {
            res.status(500);
            res.send(err);
        });
};

const add = function (req, res) {
    const movie = req.body;
    Movie.create(movie, {
        isNewRecord: true
    })
        .then(result => {
            res.status(200);
            res.send({
                _id: result.id
            })
        })
        .catch(err => {
            res.status(500);
            res.send(err);
        });
};

const update = function (req, res) {
    delete req.body.id; // remove id from body if there is
    Movie.findById(+req.params.id)
        .then(movie => {
            if (movie) {
                movie.update(req.body)
                    .then(() => {
                        res.status(200);
                        res.send();
                    });
            } else {
                res.status(404);
                res.send();
            }
        })
        .catch(err => {
            res.status(500);
            res.send(err);
        });
};

const remove = function (req, res) {
    Movie.findById(+req.params.id)
        .then(movie => {
            if (movie) {
                movie.destroy()
                    .then(() => {
                        res.status(200);
                        res.send();
                    });
            } else {
                res.status(404);
                res.send();
            }
        })
        .catch(err => {
            res.status(500);
            res.send(err);
        });
};

module.exports = {
    get: get,
    add: add,
    getById: getById,
    update: update,
    remove: remove
};