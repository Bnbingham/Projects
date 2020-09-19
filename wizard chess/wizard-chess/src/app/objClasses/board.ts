export class Board {
  constructor(private _sqrSize: number = 40, private _border: number = 4) {}
  public get sqrSize(): number {
    return this._sqrSize;
  }

  public set sqrSize(input: number) {
    this._sqrSize = input;
  }
  public get border(): number {
    return this._border;
  }

  public set border(input: number) {
    this._border = input;
  }
  public get height() {
    return this._sqrSize * 8;
  }
  public get width() {
    return this._sqrSize * 4;
  }
  public get tokenSize() {
    return this._sqrSize - this.border * 2;
  }
}
