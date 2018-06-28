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

app.set('view engine', 'pug');
app.set('views', './src/views');

app.use(cookieParser());
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(expressSession({
    secret: 'ABcd12!@',
    resave: false,
    saveUninitialized: false,
    store: new SessionStore(options)
}));
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());

app.use('/users', usersRouter);
app.use('/dashboard', dashboardRouter);

let port = process.env['PORT'] || 4000;

app.listen(port, () => {
    console.log(`application started on http://localhost:${port}`);
});