import { Model } from "sequelize-typescript";

export class GenericRepository<T extends Model> {
  constructor(private model: any) {}

  async create(item: Partial<T>): Promise<T> {
    return await this.model.create(item);
  }

  async findAll(): Promise<T[]> {
    return await this.model.findAll();
  }

  async findById(id: number): Promise<T | null> {
    return await this.model.findByPk(id);
  }

  async update(id: number, updates: Partial<T>): Promise<T | null> {
    const item = await this.model.findByPk(id);
    if (item) {
      await item.update(updates);
    }
    return item;
  }

  async delete(id: number): Promise<void> {
    const item = await this.model.findByPk(id);
    if (item) {
      await item.destroy();
    }
  }
}
