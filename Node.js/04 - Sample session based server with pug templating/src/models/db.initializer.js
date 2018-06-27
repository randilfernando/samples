const Sequelize = require('sequelize');

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

require('./user.model').init(sequelize);

sequelize.sync();