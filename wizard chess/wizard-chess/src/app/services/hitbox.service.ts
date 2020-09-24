import { Card } from "src/app/objClasses/card";
import { PlayerService } from "./player.service";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class HitboxService {
  private p1 = this.playerService.p1;
  private p2 = this.playerService.p2;

  constructor(private playerService: PlayerService) {}
  checkHit(input: Card, player = this.p2) {
    if (input.subType == "Ray") {
      console.log("in Ray");
      let sqrsHit = [];
      // forawrd ray path
      let rowsHit = input.action[0] - this.p2.location[1];
      if (rowsHit >= 4) rowsHit = 4;
      for (let currRow = 0; currRow < rowsHit; currRow++) {
        let rayX = this.p2.location[0];
        sqrsHit.push([rayX, 3 - currRow]);
      }
      // sideways ray path
      //todo:clean this up
      let colHit = 4 - rowsHit;
      if (input.action[1] != 0) {
        if (input.action[1] > 0) {
          // calc right
          for (let xTravel = 1; xTravel < input.action[0]; xTravel++) {
            let thisSqr = xTravel + this.p2.location[0];
            if (thisSqr < 4) {
              sqrsHit.push([thisSqr, colHit]);
            }
          }
        } else {
          // calc left
          let left = this.p2.location[0] + input.action[1];
          for (let index = left; index < this.p2.location[0]; index++) {
            if (index >= 0) {
              sqrsHit.push([index, colHit]);
            }
          }
        }
      }
      let pX = this.p1.location[0];
      let pY = this.p1.location[1];
      let hit: boolean;
      sqrsHit.forEach((sqr) => {
        let aX = sqr[0];
        let aY = sqr[1];
        if (pX == aX && pY == aY) {
          hit = true;
        }
      });
      if (hit) {
        //todo: hit logic
        console.log("hit");
      } else {
        console.log("miss");
      }
    } else {
      let hit: boolean;
      // player location
      let pX = this.p1.location[0];
      let pY = this.p1.location[1];
      input.action.forEach((sqr) => {
        // attack location
        let aX = sqr[0];
        let aY = sqr[1];
        if (pX == aX && pY == aY) {
          hit = true;
        }
      });
      if (hit) {
        //todo: hit logic
        console.log("hit");
      } else {
        console.log("miss");
      }
    }
  }
}
