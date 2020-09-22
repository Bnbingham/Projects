import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class CardService {
  cards = [
    {
      id: 1,
      color: "yellow",
      type: "Movement",
      subType: "step",
      action: [0, -1],
    },
    { id: 2, color: "red", type: "Movement", subType: "step", action: [0, 1] },
    {
      id: 3,
      color: "green",
      type: "Movement",
      subType: "step",
      action: [-1, 0],
    },
    { id: 4, color: "blue", type: "Movement", subType: "step", action: [1, 0] },
    {
      id: 5,
      color: "white",
      type: "Movement",
      subType: "teleport",
      action: [1, 2],
    },
    {
      id: 6,
      color: "purple",
      type: "Attack",
      subType: "ray",
      action: [3, 1],
    },
  ];
  inHand = [this.cards[0], this.cards[1], this.cards[2]];
  constructor() {}
}
