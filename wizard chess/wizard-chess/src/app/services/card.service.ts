import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class CardService {
  cards = [
    { id: 1, name: "up", type: "move", subType: "step", action: [0, -1] },
    { id: 2, name: "down", type: "move", subType: "step", action: [0, 1] },
    { id: 3, name: "left", type: "move", subType: "step", action: [-1, 0] },
    { id: 4, name: "right", type: "move", subType: "step", action: [1, 0] },
    { id: 5, name: "tele", type: "move", subType: "teleport", action: [1, 2] },
    {
      id: 6,
      name: "ray",
      type: "attack",
      subType: "ray",
      action: [4, 2],
    },
  ];
  constructor() {}
}
