import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordinatedOutfitsPage } from './coordinated-outfits.page';

describe('CoordinatedOutfitsPage', () => {
  let component: CoordinatedOutfitsPage;
  let fixture: ComponentFixture<CoordinatedOutfitsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoordinatedOutfitsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoordinatedOutfitsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
