import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { ActiveComponent } from "./player-area/active/active.component";
import { CardComponent } from "./player-area/card/card.component";
import { DiscardComponent } from "./player-area/discard/discard.component";
import { GameMatComponent } from "./player-area/game-mat/game-mat.component";
import { HandComponent } from "./player-area/hand/hand.component";

@NgModule({
  declarations: [
    AppComponent,
    GameMatComponent,
    HandComponent,
    DiscardComponent,
    ActiveComponent,
    CardComponent,
  ],
  imports: [BrowserModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
