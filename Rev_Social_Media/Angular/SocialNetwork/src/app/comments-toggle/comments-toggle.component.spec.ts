import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentsToggleComponent } from './comments-toggle.component';

describe('CommentsToggleComponent', () => {
  let component: CommentsToggleComponent;
  let fixture: ComponentFixture<CommentsToggleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentsToggleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentsToggleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
