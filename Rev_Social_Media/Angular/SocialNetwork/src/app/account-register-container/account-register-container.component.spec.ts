import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountRegisterContainerComponent } from './account-register-container.component';

describe('AccountRegisterContainerComponent', () => {
  let component: AccountRegisterContainerComponent;
  let fixture: ComponentFixture<AccountRegisterContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountRegisterContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountRegisterContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
