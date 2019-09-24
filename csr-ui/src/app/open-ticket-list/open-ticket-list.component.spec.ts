import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenTicketListComponent } from './open-ticket-list.component';

describe('OpenTicketListComponent', () => {
  let component: OpenTicketListComponent;
  let fixture: ComponentFixture<OpenTicketListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenTicketListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenTicketListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
