import { TestBed } from '@angular/core/testing';

import { School, SchoolService } from './school';

describe('SchoolService', () => {
  let service: SchoolService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SchoolService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
