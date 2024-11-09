import "./config/dotenv.config"; // Import the dotenv configuration first

// Now you can access the environment variables in your application
import app from "./app";
import initializeSequelize from "./config/database.config";

const PORT = process.env.PORT || 3000;

// Initialize the database and start the server
initializeSequelize()
  .then(() => {
    console.log("Database connection established");
    app.listen(PORT, () => {
      console.log(`Server running on http://localhost:${PORT}`);
    });
  })
  .catch((err) => {
    console.error("Error connecting to the database: ", err);
  });
