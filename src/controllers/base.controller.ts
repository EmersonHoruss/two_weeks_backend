import { Request, Response } from "express";
import { BaseService } from "../services/base.service";
import { BaseModel } from "../models/base.model";

export class BaseController<T extends BaseModel> {
  private service: BaseService<T>;

  constructor(service: BaseService<T>) {
    this.service = service;
  }

  async create(req: Request, res: Response) {
    try {
      const data = req.body;
      const result = await this.service.create(data);
      res.status(201).json(result);
    } catch (err) {
      res.status(500).json({ error: "Error creating entity" });
    }
  }

  async findAll(req: Request, res: Response) {
    try {
      const data = await this.service.findAll();
      res.status(200).json(data);
    } catch (err) {
      res.status(500).json({ error: "Error fetching entities" });
    }
  }

  async findById(req: Request, res: Response) {
    try {
      const id = req.params.id;
      const result = await this.service.findById(Number(id));
      if (result) {
        res.status(200).json(result);
      } else {
        res.status(404).json({ error: "Entity not found" });
      }
    } catch (err) {
      res.status(500).json({ error: "Error fetching entity" });
    }
  }

  async update(req: Request, res: Response) {
    try {
      const id = req.params.id;
      const data = req.body;
      const result = await this.service.update(Number(id), data);
      if (result) {
        res.status(200).json(result);
      } else {
        res.status(404).json({ error: "Entity not found" });
      }
    } catch (err) {
      res.status(500).json({ error: "Error updating entity" });
    }
  }

  async delete(req: Request, res: Response) {
    try {
      const id = req.params.id;
      const success = await this.service.delete(Number(id));
      if (success) {
        res.status(204).json();
      } else {
        res.status(404).json({ error: "Entity not found" });
      }
    } catch (err) {
      res.status(500).json({ error: "Error deleting entity" });
    }
  }

  async findAndPaginate(req: Request, res: Response) {
    try {
      const options = {
        where: req.query.where,
        limit: Number(req.query.limit) || 10,
        offset: Number(req.query.offset) || 0,
        order: req.query.order ? JSON.parse(req.query.order as string) : [],
      };

      const result = await this.service.findAndPaginate(options);
      res.status(200).json(result);
    } catch (err) {
      res.status(500).json({ error: "Error fetching paginated entities" });
    }
  }
}
