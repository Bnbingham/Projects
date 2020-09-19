import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameMatComponent } from './game-mat.component';

describe('GameMatComponent', () => {
  let component: GameMatComponent;
  let fixture: ComponentFixture<GameMatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GameMatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameMatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
