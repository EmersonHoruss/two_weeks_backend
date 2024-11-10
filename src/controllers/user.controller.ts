import { UserService } from "../services/user.service";
import { BaseController } from "./base.controller";
import { User } from "../models/user.model";

export class UserController extends BaseController<User> {
  constructor() {
    super(new UserService());
  }
}
