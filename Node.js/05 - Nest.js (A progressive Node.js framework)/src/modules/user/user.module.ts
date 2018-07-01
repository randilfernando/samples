import { Module } from '@nestjs/common';
import { UserController } from './user.controller';
import { UserService } from './user.service';
import { UserRepository } from './user.repository';
import { getManager } from 'typeorm';

export function getUserRepository() {
  return getManager().getCustomRepository(UserRepository);
}

@Module({
  imports: [],
  controllers: [
    UserController,
  ],
  providers: [
    UserService,
    {
      provide: UserRepository,
      useFactory: getUserRepository,
    },
  ],
})
export class UserModule {
}