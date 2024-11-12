import { Sell } from "../models/sell.model";
import { SellRepository } from "../repositories/sell.repository";
import { BaseService } from "./base.service";

export class SellService extends BaseService<Sell> {
  constructor() {
    super(new SellRepository());
  }
}
