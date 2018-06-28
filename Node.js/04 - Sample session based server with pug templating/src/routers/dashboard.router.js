const express = require('express');
const dashboardController = require('../controllers/dashboard.controller');
const authMiddleware = require('../middlewares/auth.middleware');

const dashboardRouter = express.Router();

dashboardRouter.use(authMiddleware.checkAuth);
dashboardRouter.route('')
    .get(dashboardController.dashboardPage);

module.exports = dashboardRouter;