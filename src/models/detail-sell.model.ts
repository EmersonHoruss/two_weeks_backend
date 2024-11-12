import {
  Column,
  Table,
  ForeignKey,
  BelongsTo,
  DataType,
} from "sequelize-typescript";
import { BaseModel } from "./base.model"; // Import the BaseModel
import { Sell } from "./sell.model"; // Import the Sell model
import { Product } from "./product.model"; // Import the Product model

@Table
export class DetailSell extends BaseModel {
  @Column({
    type: DataType.INTEGER,
    allowNull: false,
  })
  quantity!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  price!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  totalAmount!: number; // quantity * price

  @ForeignKey(() => Sell)
  @Column
  sellId!: number;

  @BelongsTo(() => Sell)
  sell!: Sell;

  @ForeignKey(() => Product)
  @Column
  productId!: number;

  @BelongsTo(() => Product)
  product!: Product;
}
