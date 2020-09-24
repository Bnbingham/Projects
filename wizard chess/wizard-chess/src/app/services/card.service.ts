import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class CardService {
  cards = [
    {
      id: 0,
      color: "White",
      type: "Movement",
      subType: "Step",
      action: [1, 1],
    },
    {
      id: 1,
      color: "White",
      type: "Movement",
      subType: "Step",
      action: [0, 1],
    },
    {
      id: 2,
      color: "White",
      type: "Movement",
      subType: "Teleport",
      action: [0, 3],
    },
    {
      id: 3,
      color: "White",
      type: "Movement",
      subType: "Teleport",
      action: [1, 2],
    },
    {
      id: 4,
      color: "White",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 2],
        [1, 2],
        [2, 2],
        [3, 2],
      ],
    },
    {
      id: 5,
      color: "White",
      type: "Attack",
      subType: "AOE",
      action: [
        [1, 0],
        [2, 0],
        [1, 3],
        [2, 3],
      ],
    },
    {
      id: 6,
      color: "White",
      type: "Attack",
      subType: "Ray",
      action: [7, 0],
    },
    {
      id: 7,
      color: "Black",
      type: "Movement",
      subType: "Step",
      action: [0, -1],
    },
    {
      id: 8,
      color: "Black",
      type: "Movement",
      subType: "Step",
      action: [1, -1],
    },
    {
      id: 9,
      color: "Black",
      type: "Movement",
      subType: "Teleport",
      action: [1, 1],
    },
    {
      id: 10,
      color: "Black",
      type: "Movement",
      subType: "Teleport",
      action: [0, 0],
    },
    {
      id: 11,
      color: "Black",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 1],
        [1, 1],
        [2, 1],
        [3, 1],
      ],
    },
    {
      id: 12,
      color: "Black",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 1],
        [0, 2],
        [3, 1],
        [3, 2],
      ],
    },
    {
      id: 13,
      color: "Black",
      type: "Attack",
      subType: "Ray",
      action: [4, 3],
    },
    {
      id: 14,
      color: "Blue",
      type: "Movement",
      subType: "Step",
      action: [1, -1],
    },
    {
      id: 15,
      color: "Blue",
      type: "Movement",
      subType: "Step",
      action: [1, 0],
    },
    {
      id: 16,
      color: "Blue",
      type: "Movement",
      subType: "Teleport",
      action: [1, 0],
    },
    {
      id: 17,
      color: "Blue",
      type: "Movement",
      subType: "Teleport",
      action: [0, 1],
    },
    {
      id: 18,
      color: "Blue",
      type: "Attack",
      subType: "AOE",
      action: [
        [3, 0],
        [2, 1],
        [2, 2],
        [3, 3],
      ],
    },
    {
      id: 19,
      color: "Blue",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 0],
        [0, 1],
        [0, 2],
        [0, 3],
      ],
    },
    {
      id: 20,
      color: "Blue",
      type: "Attack",
      subType: "Ray",
      action: [5, 2],
    },
    {
      id: 21,
      color: "Purple",
      type: "Movement",
      subType: "Step",
      action: [1, 1],
    },
    {
      id: 22,
      color: "Purple",
      type: "Movement",
      subType: "Step",
      action: [1, 0],
    },
    {
      id: 23,
      color: "Purple",
      type: "Movement",
      subType: "Teleport",
      action: [0, 2],
    },
    {
      id: 24,
      color: "Purple",
      type: "Movement",
      subType: "Teleport",
      action: [1, 3],
    },
    {
      id: 25,
      color: "Purple",
      type: "Attack",
      subType: "AOE",
      action: [
        [3, 0],
        [2, 1],
        [1, 2],
        [0, 3],
      ],
    },
    {
      id: 26,
      color: "Purple",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 3],
        [1, 3],
        [2, 3],
        [3, 3],
      ],
    },
    {
      id: 27,
      color: "Purple",
      type: "Attack",
      subType: "Ray",
      action: [6, 1],
    },
    {
      id: 28,
      color: "Green",
      type: "Movement",
      subType: "Step",
      action: [-1, -1],
    },
    {
      id: 29,
      color: "Green",
      type: "Movement",
      subType: "Step",
      action: [0, -1],
    },
    {
      id: 30,
      color: "Green",
      type: "Movement",
      subType: "Teleport",
      action: [2, 1],
    },
    {
      id: 31,
      color: "Green",
      type: "Movement",
      subType: "Teleport",
      action: [3, 0],
    },
    {
      id: 32,
      color: "Green",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 0],
        [1, 1],
        [1, 2],
        [0, 3],
      ],
    },
    {
      id: 33,
      color: "Green",
      type: "Attack",
      subType: "AOE",
      action: [
        [2, 0],
        [2, 1],
        [2, 2],
        [2, 3],
      ],
    },
    {
      id: 34,
      color: "Green",
      type: "Attack",
      subType: "Ray",
      action: [4, -3],
    },
    {
      id: 35,
      color: "Brown",
      type: "Movement",
      subType: "Step",
      action: [-1, -1],
    },
    {
      id: 36,
      color: "Brown",
      type: "Movement",
      subType: "Step",
      action: [-1, 0],
    },
    {
      id: 37,
      color: "Brown",
      type: "Movement",
      subType: "Teleport",
      action: [0, 2],
    },
    {
      id: 38,
      color: "Brown",
      type: "Movement",
      subType: "Teleport",
      action: [3, 1],
    },
    {
      id: 39,
      color: "Brown",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 0],
        [1, 1],
        [2, 1],
        [3, 0],
      ],
    },
    {
      id: 40,
      color: "Brown",
      type: "Attack",
      subType: "AOE",
      action: [
        [3, 0],
        [3, 1],
        [3, 2],
        [3, 3],
      ],
    },
    {
      id: 41,
      color: "Brown",
      type: "Attack",
      subType: "Ray",
      action: [5, -2],
    },
    {
      id: 42,
      color: "Yellow",
      type: "Movement",
      subType: "Step",
      action: [-1, 1],
    },
    {
      id: 43,
      color: "Yellow",
      type: "Movement",
      subType: "Step",
      action: [-1, 0],
    },
    {
      id: 44,
      color: "Yellow",
      type: "Movement",
      subType: "Teleport",
      action: [2, 3],
    },
    {
      id: 45,
      color: "Yellow",
      type: "Movement",
      subType: "Teleport",
      action: [3, 2],
    },
    {
      id: 46,
      color: "Yellow",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 3],
        [1, 2],
        [2, 2],
        [3, 3],
      ],
    },
    {
      id: 47,
      color: "Yellow",
      type: "Attack",
      subType: "AOE",
      action: [
        [1, 0],
        [1, 1],
        [1, 2],
        [1, 3],
      ],
    },
    {
      id: 48,
      color: "Yellow",
      type: "Attack",
      subType: "Ray",
      action: [6, -1],
    },
    {
      id: 49,
      color: "Red",
      type: "Movement",
      subType: "Step",
      action: [-1, 1],
    },
    {
      id: 50,
      color: "Red",
      type: "Movement",
      subType: "Step",
      action: [0, 1],
    },
    {
      id: 51,
      color: "Red",
      type: "Movement",
      subType: "Teleport",
      action: [2, 2],
    },
    {
      id: 52,
      color: "Red",
      type: "Movement",
      subType: "Teleport",
      action: [3, 3],
    },
    {
      id: 53,
      color: "Red",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 0],
        [1, 1],
        [2, 2],
        [3, 3],
      ],
    },
    {
      id: 54,
      color: "Red",
      type: "Attack",
      subType: "AOE",
      action: [
        [0, 0],
        [1, 0],
        [2, 0],
        [3, 0],
      ],
    },
    {
      id: 55,
      color: "Red",
      type: "Attack",
      subType: "Ray",
      action: [7, 0],
    },
  ];
  inHand = [
    this.cards[49],
    this.cards[50],
    this.cards[51],
    this.cards[52],
    this.cards[53],
    this.cards[34],
    this.cards[13],
  ];
  constructor() {}
}
