import { HitboxService } from "./../../services/hitbox.service";
import { Card } from "./../../objClasses/card";
import { Component, Input, OnInit } from "@angular/core";
import { CanvasService } from "src/app/services/canvas.service";

@Component({
  selector: "card",
  templateUrl: "./card.component.html",
  styleUrls: ["./card.component.css"],
})
export class CardComponent implements OnInit {
  @Input() cardData: Card = new Card();

  constructor(
    private canvasService: CanvasService,
    private hitBoxService: HitboxService
  ) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.canvasService.drawCard(this.cardData);
    }, 1);
  }
  onClick() {
    this.canvasService.actionSwitch(this.cardData);
    if (this.cardData.type == "Attack") {
      this.hitBoxService.checkHit(this.cardData);
    }
  }
  onMouseEnter() {
    this.canvasService.mouseEnterEffect(this.cardData);
  }
  onMouseLeave() {
    this.canvasService.mouseLeaveEvent();
  }
}
