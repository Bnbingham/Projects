export class Player {
  constructor(_id?: number, _location?: number[]) {
    this.id = _id;
    this.location = _location;
  }
  private _id: number;
  private _location: number[];
  private _health: number = 3;

  public get id(): number {
    return this._id;
  }
  public set id(input: number) {
    this._id = input;
  }
  public get location(): number[] {
    return this._location;
  }
  public set location(input: number[]) {
    this._location = input;
  }
  public get health(): number {
    return this._health;
  }
  public set health(input: number) {
    this._health = input;
  }
}
