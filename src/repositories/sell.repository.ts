import { Sell } from "../models/sell.model";
import { BaseRepository } from "./base.repository";

export class SellRepository extends BaseRepository<Sell> {
  constructor() {
    super(Sell);
  }
}
