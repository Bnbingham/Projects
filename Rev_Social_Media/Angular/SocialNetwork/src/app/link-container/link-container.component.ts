import { Component, OnInit, Input } from "@angular/core";

@Component({
  selector: "link-container",
  templateUrl: "./link-container.component.html",
  styleUrls: ["./link-container.component.css"],
})
export class LinkContainerComponent implements OnInit {
  @Input("link") link;
  constructor() {}

  ngOnInit(): void {}
}
