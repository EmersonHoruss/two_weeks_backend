  import { FindOptions } from "sequelize";
  import { BaseModel } from "../models/base.model";

  export class BaseRepository<T extends BaseModel> {
    private model: typeof BaseModel;

    constructor(model: typeof BaseModel) {
      this.model = model;
    }

    async create(data: Partial<T>): Promise<T> {
      return this.model.create(data as any) as Promise<T>;
    }

    async findAll(): Promise<T[]> {
      return this.model.findAll() as Promise<T[]>;
    }

    async findById(id: number): Promise<T | null> {
      return this.model.findByPk(id) as Promise<T | null>;
    }

    async update(id: number, data: Partial<T>): Promise<T | null> {
      const entity = await this.findById(id);
      if (entity) {
        return entity.update(data);
      }
      return null;
    }

    async delete(id: number): Promise<boolean> {
      const entity = await this.findById(id);
      if (entity) {
        await entity.destroy();
        return true;
      }
      return false;
    }

    async findAndPaginate(options: FindOptions) {
      const { limit, offset, where, order } = options;

      const result = await this.model.findAndCountAll({
        where,
        limit,
        offset,
        order,
      });

      return {
        rows: result.rows as T[],
        total: result.count,
        limit,
        offset,
      };
    }
  }
