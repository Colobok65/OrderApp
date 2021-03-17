import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodsOrderComponent } from './goods-order.component';

describe('GoodsOrderComponent', () => {
  let component: GoodsOrderComponent;
  let fixture: ComponentFixture<GoodsOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GoodsOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GoodsOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
