import { Component } from "@angular/core";
import { Card } from "./objClasses/card";
import { CanvasService } from "./services/canvas.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  constructor(private canvasService: CanvasService) {}
  ngOnInit() {
    this.canvasService.start();
  }
}
