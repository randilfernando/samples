const express = require('express');
const usersController = require('../controllers/users.controller');

const usersRouter = express.Router();

usersRouter.route('/register')
    .get(usersController.registerPage)
    .post(usersController.register);

usersRouter.route('/login')
    .get(usersController.loginPage)
    .post(usersController.login);

usersRouter.route('/logout')
    .get(usersController.logout);

module.exports = usersRouter;
