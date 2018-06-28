const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const User = require('../models/user.model').get();

/**
 * @description this code is copied from local authentication section in passport.js documentation
 * there are multiple auth methods supported by passport.js (google, facebook, twitter)
 */
passport.use(new LocalStrategy({
        usernameField: 'username',
        passwordField: 'password'
    }, (username, password, done) => {
        User.findOne({
            where: {
                username: username
            }
        }).then(user => {
            if (!user) {
                return done(null, false, {message: 'Incorrect username.'});
            }
            if (!user.validatePassword(password)) {
                return done(null, false, {message: 'Incorrect password.'});
            }
            return done(null, user);
        }).catch(err => {
            return done(err);
        });
    }
));

/**
 * @description serialize session
 * after successful login user id will save to the session store with the session id
 */
passport.serializeUser((user, done) => {
    if (user) {
        done(null, user.id);
    } else {
        done(null, null);
    }
});

/**
 * @description at every request after the login this will find a relevant user with the user id
 * and append that user as req.user
 */
passport.deserializeUser((id, done) => {
    User.findById(id).then(user => {
        if (user) {
            done(null, user);
        } else {
            done(null, null);
        }
    }).catch(err => {
        done(err, null);
    });
});