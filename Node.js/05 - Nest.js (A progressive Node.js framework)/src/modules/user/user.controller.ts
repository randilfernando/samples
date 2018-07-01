import { Body, Controller, Delete, Get, HttpCode, Param, Patch, Post } from '@nestjs/common';
import { UserService } from './user.service';
import { UserEntity } from '../../entities/user.entity';
import { UserDto } from './user.dto';

@Controller('/users')
export class UserController {
  constructor(private readonly userService: UserService) {
  }

  @Get()
  async getUsers(): Promise<UserEntity[]> {
    return await this.userService.getUsers();
  }

  @Post()
  @HttpCode(201)
  async addUser(@Body() userDto: UserDto): Promise<UserEntity> {
    const user: UserEntity = new UserEntity(userDto.username, userDto.password);
    return await this.userService.addUser(user);
  }

  @Get(':id')
  async getUserById(@Param('id') id: number) {
    return await this.userService.getUserById(id);
  }

  @Patch(':id')
  async updateUserById(@Param('id') id: number, @Body() userDto: UserDto) {
    const user = await this.userService.getUserById(id);
    if (userDto.username) {
      user.username = userDto.username;
    }

    if (userDto.password) {
      user.password = userDto.password;
    }

    return await this.userService.updateUser(user);
  }

  @Delete(':id')
  async removeUserById(@Param('id') id: number) {
    const user = await this.userService.getUserById(id);
    return await this.userService.removeUser(user);
  }
}