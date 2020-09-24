export class Card {
  constructor(
    id: number = 0,
    name: String = "",
    color: String = "",
    type: String = "",
    subType: String = "",
    action: any[] = []
  ) {}
  id: number;
  name: String;
  color: String;
  type: String;
  subType: String;
  action: any[];
}
