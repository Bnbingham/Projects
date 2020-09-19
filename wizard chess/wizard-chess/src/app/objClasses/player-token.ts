import { Board } from "./board";
import { Player } from "./Player";
export class PlayerToken {
  constructor(private _player: Player, private _board: Board) {
    this.player = _player;
    this.board = _board;
  }

  public get player(): Player {
    return this._player;
  }

  public set player(input: Player) {
    this._player = input;
  }

  public get board(): Board {
    return this._board;
  }

  public set board(input: Board) {
    this._board = input;
  }
  public get x() {
    return this._player.location[0] * this.board.sqrSize + this.board.border;
  }
  public get y() {
    let offset = 0;
    console.log(this._player);
    if (this._player.id == 2) {
      offset = 160;
    }
    return (
      this._player.location[1] * this.board.sqrSize + this.board.border + offset
    );
  }
}
