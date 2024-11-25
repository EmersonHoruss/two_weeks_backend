import {
  Table,
  Column,
  Model,
  PrimaryKey,
  AutoIncrement,
  CreatedAt,
  UpdatedAt,
  DataType,
} from "sequelize-typescript";

@Table
export class BaseModel extends Model {
  @PrimaryKey
  @AutoIncrement
  @Column
  declare id: number;

  @CreatedAt
  @Column
  declare createdAt: Date;

  @UpdatedAt
  @Column
  declare updatedAt: Date;

  @Column({
    type: DataType.BOOLEAN,
    defaultValue: true,
  })
  activated?: boolean;
}
