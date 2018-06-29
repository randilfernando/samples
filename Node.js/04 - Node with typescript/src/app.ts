import * as bodyParser from 'body-parser';
import * as express from 'express';
import {Application} from 'express';
import {logger} from './common/logger';
import UserController from './usermanagement/user.controller';

export default class App {
    public express: Application;

    constructor() {
        this.express = express();
        this.initMiddleware();
        this.initControllers();
    }

    public listen(port: number): void {
        this.express.listen(port, (err) => {
            if (err) {
                logger.error('error when starting server');
            } else {
                logger.info(`Server started at http://localhost:${port}`);
            }
        });
    }

    private initMiddleware(): void {
        this.express.use(bodyParser.json());
    }

    private initControllers(): void {
        const userController = new UserController();
        this.express.use('/users', userController.getRouter());
    }
}
