import {getCustomRepository, getManager} from 'typeorm';
import UserEntity from '../entities/user.entity';
import UserRepository from './user.repository';

export default class UserService {

    private userRepository;

    constructor() {
        this.userRepository = getManager().getCustomRepository(UserRepository);
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
