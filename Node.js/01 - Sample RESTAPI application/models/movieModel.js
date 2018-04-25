/**
 * Movie model is connected to mock dataset
 * When developing a real application need to connect to a database
 * lodash library is a collection of usefull functions
 */
const _ = require('lodash');
const movies = require('../data/mock-data').movies;
const lastId = require('../data/mock-data').movieLastId;

/**
 * these functions are simple javascript functions
 * not holds any implementation related to the express framework or restAPI
 * only access mock data set and apply logic to that dataset such as insert, update, delete
 */

const find = function () {
    return movies;
};

const findById = function (_id) {
    return _.find(movies, movie => movie._id === _id);
};

const add = function (movie) {
    movie['_id'] = movies.length + 1;
    movies.push(movie);

    return movies.length;
};

const update = function (_id, movie) {
    const found = findById(_id);
    _.assign(found, movie);
};

const remove = function (_id) {
    _.remove(movies, movie => movie._id === _id);
};

module.exports = {
    find: find,
    findById: findById,
    add: add,
    update: update,
    remove: remove
};