import request from "supertest";
import app from "../app";

describe("GET /users", () => {
  it("should return a list of users", async () => {
    const response = await request(app).get("/users");
    expect(response.status).toBe(200);
    expect(response.body).toEqual([
      { id: 1, name: "Juan" },
      { id: 2, name: "Ana" },
    ]);
  });
});
