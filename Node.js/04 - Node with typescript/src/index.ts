import 'reflect-metadata';

import App from './app';

const port = +process.env.PORT || 3000;

new App().listen(port);
