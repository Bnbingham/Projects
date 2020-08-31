import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserContentInputFormComponent } from './user-content-input-form.component';

describe('UserContentInputFormComponent', () => {
  let component: UserContentInputFormComponent;
  let fixture: ComponentFixture<UserContentInputFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserContentInputFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserContentInputFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
