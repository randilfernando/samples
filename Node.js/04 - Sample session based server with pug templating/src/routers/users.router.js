const express = require('express');
const usersController = require('../controllers/users.controller');

const usersRouter = express.Router();

usersRouter.route('/get')
    .get(usersController.getUser);

usersRouter.route('/add')
    .get(usersController.addUserPage)
    .post(usersController.addUser);

module.exports = usersRouter;
