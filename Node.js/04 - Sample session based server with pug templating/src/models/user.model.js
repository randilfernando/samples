const Sequelize = require('sequelize');
const bcrypt = require('bcrypt');

let User;

const init = (s) => {
    User = s.define('user', {
        id: {type: Sequelize.DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
        username: {type: Sequelize.DataTypes.STRING(20), allowNull: false, unique: true},
        password: {type: Sequelize.DataTypes.STRING}
    });

    User.prototype.hashPassword = function () {
        this.password = bcrypt.hashSync(this.password, 10);
    };

    User.prototype.validatePassword = function (password) {
        return bcrypt.compareSync(password, this.password);
    };
};

const get = () => {
    return User;
};

module.exports = {
    init: init,
    get: get
};