import { TestBed } from '@angular/core/testing';

import { GoodsOrderService } from './goods-order.service';

describe('GoodsOrderService', () => {
  let service: GoodsOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GoodsOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
