import { TestBed } from '@angular/core/testing';

import { SalestransactionService } from './salestransaction.service';

describe('SalestransactionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalestransactionService = TestBed.get(SalestransactionService);
    expect(service).toBeTruthy();
  });
});
