import { Request, Response } from "express";
import { User } from "../models/user.model";

// Crear un usuario
export const createUser = async (req: Request, res: Response) => {
  try {
    const user = await User.create(req.body);
    res.status(201).json(user);
  } catch (err) {
    res.status(500).json({ error: "Error creating user" });
  }
};

// Obtener todos los usuarios
export const getUsers = async (req: Request, res: Response) => {
  try {
    const users = await User.findAll();
    res.status(200).json(users);
  } catch (err) {
    res.status(500).json({ error: "Error fetching users" });
  }
};

// Obtener un usuario por ID
export const getUser = async (req: Request, res: Response) => {
  try {
    const user = await User.findByPk(req.params.id);
    if (user) {
      res.status(200).json(user);
    } else {
      res.status(404).json({ error: "User not found" });
    }
  } catch (err) {
    res.status(500).json({ error: "Error fetching user" });
  }
};

// Actualizar un usuario
export const updateUser = async (req: Request, res: Response) => {
  try {
    const user = await User.findByPk(req.params.id);
    if (user) {
      await user.update(req.body);
      res.status(200).json(user);
    } else {
      res.status(404).json({ error: "User not found" });
    }
  } catch (err) {
    res.status(500).json({ error: "Error updating user" });
  }
};

// Eliminar un usuario
export const deleteUser = async (req: Request, res: Response) => {
  try {
    const user = await User.findByPk(req.params.id);
    if (user) {
      await user.destroy();
      res.status(204).json();
    } else {
      res.status(404).json({ error: "User not found" });
    }
  } catch (err) {
    res.status(500).json({ error: "Error deleting user" });
  }
};
