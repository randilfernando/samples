/**
 * @description run configuration at the beginning because some files may depend on these configs
 * (eg: Model.get() will depend on Model.init())
 */
require('./config/db.config');
require('./config/passport.config');

const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const expressSession = require('express-session');
const SessionStore = require('express-mysql-session');
const passport = require('passport');
const flash = require('connect-flash');

const usersRouter = require('./routers/users.router');
const dashboardRouter = require('./routers/dashboard.router');

const options = {
    host: 'localhost',
    port: 3306,
    user: 'samples',
    password: 'ABcd12!@',
    database: 'node_sample'
};

const app = express();

app.set('view engine', 'pug'); // set view engine
app.set('views', './src/views'); // set view base location

app.use(cookieParser()); // use cookies because we want to enable session support
app.use(bodyParser.urlencoded({
    extended: false
})); // here we are submitting information using forms. this will parse inputs and add those details to req.body
app.use(expressSession({
    secret: 'ABcd12!@',
    resave: false,
    saveUninitialized: false,
    store: new SessionStore(options) // use mysql session store to store sessions (default session store is an in memory one)
}));
app.use(passport.initialize()); // initialize passport middleware throughout our application
app.use(passport.session()); // initialize passport sessions (passport.session() depend on expressSession())
app.use(flash()); // use flash messages

app.use('/users', usersRouter);
app.use('/dashboard', dashboardRouter);

let port = process.env['PORT'] || 4000;

app.listen(port, () => {
    console.log(`application started on http://localhost:${port}`);
});