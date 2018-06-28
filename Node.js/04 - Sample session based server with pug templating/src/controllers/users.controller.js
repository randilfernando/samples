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
    const user = User.build(req.body);
    user.hashPassword();
    await user.save();
    res.redirect('/users/login');
};

const login = passport.authenticate('local', {
    successRedirect: '/dashboard',
    failureRedirect: '/users/login',
    failureFlash: true
});

const logout = (req, res) => {
    req.logout();
    res.redirect('/users/login');
};

module.exports = {
    registerPage: registerPage,
    loginPage: loginPage,
    register: register,
    login: login,
    logout: logout
};