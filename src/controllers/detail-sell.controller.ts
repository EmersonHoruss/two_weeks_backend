import { DetailSellService } from "../services/detail-sell.service";
import { BaseController } from "./base.controller";
import { DetailSell } from "../models/detail-sell.model";

export class DetailSellController extends BaseController<DetailSell> {
  constructor() {
    super(new DetailSellService());
  }
}
