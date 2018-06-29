import * as express from 'express';
import {Router} from 'express';
import UserEntity from '../entities/user.entity';
import UserService from './user.service';

export default class UserController {

    private router: Router;
    private userService: UserService;

    constructor() {
        this.router = express.Router();
        this.userService = new UserService();
        this.initRoutes();
    }

    public getRouter(): Router {
        return this.router;
    }

    private initRoutes() {
        this.router.use((req, res, next) => {
            if (req.params.id) {
                req.body.id = req.params.id;
            }

            if (req.body && req.body.id) {
                req.body = new UserEntity(req.body.id, req.body.username, req.body.password);
            }

            next();
        });

        this.router.route('')
            .get((async (req, res) => {
                const users = await this.userService.getUsers();
                res.json({
                    data: users,
                });
            }))
            .post(async (req, res) => {
                const user = await this.userService.addUser(req.body);
                res.status(201);
                res.json({
                    id: user.id,
                });
            });

        this.router.route('/:id')
            .get(async (req, res) => {
                const user = await this.userService.getUserById(req.params.id);
                if (user) {
                    res.json({
                        data: user,
                    });
                } else {
                    res.status(404);
                    res.json();
                }
            })
            .put(async (req, res) => {
                const user = await this.userService.updateUser(req.body);
                res.json({
                    data: user,
                });
            })
            .delete(async (req, res) => {
                const user = await this.userService.getUserById(req.params.id);
                await this.userService.removeUser(user);
                res.json();
            });
    }
}