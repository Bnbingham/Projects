import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostContainerFeedComponent } from './post-container-feed.component';

describe('PostContainerFeedComponent', () => {
  let component: PostContainerFeedComponent;
  let fixture: ComponentFixture<PostContainerFeedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostContainerFeedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostContainerFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
