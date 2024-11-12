import { SellService } from "../services/sell.service";
import { BaseController } from "./base.controller";
import { Sell } from "../models/sell.model";

export class SellController extends BaseController<Sell> {
  constructor() {
    super(new SellService());
  }
}
