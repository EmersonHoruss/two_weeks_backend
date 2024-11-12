import { DetailSell } from "../models/detail-sell.model";
import { BaseRepository } from "./base.repository";

export class DetailSellRepository extends BaseRepository<DetailSell> {
  constructor() {
    super(DetailSell);
  }
}
