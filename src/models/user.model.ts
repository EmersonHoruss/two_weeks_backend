import {
  Table,
  Column,
  Model,
  PrimaryKey,
  AutoIncrement,
} from "sequelize-typescript";

@Table
export class User extends Model {
  @PrimaryKey
  @AutoIncrement
  @Column
  declare id: number;

  @Column
  name!: string;

  @Column
  email!: string;

  @Column
  password!: string;
}