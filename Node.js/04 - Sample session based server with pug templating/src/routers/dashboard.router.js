const express = require('express');
const dashboardController = require('../controllers/dashboard.controller');
const authMiddleware = require('../middlewares/auth.middleware');

const dashboardRouter = express.Router();

/**
 * @description use auth guard only authenticated users can enter routes start with /dashboard
 */
dashboardRouter.use(authMiddleware.checkAuth);

// end point: /dashboard
dashboardRouter.route('')
    .get(dashboardController.dashboardPage);

module.exports = dashboardRouter;