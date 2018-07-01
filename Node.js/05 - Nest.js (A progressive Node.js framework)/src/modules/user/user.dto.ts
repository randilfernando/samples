export class UserDto {
  readonly username: string;
  readonly password: string;

  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}
