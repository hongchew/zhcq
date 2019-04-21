import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionalProductsPage } from './promotional-products.page';

describe('PromotionalProductsPage', () => {
  let component: PromotionalProductsPage;
  let fixture: ComponentFixture<PromotionalProductsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionalProductsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionalProductsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
