import 'reflect-metadata';

import {createConnection} from 'typeorm';
import App from './app';

const port = +process.env.PORT || 4000;

createConnection().then(() => {
    new App().listen(port);
});
