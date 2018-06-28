const Sequelize = require('sequelize');
const bcrypt = require('bcrypt');

/**
 * @description here for the simplicity we provide two methods called init and get
 * init method will be used by db.handler.js to initialize the model using sequelize object
 * after that we can use initialized object anywhere. (Note: init should execute first before accessing the model using get)
 */

let User;

const init = (s) => {
    /**
     * @description define new model and store it
     * init method called exactly once per server start
     */
    User = s.define('user', {
        id: {type: Sequelize.DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
        username: {type: Sequelize.DataTypes.STRING(20), allowNull: false, unique: true},
        password: {type: Sequelize.DataTypes.STRING}
    });

    /**
     * @description this are instance methods which we can execute on model instance
     */
    User.prototype.hashPassword = function () {
        this.password = bcrypt.hashSync(this.password, 10);
    };

    User.prototype.validatePassword = function (password) {
        return bcrypt.compareSync(password, this.password);
    };
};

/**
 * @description simply return the created model
 * @returns {*}
 */
const get = () => {
    return User;
};

module.exports = {
    init: init,
    get: get
};