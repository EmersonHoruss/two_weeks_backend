import express from "express";
import userRoutes from "./routes/user.route";
import productRoutes from "./routes/product.route";

const app = express();

// Middleware for parsing JSON requests
app.use(express.json());

// Use routes from the `routes/userRoutes.ts`
app.use("/users", userRoutes);
app.use("/products", productRoutes);

// Error handling for unknown routes
app.use((req, res) => {
  res.status(404).json({ message: "Endpoint not found" });
});

export default app;
