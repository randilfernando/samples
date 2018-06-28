const User = require('../models/user.model').get();
const passport = require('passport');

const registerPage = (req, res) => {
    res.render('register', {title: 'Register'})
};

const loginPage = (req, res) => {
    res.render('login', {title: 'Login'})
};

const register = async (req, res) => {
    delete req.body.id;
    /**
     * @description build will create a model instance without saving if you want to directly save use User.create()
     * here we want to execute instance methods before saving to the database
     */
    const user = User.build(req.body);
    user.hashPassword(); // call instance method this will simply hash the password and replace it with the actual value
    await user.save();
    res.redirect('/users/login');
};

/**
 * @description passport.js is a authentication middleware see passport.config.js for passport configurations
 * this will create a new user session and serialize session to the session store
 */
const login = passport.authenticate('local', {
    successRedirect: '/dashboard',
    failureRedirect: '/users/login',
    failureFlash: true
});

const logout = (req, res) => {
    req.logout(); // wrapper method provided by passport.js
    res.redirect('/users/login');
};

module.exports = {
    registerPage: registerPage,
    loginPage: loginPage,
    register: register,
    login: login,
    logout: logout
};