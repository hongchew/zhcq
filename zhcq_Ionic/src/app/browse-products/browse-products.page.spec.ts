import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseProductsPage } from './browse-products.page';

describe('BrowseProductsPage', () => {
  let component: BrowseProductsPage;
  let fixture: ComponentFixture<BrowseProductsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrowseProductsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseProductsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
