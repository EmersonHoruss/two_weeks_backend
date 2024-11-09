import { User } from "../models/user.model";
import { GenericRepository } from "./generic.repository";

export class UserRepository extends GenericRepository<User> {
  constructor() {
    super(User);
  }
}
