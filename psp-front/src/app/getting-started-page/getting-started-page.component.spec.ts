import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GettingStartedPageComponent } from './getting-started-page.component';

describe('GettingStartedPageComponent', () => {
  let component: GettingStartedPageComponent;
  let fixture: ComponentFixture<GettingStartedPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GettingStartedPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GettingStartedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
