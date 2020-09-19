export class Card {
  constructor(
    id: number = 0,
    name: String = "",
    type: String = "",
    subType: String = "",
    action: number[] = []
  ) {}
  id: number;
  name: String;
  type: String;
  subType: String;
  action: number[];
}
/*
action

Grid - for area of effect 
  [ 0][ 1][ 2][ 3]
  [ 4][ 5][ 6][ 7]
  [ 8][ 9][10][11]
  [12][13][14][15]

  [0,3,5,6] - attack squares 0,3,5,6

Path - for step teleport and beam
  [x,y]

  [-1,0] - step one left
  [1,2] - to teleport to 1-2
  [2,4] - beam attack 4 ahead then right 2
*/
