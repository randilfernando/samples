const Sequelize = require('sequelize');

// hold created database model
let Movie;

const init = function (connection) {
    Movie = connection.define ('movie', {
        id: {type: Sequelize.INTEGER, primaryKey: true, autoIncrement: true},
        name: {type: Sequelize.STRING, allowNull: false},
        ratings: {type: Sequelize.INTEGER, allowNull: false}
    });
};

const get = function() {
    return Movie;
};

module.exports = {
    init: init,
    get: get
};
