import { ProductService } from "../services/product.service";
import { BaseController } from "./base.controller";
import { Product } from "../models/product.model";

export class ProductController extends BaseController<Product> {
  constructor() {
    super(new ProductService());
  }
}
