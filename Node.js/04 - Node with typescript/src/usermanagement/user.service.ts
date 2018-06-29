import {createConnection} from 'typeorm';
import {logger} from '../common/logger';
import UserEntity from '../entities/user.entity';

export default class UserService {

    private userRepository;

    constructor() {
        createConnection()
            .then(async (connection) => {
                this.userRepository = connection.getRepository(UserEntity);
            })
            .catch((err) => {
                logger.error('error when getting repository');
            });
    }

    public async getUsers(): Promise<UserEntity[]> {
        return await this.userRepository.find();
    }

    public async getUserById(id: number): Promise<UserEntity> {
        return await this.userRepository.findOne(id);
    }

    public async addUser(user: UserEntity): Promise<UserEntity> {
        return await this.userRepository.save(user);
    }

    public async updateUser(user: UserEntity): Promise<UserEntity> {
        return await this.userRepository.save(user);
    }

    public async removeUser(user: UserEntity): Promise<any> {
        return await this.userRepository.remove(user);
    }
}
