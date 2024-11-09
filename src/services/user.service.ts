import { UserRepository } from "../repositories/user.repository";
import { User } from "../models/user.model";

export class UserService {
  private userRepository = new UserRepository();

  async createUser(data: Partial<User>) {
    return this.userRepository.create(data);
  }

  async getUsers() {
    return this.userRepository.findAll();
  }

  async getUser(id: number) {
    return this.userRepository.findById(id);
  }

  async updateUser(id: number, updates: Partial<User>) {
    return this.userRepository.update(id, updates);
  }

  async deleteUser(id: number) {
    return this.userRepository.delete(id);
  }
}
