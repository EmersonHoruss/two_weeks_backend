import dotenv from "dotenv";

const envPath =
  process.env.NODE_ENV === "production"
    ? "./production.env"
    : "./development.env";

dotenv.config({ path: envPath });

console.log(`Loaded ${process.env.NODE_ENV} environment variables`);
console.log(process.env.DB_PORT);
