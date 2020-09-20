import { PlayerToken } from "./../objClasses/player-token";
import { Board } from "./../objClasses/board";
import { Player } from "./../objClasses/Player";
import { Card } from "src/app/objClasses/card";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class CanvasService {
  constructor() {}
  private canvas;
  private ctx;

  private gb: Board;
  private p1: Player;
  private p2: Player;
  private pt1: PlayerToken;
  private pt2: PlayerToken;

  public start() {
    this.canvas = document.getElementById("myCanvas");
    this.ctx = this.canvas.getContext("2d");

    this.gb = new Board();

    this.p1 = new Player(1, [0, 0]);
    this.p2 = new Player(2, [0, 0]);

    this.pt1 = new PlayerToken(this.p1, this.gb);
    this.pt2 = new PlayerToken(this.p2, this.gb);

    this.drawBoard();
    this.drawPlayers();
  }
  //----------------------------------------------------
  public actionSwitch(input: Card, player = this.p1) {
    switch (input.type) {
      case "move":
        this.moveSwitch(input, player);
        break;
      case "attack":
        this.attackSwitch(input, player);
        break;
      default:
        break;
    }
  }

  private moveSwitch(input: Card, player: Player) {
    //old location to new location
    let currL = player.location;
    switch (input.subType) {
      case "step":
        //check new location
        let newX = currL[0] + input.action[0];
        let newY = currL[1] + input.action[1];
        if (newX >= 0 && newX <= 3) {
          currL[0] = currL[0] + input.action[0];
        }
        if (newY >= 0 && newY <= 3) {
          currL[1] += input.action[1];
        }
        break;
      case "teleport":
        currL[0] = input.action[0];
        currL[1] = input.action[1];
        break;

      default:
        break;
    }
    this.reDraw();
  }
  private attackSwitch(input: Card, player: Player) {
    switch (input.subType) {
      case "beam":
        this.drawRay(input, player);
        break;

      default:
        break;
    }
  }
  //  --------------------------------------------- //
  private reDraw() {
    let board = this.gb;
    this.ctx.clearRect(0, 0, board.width, board.height);
    this.drawBoard();
    this.drawPlayers();
  }

  private drawBoard() {
    //board
    let sqr = this.gb.sqrSize;
    let row = 0;
    while (row < 8) {
      for (let col = 0; col < 4; col++) {
        let x = col * sqr;
        this.ctx.strokeRect(x, row * sqr, sqr, sqr);
      }
      row++;
    }
    //center line
    this.ctx.fillStyle = "orange";
    this.ctx.fillRect(0, 159, 160, 2);
  }

  private drawPlayers() {
    let ts = this.gb.tokenSize;
    let p1x = this.pt1.x;
    let p1y = this.pt1.y;
    let p2x = this.pt2.x;
    let p2y = this.pt2.y;
    this.ctx.fillStyle = "rgb(200, 0, 0)";
    this.ctx.fillRect(p1x, p1y, ts, ts);

    this.ctx.fillStyle = "rgb(0, 0, 200)";
    this.ctx.fillRect(p2x, p2y, ts, ts);
  }

  private drawRay(input: Card, player) {
    //beam[0] is forward, ray[1] is left(-) right(+)
    //todo: inverse sideways path for player 2
    let rayPath = input.action;
    let rayColor = "green";
    let raySize = this.gb.tokenSize;
    let rayWidth = raySize;
    let rayLengthForward =
      raySize * rayPath[0] + (rayPath[0] - 1) * 2 * this.gb.border;
    let rayLengthSideways =
      raySize * rayPath[1] + rayPath[1] * 2 * this.gb.border;
    let p: PlayerToken;
    let offsetY: number;
    let offsetX: number;
    let offsetP2Y: number;
    if (player == this.p1) {
      p = this.pt1;
      offsetY = this.gb.sqrSize;
      if (rayPath[1] != 0) {
        offsetP2Y = rayLengthForward + 2 * this.gb.border - offsetY;
        if (rayPath[1] > 0) {
          offsetX = raySize;
        } else {
          offsetX = 0;
        }
      }
    } else if (player == this.p2) {
      p = this.pt2;
      offsetY = -this.gb.sqrSize * rayPath[0];
      if (rayPath[1] != 0) {
        if (rayPath[1] > 0) {
          offsetX = raySize;
        } else {
          offsetX = 0;
        }
      }
    }

    //forward beam
    this.ctx.fillStyle = rayColor;
    this.ctx.fillRect(p.x, p.y + offsetY, rayWidth, rayLengthForward);

    //sideways beam
    this.ctx.fillRect(
      p.x + offsetX,
      p.y + offsetY + offsetP2Y,
      rayLengthSideways,
      rayWidth
    );
  }

  drawCard(input: Card) {
    this.canvas = document.getElementById(String(input.id));
    this.ctx = this.canvas.getContext("2d");
    //TODO: modify drawBoard to draw one on a card
    if (input.type == "move") {
      switch (input.subType) {
        case "step":
          //code here
          break;
        case "teleport":
          //code here
          break;
        default:
          //code here
          break;
      }
    } else if (input.type == "attack") {
      switch (input.subType) {
        case "ray":
          //code here
          break;
        case "AOE":
          //code here
          break;
        default:
          //code here
          break;
      }
    }
  }
}
