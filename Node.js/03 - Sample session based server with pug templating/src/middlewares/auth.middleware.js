const checkAuth = (req, res, next) => {
    // wrapper method provided by passport.js is used
    if (req.isAuthenticated()) {
        next();
    } else {
        res.redirect('/users/login');
    }
};

module.exports = {
    checkAuth: checkAuth
};