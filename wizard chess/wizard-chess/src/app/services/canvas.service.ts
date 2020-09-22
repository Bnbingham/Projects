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

  private gb: Board;
  private p1: Player;
  private p2: Player;
  private pt1: PlayerToken;
  private pt2: PlayerToken;

  public start() {
    this.gb = new Board(40, 8, "myCanvas");

    this.p1 = new Player(1, [0, 0]);
    this.p2 = new Player(2, [0, 0]);

    this.pt1 = new PlayerToken(this.p1, this.gb);
    this.pt2 = new PlayerToken(this.p2, this.gb);

    this.drawBoard(this.gb);
    this.drawPlayers();
  }
  //----------------------------------------------------
  public actionSwitch(input: Card, player = this.p2) {
    switch (input.type) {
      case "Movement":
        this.moveSwitch(input, player);
        break;
      case "Attack":
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
      case "ray":
        this.drawRay(input, player);
        break;

      default:
        break;
    }
  }
  //  --------------------------------------------- //
  private reDraw() {
    let board = this.gb;
    board.ctx.clearRect(0, 0, board.width, board.height);
    this.drawBoard(this.gb);
    this.drawPlayers();
  }

  private drawBoard(board: Board, centerLine = true) {
    //board
    board.getCanvasContext();
    let totalRows = board.totalRows;
    let totalCols = 4;
    let currRow = 0;
    let sqr = board.sqrSize;
    let centerGrid = 0;
    if (totalRows <= 4) {
      centerGrid = 2 * sqr;
    }
    while (currRow < totalRows) {
      for (let currCol = 0; currCol < totalCols; currCol++) {
        let x = currCol * sqr;
        board.ctx.strokeRect(x, currRow * sqr + centerGrid, sqr, sqr);
      }
      currRow++;
    }
    //center line
    if (board.totalRows > 4 && centerLine) {
      board.ctx.fillStyle = "orange";
      let halfBoard = 4 * sqr;
      board.ctx.fillRect(0, halfBoard - 1, halfBoard, 2);
    }
  }

  private drawPlayers() {
    let ts = this.gb.tokenSize;
    let p1x = this.pt1.x;
    let p1y = this.pt1.y;
    let p2x = this.pt2.x;
    let p2y = this.pt2.y;
    let board = this.gb;
    board.ctx.fillStyle = "rgb(200, 0, 0)";
    board.ctx.fillRect(p1x, p1y, ts, ts);

    board.ctx.fillStyle = "rgb(0, 0, 200)";
    board.ctx.fillRect(p2x, p2y, ts, ts);
  }

  private drawRay(input: Card, player) {
    //beam[0] is forward, ray[1] is left(-) right(+)
    //todo: inverse sideways path for player 2
    let board = this.gb;
    let rayPath = input.action;
    let rayColor = "green";
    let raySize = this.gb.tokenSize;
    let rayWidth = raySize;
    let rayLengthForward =
      raySize * rayPath[0] + (rayPath[0] - 1) * 2 * this.gb.tknPadding;
    let rayLengthSideways =
      raySize * rayPath[1] + rayPath[1] * 2 * this.gb.tknPadding;
    let p: PlayerToken;
    let offsetY: number;
    let offsetX: number;
    let offsetP2Y: number = 0;
    if (player == this.p1) {
      p = this.pt1;
      offsetY = this.gb.sqrSize;
      if (rayPath[1] != 0) {
        offsetP2Y = rayLengthForward + 2 * this.gb.tknPadding - offsetY;
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
    board.ctx.fillStyle = rayColor;
    board.ctx.fillRect(p.x, p.y + offsetY, rayWidth, rayLengthForward);

    //sideways beam
    board.ctx.fillRect(
      p.x + offsetX,
      p.y + offsetY + offsetP2Y,
      rayLengthSideways,
      rayWidth
    );
  }

  drawCard(input: Card) {
    let board;
    let sqrSize = 10;
    if (input.type == "Movement") {
      //set up 4 x 4 board
      board = new Board(sqrSize, 4, String(input.id));
      switch (input.subType) {
        case "step":
          let xStart = 1;
          let xFinish = 1 + input.action[0];
          let yStart = 3;
          let yFinish = 3 + input.action[1];
          if (xFinish == 0) {
            xStart += 1;
            xFinish += 1;
          }
          if (yFinish == 2) {
            yStart += 1;
            yFinish += 1;
          }
          board.tknPadding = 0;
          board.getCanvasContext();
          this.drawBoard(board);
          board.ctx.fillStyle = "darkGrey";
          board.ctx.fillRect(
            sqrSize * xStart,
            sqrSize * yStart,
            sqrSize,
            sqrSize
          );
          board.ctx.fillStyle = "lightGrey";
          board.ctx.fillRect(
            sqrSize * xFinish,
            sqrSize * yFinish,
            sqrSize,
            sqrSize
          );
          break;
        case "teleport":
          board.tknPadding = 0;
          board.getCanvasContext();
          this.drawBoard(board);
          board.ctx.fillStyle = "darkGrey";
          board.ctx.fillRect(
            sqrSize * input.action[0],
            sqrSize * (input.action[1] + 2),
            sqrSize,
            sqrSize
          );
          break;
        default:
          //code here
          break;
      }
    } else if (input.type == "Attack") {
      switch (input.subType) {
        case "ray":
          let rayStart = 3;
          //set up a full board without a centerline
          board = new Board(sqrSize, 8, String(input.id));
          board.tknPadding = 0;
          board.getCanvasContext();
          this.drawBoard(board, false);
          //0:1,1:1,2:0,3:0  -1:2,-2:3,-3,3
          switch (input.action[1]) {
            case 0:
            case 1:
              rayStart = 1;
              break;
            case 2:
            case 3:
              rayStart = 0;
              break;
            case -1:
              rayStart = 2;
              break;
            case -2:
            case -3:
              rayStart = 3;
              break;
            default:
              break;
          }
          board.ctx.fillStyle = "darkGrey";
          board.ctx.fillRect(sqrSize * rayStart, sqrSize * 7, sqrSize, sqrSize);
          board.ctx.fillStyle = "lightGrey";
          for (let i = 0; i < input.action[0]; i++) {
            board.ctx.fillRect(
              sqrSize * rayStart,
              sqrSize * (6 - i),
              sqrSize,
              sqrSize
            );
          }
          if (input.action[1] != 0) {
            if (input.action[1] > 0) {
              //ray right
              for (let i = 0; i < input.action[1]; i++) {
                board.ctx.fillRect(
                  sqrSize * (rayStart + 1 + i),
                  sqrSize * (7 - input.action[0]),
                  sqrSize,
                  sqrSize
                );
              }
            } else {
              //ray left
              for (let j = 0; j > input.action[1]; j--) {
                board.ctx.fillRect(
                  sqrSize * (rayStart - 1 + j),
                  sqrSize * (7 - input.action[0]),
                  sqrSize,
                  sqrSize
                );
              }
            }
          }
          break;
        case "AOE":
          //set up 4 x 4 board
          board = new Board(sqrSize, 4, String(input.id));
          board.tknPadding = 0;
          board.getCanvasContext();
          this.drawBoard(board);
          input.action.forEach((index) => {
            let x = index[0];
            let y = index[1];
            board.ctx.fillStyle = "darkGrey";
            board.ctx.fillRect(sqrSize * x, sqrSize * y, sqrSize, sqrSize);
          });
          break;
        default:
          //code here
          break;
      }
    }
  }
}
