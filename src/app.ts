import express from "express";
import userRoutes from "./routes/userRoutes";

const app = express();

// Middleware for parsing JSON requests
app.use(express.json());

// Routes
app.use("/users", userRoutes);

// Error handling for unknown routes
app.use((req, res) => {
  res.status(404).json({ message: "Endpoint not found" });
});

export default app;
