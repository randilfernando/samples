import { Injectable } from '@nestjs/common';
import { UserEntity } from '../../entities/user.entity';
import { UserRepository } from './user.repository';

@Injectable()
export class UserService {

  constructor(private userRepository: UserRepository) {
  }

  async getUsers(): Promise<UserEntity[]> {
    return await this.userRepository.find();
  }

  async getUserById(id: number): Promise<UserEntity> {
    return await this.userRepository.findOne(id);
  }

  async addUser(user: UserEntity): Promise<UserEntity> {
    return await this.userRepository.save(user);
  }

  async updateUser(user: UserEntity): Promise<UserEntity> {
    return await this.userRepository.save(user);
  }

  async removeUser(user: UserEntity): Promise<any> {
    return await this.userRepository.remove(user);
  }
}