import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { createConnection } from 'typeorm';

async function bootstrap() {
  await createConnection();
  const app = await NestFactory.create(AppModule);
  await app.listen(3000);
}

bootstrap();
