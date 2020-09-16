var canvas;
var ctx;

//board
let sqr = 40;
let width = sqr * 4;
let height = sqr * 8;
let border = 4;

//tokens
let defaultTokenSize = sqr - border * 2;

var LocP1 = [3, 0];
var LocP2 = [3, 1];
let p1x = LocP1[0] * sqr + border;
let p1y = LocP1[1] * sqr + border;
let p2x = LocP2[0] * sqr + border;
let p2y = LocP2[1] * sqr + width + border;

function init() {
  canvas = document.getElementById("myCanvas");
  ctx = canvas.getContext("2d");

  drawBoard();
  drawPlayers();
  //   drawBeam();
}
function drawBoard() {
  //board
  let row = 0;
  while (row < 8) {
    for (let col = 0; col < 4; col++) {
      let x = col * sqr;
      ctx.strokeRect(x, row * sqr, sqr, sqr);
    }
    row++;
  }
  //center line
  ctx.fillStyle = "orange";
  ctx.fillRect(0, 159, 160, 2);
}
function drawPlayers() {
  let playerSize = defaultTokenSize;
  ctx.fillStyle = "rgb(200, 0, 0)";
  ctx.fillRect(p1x, p1y, playerSize, playerSize);

  ctx.fillStyle = "rgb(0, 0, 200)";
  ctx.fillRect(p2x, p2y, playerSize, playerSize);
}

function drawBeam() {
  //beam[0] is forward, beam[1] is left(-) right(+)
  let beamPath = [4, -2];
  let beamColor = "green";
  let SizeRelativeToPlayer = 0.5;

  let relativeSize = defaultTokenSize * SizeRelativeToPlayer;
  let relativeOffset = (defaultTokenSize - relativeSize) / 2;
  let relativeP1x = p1x + relativeOffset;
  let bSqr = sqr + relativeOffset;

  //color of beam
  ctx.fillStyle = beamColor;
  //beam forward travel
  if (beamPath[0] == 0) {
    ctx.fillRect(
      relativeP1x,
      p1y + bSqr,
      relativeSize,
      relativeSize * beamPath[0]
    );
  } else {
    ctx.fillRect(
      relativeP1x,
      p1y + bSqr,
      relativeSize,
      relativeSize * beamPath[0] +
        2 * border * (beamPath[0] - 1) +
        relativeOffset * 2 * (beamPath[0] - 1)
    );
  }
  //beam sideways travel
  if (beamPath[1] != 0) {
    if (beamPath[0] == 0) {
      //direct beams left or right from position
    } else {
      if (beamPath[1] > 0) {
        //traveling right
        ctx.fillRect(
          relativeP1x + relativeSize,
          p1y + sqr * beamPath[0] + relativeOffset,
          relativeSize * beamPath[1] +
            border * 2 * beamPath[1] +
            relativeOffset * 2 * beamPath[1],
          relativeSize
        );
      } else {
        //traveling left
        ctx.fillRect(
          relativeP1x,
          p1y + sqr * beamPath[0] + relativeOffset,
          relativeSize * beamPath[1] +
            border * 2 * beamPath[1] +
            relativeOffset * 2 * beamPath[1],
          relativeSize
        );
      }
    }
  }
}
class Movement {
  constructor(type, action) {
    this.type = type;
    this.action = action;
  }
}

p1MoveHand = {
  type: "step",
  action: [1, 0],
};
