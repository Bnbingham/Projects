export class Board {
  constructor(
    private _sqrSize: number = 40,
    private _totalRows: number = 8,
    private _elementId: string = "myCanvas"
  ) {}
  private _tokenPadding: number = 4;
  private _canvas;
  private _ctx;

  public get ctx() {
    return this._ctx;
  }
  public get sqrSize(): number {
    return this._sqrSize;
  }
  public set sqrSize(input: number) {
    this._sqrSize = input;
  }
  public get tknPadding(): number {
    return this._tokenPadding;
  }
  public get totalRows() {
    return this._totalRows;
  }
  public set tknPadding(input: number) {
    this._tokenPadding = input;
  }
  public get height() {
    return this._sqrSize * this._totalRows;
  }
  public get width() {
    return this._sqrSize * 4;
  }
  public get tokenSize() {
    return this._sqrSize - this.tknPadding * 2;
  }
  public getCanvasContext() {
    this._canvas = document.getElementById(this._elementId);
    this._ctx = this._canvas.getContext("2d");
  }
}
