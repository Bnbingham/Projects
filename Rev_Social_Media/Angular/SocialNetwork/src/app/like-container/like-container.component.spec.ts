import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LikeContainerComponent } from './like-container.component';

describe('LikeContainerComponent', () => {
  let component: LikeContainerComponent;
  let fixture: ComponentFixture<LikeContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LikeContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LikeContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
