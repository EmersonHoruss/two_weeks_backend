import { Column, Table, DataType, HasMany } from "sequelize-typescript";
import { BaseModel } from "./base.model"; // Import the BaseModel
import { DetailSell } from "./detail-sell.model"; // Import DetailSell model

@Table
export class Product extends BaseModel {
  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  name!: string;

  @Column({
    type: DataType.STRING,
  })
  description!: string;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  price!: number;

  @Column({
    type: DataType.INTEGER,
    defaultValue: 0,
  })
  stock!: number;

  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  category!: string;

  @HasMany(() => DetailSell)
  DetailSells!: DetailSell[];
}
