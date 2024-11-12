import { DetailSell } from "../models/detail-sell.model";
import { DetailSellRepository } from "../repositories/detail-sell.repository";
import { BaseService } from "./base.service";

export class DetailSellService extends BaseService<DetailSell> {
  constructor() {
    super(new DetailSellRepository());
  }
}
