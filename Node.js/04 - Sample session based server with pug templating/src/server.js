require('./models/db.initializer');

const express = require('express');
const bodyParser = require('body-parser');
const usersRouter = require('./routers/users.router');

let port = process.env['PORT'] || 4000;

const app = express();

app.set('view engine', 'pug');
app.set('views', './src/views');

app.use(bodyParser.urlencoded());

app.use('/user', usersRouter);

app.listen(port, () => {
    console.log(`application started on http://localhost:${port}`);
});