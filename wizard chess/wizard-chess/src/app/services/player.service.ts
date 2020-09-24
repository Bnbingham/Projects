import { BehaviorSubject, Observable } from "rxjs";
import { Player } from "./../objClasses/Player";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class PlayerService {
  p1: Player;
  p2: Player;
  constructor() {}
  newPlayers() {
    this.p1 = new Player(1, [1, 0]);
    this.p2 = new Player(2, [0, 3]);
  }
}
