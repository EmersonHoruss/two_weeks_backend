import { Table, Column } from "sequelize-typescript";
import { BaseModel } from "./base.model";

@Table
export class User extends BaseModel {
  @Column
  name!: string;

  @Column
  email!: string;

  @Column
  password!: string;
}
