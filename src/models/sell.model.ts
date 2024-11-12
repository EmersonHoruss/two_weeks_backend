import { Column, Table, DataType, HasMany } from "sequelize-typescript";
import { BaseModel } from "./base.model"; // Import the BaseModel
import { DetailSell } from "./detail-sell.model"; // Import DetailSell model

@Table
export class Sell extends BaseModel {
  @Column({
    type: DataType.DATE,
    allowNull: false,
  })
  date!: Date;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  totalAmount!: number;

  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  paymentMethod!: string;

  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  status!: string;

  @HasMany(() => DetailSell)
  DetailSells!: DetailSell[];
}
