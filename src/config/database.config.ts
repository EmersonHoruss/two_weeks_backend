import { Sequelize } from "sequelize-typescript";
import { User } from "../models/user.model";
import mysql from "mysql2/promise"; // Import mysql2 for raw database handling

// Function to create the database if it doesn't exist
async function ensureDatabaseExists() {
  try {
    // Create a connection to MySQL without specifying the database
    const connection = await mysql.createConnection({
      host: process.env.DB_HOST,
      port: Number(process.env.DB_PORT),
      user: process.env.DB_USER,
      password: process.env.DB_PASSWORD,
    });

    // Query to create the database if it doesn't exist
    await connection.query(
      `CREATE DATABASE IF NOT EXISTS \`${process.env.DB_NAME}\`;`
    );
    console.log(`Database ${process.env.DB_NAME} ensured!`);

    // Close the connection
    await connection.end();
  } catch (error) {
    console.error("Error ensuring the database exists:", error);
    throw error; // Throw error to stop the app if there's an issue creating the database
  }
}

async function initializeSequelize() {
  // Ensure the database exists before starting Sequelize
  await ensureDatabaseExists();

  // Initialize Sequelize
  const sequelize = new Sequelize({
    database: process.env.DB_NAME,
    dialect: "mysql",
    username: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    host: process.env.DB_HOST,
    port: Number(process.env.DB_PORT),
    models: [User],
    logging: process.env.NODE_ENV === "development", // Logging based on environment
  });

  // Sync the models (without dropping tables or data)
  try {
    await sequelize.sync({ alter: true }); // Automatically adjust the schema without data loss
    console.log("Database synced!");
  } catch (error) {
    console.error("Error syncing database:", error);
    throw error;
  }

  return sequelize;
}

export default initializeSequelize;
