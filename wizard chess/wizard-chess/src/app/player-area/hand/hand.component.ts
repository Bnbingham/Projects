import { Component, OnInit } from "@angular/core";
import { CardService } from "src/app/services/card.service";

@Component({
  selector: "hand",
  templateUrl: "./hand.component.html",
  styleUrls: ["./hand.component.css"],
})
export class HandComponent implements OnInit {
  constructor(private cardService: CardService) {}
  inHand: any;
  ngOnInit(): void {
    this.inHand = this.cardService.inHand;
  }
}
