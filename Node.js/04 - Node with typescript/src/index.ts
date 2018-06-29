import 'reflect-metadata';

import {createConnection} from 'typeorm';
import App from './app';

const port = +process.env.PORT || 3000;

createConnection().then(() => {
    new App().listen(port);
});
