import { Column, Table, DataType, HasMany } from "sequelize-typescript";
import { BaseModel } from "./base.model"; // Import the BaseModel
import { DetailSell } from "./detail-sell.model"; // Import DetailSell model

@Table
export class Product extends BaseModel {
  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  name!: string; // Calculated attribute: type + brand + size

  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  type!: string;

  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  brand!: string;

  @Column({
    type: DataType.STRING,
    allowNull: false,
  })
  size!: string;

  @Column({
    type: DataType.INTEGER,
    allowNull: false,
    defaultValue: 0,
  })
  stock!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  purchasePrice!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  sellPriceNormal!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  sellPriceWholesale1!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  sellPriceWholesale2!: number;

  @Column({
    type: DataType.FLOAT,
    allowNull: false,
  })
  sellPriceCustomized!: number;

  @Column({
    type: DataType.STRING,
  })
  code?: string;

  @HasMany(() => DetailSell)
  DetailSells!: DetailSell[];
}
