import { TestBed } from '@angular/core/testing';

import { TestEntService } from './test-ent.service';

describe('TestEntService', () => {
  let service: TestEntService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestEntService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
