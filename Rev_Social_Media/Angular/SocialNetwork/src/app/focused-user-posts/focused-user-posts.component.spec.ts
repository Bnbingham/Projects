import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FocusedUserPostsComponent } from './focused-user-posts.component';

describe('FocusedUserPostsComponent', () => {
  let component: FocusedUserPostsComponent;
  let fixture: ComponentFixture<FocusedUserPostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FocusedUserPostsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FocusedUserPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
