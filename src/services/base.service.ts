import { BaseModel } from "../models/base.model";
import { BaseRepository } from "../repositories/base.repository";

export class BaseService<T extends BaseModel> {
  private repository: BaseRepository<T>;

  constructor(repository: BaseRepository<T>) {
    this.repository = repository;
  }

  async create(data: Partial<T>): Promise<T> {
    return this.repository.create(data);
  }

  async findAll(): Promise<T[]> {
    return this.repository.findAll();
  }

  async findById(id: number): Promise<T | null> {
    return this.repository.findById(id);
  }

  async update(id: number, data: Partial<T>): Promise<T | null> {
    return this.repository.update(id, data);
  }

  async delete(id: number): Promise<boolean> {
    return this.repository.delete(id);
  }

  async findAndPaginate(options = {}) {
    return this.repository.findAndPaginate(options);
  }
}
