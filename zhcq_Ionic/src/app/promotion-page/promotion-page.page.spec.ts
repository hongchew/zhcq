import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionPagePage } from './promotion-page.page';

describe('PromotionPagePage', () => {
  let component: PromotionPagePage;
  let fixture: ComponentFixture<PromotionPagePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionPagePage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionPagePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
