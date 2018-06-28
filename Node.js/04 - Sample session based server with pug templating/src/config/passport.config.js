const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const User = require('../models/user.model').get();

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

passport.serializeUser((user, done) => {
    if (user) {
        done(null, user.id);
    } else {
        done(null, null);
    }
});

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