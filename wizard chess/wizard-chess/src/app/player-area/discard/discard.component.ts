import { Component, OnInit } from "@angular/core";

@Component({
  selector: "discard",
  templateUrl: "./discard.component.html",
  styleUrls: ["./discard.component.css"],
})
export class DiscardComponent implements OnInit {
  constructor() {}
  discardedCards = [1, 2, 3, 4, 4];
  ngOnInit(): void {}
}
