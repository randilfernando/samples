import { Injectable } from '@nestjs/common';
import { EntityRepository, Repository } from 'typeorm';
import { UserEntity } from '../../entities/user.entity';

@Injectable()
@EntityRepository(UserEntity)
export class UserRepository extends Repository<UserEntity> {

  findByUsernameAndPassword(username: string, password: string): Promise<UserEntity> {
    return this.findOne({username, password});
  }
}