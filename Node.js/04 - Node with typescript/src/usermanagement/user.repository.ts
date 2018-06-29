import {EntityRepository, Repository} from 'typeorm';
import UserEntity from '../entities/user.entity';

@EntityRepository(UserEntity)
export default class UserRepository extends Repository<UserEntity> {

    public findByUsernameAndPassword(username: string, password: string): Promise<UserEntity> {
        return this.findOne({username, password});
    }
}
