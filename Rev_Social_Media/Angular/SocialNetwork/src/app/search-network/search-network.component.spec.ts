import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchNetworkComponent } from './search-network.component';

describe('SearchNetworkComponent', () => {
  let component: SearchNetworkComponent;
  let fixture: ComponentFixture<SearchNetworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchNetworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchNetworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
