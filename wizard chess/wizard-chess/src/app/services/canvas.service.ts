import { PlayerService } from "./player.service";
import { PlayerToken } from "./../objClasses/player-token";
import { Board } from "./../objClasses/board";
import { Player } from "./../objClasses/Player";
import { Card } from "src/app/objClasses/card";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class CanvasService {
  constructor(private playerService: PlayerService) {}

  private gb: Board;
  public p1: Player;
  public p2: Player;
  private pt1: PlayerToken;
  private pt2: PlayerToken;

  private turnHistory = [];

  public start() {
    this.gb = new Board(40, 8, "myCanvas");

    this.playerService.newPlayers();

    this.p1 = this.playerService.p1;
    this.p2 = this.playerService.p2;

    this.pt1 = new PlayerToken(this.p1, this.gb);
    this.pt2 = new PlayerToken(this.p2, this.gb);

    this.turnHistory.push([1, this.p2.location]);
    this.drawBoard(this.gb);
    this.drawPlayers();
  }
  private saveCanvasHistory() {
    let p1Clone = new Player();
    p1Clone.id = 1;
    p1Clone.location = [this.p1.location[0], this.p1.location[1]];
    p1Clone.health = this.p1.health;
    let p2Clone = new Player();
    p2Clone.id = 2;
    p2Clone.location = [this.p2.location[0], this.p2.location[1]];
    p2Clone.health = this.p2.health;
    this.turnHistory.push([
      this.turnHistory[this.turnHistory.length - 1][0] + 1,
      p1Clone,
      p2Clone,
    ]);
  }
  public restoreLastHistory(option?) {
    let board = this.gb;
    let last = this.turnHistory.pop();

    this.p1.location = last[1].location;
    this.p2.location = last[2].location;
    if (option != "exceptHealth") {
      this.p1.health = last[1].health;
      this.p2.health = last[2].health;
    }
    this.reDraw();
  }
  //----------------------------------------------------
  public actionSwitch(input: Card, player = this.p2) {
    this.saveCanvasHistory();
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
      case "Step":
        //check new location
        let newX = currL[0] + input.action[0];
        let newY = currL[1] - input.action[1];
        if (newX >= 0 && newX <= 3) {
          currL[0] = currL[0] + input.action[0];
        }
        if (newY >= 0 && newY <= 3) {
          currL[1] -= input.action[1];
        }
        break;
      case "Teleport":
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
      case "Ray":
        this.drawRay(input, player);
        break;
      case "AOE":
        this.drawAOE(input, player);
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
    let centerGridY = 0;
    let centerGridX = 0;
    switch (totalRows) {
      case 3:
        centerGridY = 2.5 * sqr;
        centerGridX = 0.5 * sqr;
        totalCols = 3;
        break;
      case 4:
        centerGridY = 2 * sqr;
        break;
      default:
        break;
    }

    while (currRow < totalRows) {
      for (let currCol = 0; currCol < totalCols; currCol++) {
        let x = currCol * sqr + centerGridX;
        board.ctx.strokeRect(x, currRow * sqr + centerGridY, sqr, sqr);
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
    let board = this.gb;
    let p1x = this.pt1.x;
    let p1y = this.pt1.y;
    let p2x = this.pt2.x;
    let p2y = this.pt2.y;

    board.ctx.fillStyle = "rgb(200, 0, 0)";
    board.ctx.fillRect(p1x, p1y, ts, ts);

    board.ctx.fillStyle = "rgb(0, 0, 200)";
    board.ctx.fillRect(p2x, p2y, ts, ts);
  }
  private drawAOE(input: Card, player) {
    let board = this.gb;
    this.saveCanvasHistory();
    board.ctx.fillStyle = "orange";
    let blast = board.tokenSize;
    input.action.forEach((sqr) => {
      let x = sqr[0] * board.sqrSize + board.tknPadding;
      let y = sqr[1] * board.sqrSize + board.tknPadding;

      board.ctx.fillRect(x, y, blast, blast);
    });
    setTimeout(() => {
      console.log("in AOE");
      this.restoreLastHistory("exceptHealth");
    }, 2000);
  }
  private drawRay(input: Card, player) {
    //beam[0] is forward, ray[1] is left(-) right(+)
    //todo: inverse sideways path for player 2
    this.saveCanvasHistory();
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
    setTimeout(() => {
      this.restoreLastHistory("exceptHealth");
    }, 2000);
  }

  drawCard(input: Card) {
    let board;
    let sqrSize = 10;
    if (input.type == "Movement") {
      //set up 4 x 4 board

      switch (input.subType) {
        case "Step":
          board = new Board(sqrSize, 3, String(input.id));
          board.tknPadding = 0;
          board.getCanvasContext();
          this.drawBoard(board);

          board.ctx.fillStyle = "black";
          board.ctx.fillRect(
            sqrSize * 1 + 0.5 * sqrSize,
            sqrSize * 1 + 2.5 * sqrSize,
            sqrSize,
            sqrSize
          );
          board.ctx.fillStyle = "darkgrey";
          board.ctx.fillRect(
            sqrSize * (1 + input.action[0]) + 0.5 * sqrSize,
            sqrSize * (1 - input.action[1]) + 2.5 * sqrSize,
            sqrSize,
            sqrSize
          );
          break;
        case "Teleport":
          board = new Board(sqrSize, 4, String(input.id));
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
        case "Ray":
          let rayStart = 3;
          //set up a full board without a centerline
          board = new Board(sqrSize, 8, String(input.id));
          board.tknPadding = 0;
          board.getCanvasContext();
          this.drawBoard(board, false);
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
          board.ctx.fillStyle = "black";
          board.ctx.fillRect(sqrSize * rayStart, sqrSize * 7, sqrSize, sqrSize);
          board.ctx.fillStyle = "darkgrey";
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
            let y = index[1] + 2;
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

  public mouseEnterEffect(card: Card, player: Player = this.p2) {
    //save effect
    let board = this.gb;
    let sqrSize = board.sqrSize;
    let x;
    let y;
    switch (card.type) {
      case "Movement":
        console.log("in movement");
        if (card.subType == "Step") {
          x = this.p2.location[0] + card.action[0];
          y = this.p2.location[1] - card.action[1];
          if (x < 0) x = 0;
          if (x > 3) x = 3;
          if (y < 0) y = 0;
          if (y > 3) y = 3;
          board.ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
          board.ctx.fillRect(
            sqrSize * x,
            board.height / 2 + sqrSize * y,
            sqrSize,
            sqrSize
          );
        } else {
          x = card.action[0];
          y = card.action[1];
          board.ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
          board.ctx.fillRect(
            sqrSize * x,
            board.height / 2 + sqrSize * y,
            sqrSize,
            sqrSize
          );
        }
        break;
      case "Attack":
        console.log(this.p2.location);
        if (card.subType == "Ray") {
          let rayStart = this.p2.location[0];
          board.ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
          for (let i = 0; i < card.action[0]; i++) {
            board.ctx.fillRect(
              sqrSize * rayStart,
              sqrSize * (i + this.p2.location[1]),
              sqrSize,
              sqrSize
            );
          }
          if (card.action[1] != 0) {
            if (card.action[1] > 0) {
              //ray right
              for (let i = 0; i < card.action[1]; i++) {
                board.ctx.fillRect(
                  sqrSize * (rayStart + 1 + i),
                  sqrSize * (7 - card.action[0] - (3 - this.p2.location[1])),
                  sqrSize,
                  sqrSize
                );
              }
            } else {
              //ray left
              for (let j = 0; j > card.action[1]; j--) {
                board.ctx.fillRect(
                  sqrSize * (rayStart - 1 + j),
                  sqrSize * (7 - card.action[0] - (3 - this.p2.location[1])),
                  sqrSize,
                  sqrSize
                );
              }
            }
          }
        } else {
          for (let index = 0; index < card.action.length; index++) {
            let x = card.action[index][0];
            let y = card.action[index][1];
            board.ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
            board.ctx.fillRect(sqrSize * x, sqrSize * y, sqrSize, sqrSize);
          }
        }
        break;
      default:
        break;
    }
  }
  public mouseLeaveEvent() {
    this.reDraw();
  }
}
