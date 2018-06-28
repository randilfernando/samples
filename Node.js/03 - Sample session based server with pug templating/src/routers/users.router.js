const express = require('express');
const usersController = require('../controllers/users.controller');

const usersRouter = express.Router();

// end point: /user/register
usersRouter.route('/register')
    .get(usersController.registerPage)
    .post(usersController.register);

// end point: /user/login
usersRouter.route('/login')
    .get(usersController.loginPage)
    .post(usersController.login);

// end point: /user/logout
usersRouter.route('/logout')
    .get(usersController.logout);

module.exports = usersRouter;
