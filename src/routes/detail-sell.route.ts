import { Router } from "express";
import { DetailSellController } from "../controllers/detail-sell.controller";

const router = Router();
const controller = new DetailSellController();

router.post("/", (req, res) => controller.create(req, res));
router.get("/", (req, res) => controller.findAll(req, res));
router.get("/:id", (req, res) => controller.findById(req, res));
router.put("/:id", (req, res) => controller.update(req, res));
router.delete("/:id", (req, res) => controller.delete(req, res));
router.get("/paginate", (req, res) => controller.findAndPaginate(req, res));

export default router;
