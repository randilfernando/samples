const Sequelize = require('sequelize');

/**
 * @description sequlize is a ORM which support MySQL, PostgresSQL and SQLite
 * read sequelize documentation to get familiarised with features available
 */

const sequelize = new Sequelize(
    'node_sample',
    'samples',
    'ABcd12!@',
    {
        host: 'localhost',
        dialect: 'mysql',
        operatorsAliases: false
    }
);

/**
 * @description after sequlize object created we initialize all methods using sequelize object
 * init method is created to simply pass the same sequelize object initialized to all model files
 */
require('../models/user.model').init(sequelize);

/**
 * @description sync model definition with the database and update tables
 */
sequelize.sync();