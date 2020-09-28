import { PlayerService } from "./services/player.service";
import { Player } from "./objClasses/Player";
import { Component } from "@angular/core";
import { Card } from "./objClasses/card";
import { CanvasService } from "./services/canvas.service";
import { from, fromEvent, interval, Observable } from "rxjs";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  p1: Player = new Player();
  p2: Player = new Player();

  constructor(
    private playerService: PlayerService,
    private canvasService: CanvasService
  ) {}
  ngOnInit() {
    this.canvasService.start();

    this.p1 = this.playerService.p1;
    this.p2 = this.playerService.p2;
  }
  onClick() {
    this.canvasService.restoreLastHistory();
  }
}
