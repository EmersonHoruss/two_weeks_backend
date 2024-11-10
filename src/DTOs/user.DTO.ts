import { BaseDto } from "./base.DTO";

export class UserDto extends BaseDto {
  name!: string;
  email!: string;
  password!: string;
}
