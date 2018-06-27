const Sequelize = require('sequelize');

let User;

const init = (s) => {
    User = s.define('user', {
        id: { type: Sequelize.DataTypes.INTEGER, primaryKey: true, autoIncrement: true },
        username: { type: Sequelize.DataTypes.STRING(20), allowNull: false, unique: true },
        password: { type: Sequelize.DataTypes.STRING }
    })
};

const get = () => {
    return User;
};

module.exports = {
    init: init,
    get: get
};