import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SizeguidePage } from './sizeguide.page';

describe('SizeguidePage', () => {
  let component: SizeguidePage;
  let fixture: ComponentFixture<SizeguidePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SizeguidePage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SizeguidePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
