import { User } from "../models/user.model";
import { UserRepository } from "../repositories/user.repository";
import { BaseService } from "./base.service";

export class UserService extends BaseService<User> {
  constructor() {
    super(new UserRepository());
  }
}
