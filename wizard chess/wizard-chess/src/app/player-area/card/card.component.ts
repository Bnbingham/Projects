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

  constructor(private canvasService: CanvasService) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.canvasService.drawCard(this.cardData);
    }, 1);
  }
  onClick() {
    this.canvasService.actionSwitch(this.cardData);
  }
}
