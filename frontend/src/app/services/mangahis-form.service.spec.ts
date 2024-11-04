import { TestBed } from '@angular/core/testing';

import { MangahisFormService } from './mangahis-form.service';

describe('MangahisFormService', () => {
  let service: MangahisFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MangahisFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
