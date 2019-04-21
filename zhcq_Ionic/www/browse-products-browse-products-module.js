(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["browse-products-browse-products-module"],{

/***/ "./src/app/browse-products/browse-products.module.ts":
/*!***********************************************************!*\
  !*** ./src/app/browse-products/browse-products.module.ts ***!
  \***********************************************************/
/*! exports provided: BrowseProductsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BrowseProductsPageModule", function() { return BrowseProductsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _browse_products_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./browse-products.page */ "./src/app/browse-products/browse-products.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _browse_products_page__WEBPACK_IMPORTED_MODULE_6__["BrowseProductsPage"]
    }
];
var BrowseProductsPageModule = /** @class */ (function () {
    function BrowseProductsPageModule() {
    }
    BrowseProductsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["ReactiveFormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_browse_products_page__WEBPACK_IMPORTED_MODULE_6__["BrowseProductsPage"]]
        })
    ], BrowseProductsPageModule);
    return BrowseProductsPageModule;
}());



/***/ }),

/***/ "./src/app/browse-products/browse-products.page.html":
/*!***********************************************************!*\
  !*** ./src/app/browse-products/browse-products.page.html ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n<app-header></app-header>\n\n<ion-content>\n  <div *ngIf='errorMessage' class=\"errorText\">\n    {{errorMessage}}\n  </div>\n  <div *ngIf='!errorMessage'>\n    <ion-searchbar animated [formControl]=\"searchControl\"></ion-searchbar>\n\n    <h2 *ngIf='!catId' text-center style=\"font-weight: bold\">S &nbsp;&nbsp;H &nbsp;&nbsp;O &nbsp;&nbsp;P &nbsp;&nbsp;&nbsp; A&nbsp;&nbsp;L&nbsp;&nbsp;L</h2>\n    <div *ngIf='catId'>\n      <!-- <div *ngFor= \"let letter of category.categoryName.substring()\"> -->\n          <h2 text-center style=\"font-weight: bold\"> {{ category.categoryName }}</h2>\n      <!-- </div> -->\n      <!-- <h2 text-center style=\"font-weight: bold\" > C&nbsp;&nbsp;A&nbsp;&nbsp;T&nbsp;&nbsp;E&nbsp;&nbsp;G&nbsp;&nbsp;O&nbsp;&nbsp;R&nbsp;&nbsp;Y </h2> -->\n\n    </div>\n    \n    <ion-grid> \n      <ion-row>\n        <ion-col size=\"6\" *ngFor=\"let product of products\"  class=\"activated\" >\n          <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{product.picturePath}}\" class=\"img-responsive\" [routerLink]=\"['/product-details',product.productId]\"/>\n              <h6>{{product.productName}} &nbsp; <img src=\"http://localhost:8080/ZhcqRetailSystem-war/images/{{product.colourEnum}}.jpg\" class=\"colorImg\"></h6>\n              \n        </ion-col>\n      </ion-row>\n    </ion-grid>\n   <!-- <ion-infinite-scroll (ionInfinite)=\"loadMoreProducts($event)\">\n      <ion-infinite-scroll-content></ion-infinite-scroll-content>\n    </ion-infinite-scroll>  -->\n  </div>\n</ion-content>\n<app-footer></app-footer>\n\n"

/***/ }),

/***/ "./src/app/browse-products/browse-products.page.scss":
/*!***********************************************************!*\
  !*** ./src/app/browse-products/browse-products.page.scss ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".colorImg {\n  border: 1px;\n  height: 15px;\n  width: 15px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvYnJvd3NlLXByb2R1Y3RzL2Jyb3dzZS1wcm9kdWN0cy5wYWdlLnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxXQUFXO0VBQ1gsWUFBWTtFQUNaLFdBQVcsRUFBQSIsImZpbGUiOiJzcmMvYXBwL2Jyb3dzZS1wcm9kdWN0cy9icm93c2UtcHJvZHVjdHMucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmNvbG9ySW1ne1xuICAgIGJvcmRlcjogMXB4O1xuICAgIGhlaWdodDogMTVweDtcbiAgICB3aWR0aDogMTVweDtcbn0iXX0= */"

/***/ }),

/***/ "./src/app/browse-products/browse-products.page.ts":
/*!*********************************************************!*\
  !*** ./src/app/browse-products/browse-products.page.ts ***!
  \*********************************************************/
/*! exports provided: BrowseProductsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BrowseProductsPage", function() { return BrowseProductsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_product_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/product.service */ "./src/app/services/product.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var _services_category_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../services/category.service */ "./src/app/services/category.service.ts");
/* harmony import */ var _entities_category__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../entities/category */ "./src/app/entities/category.ts");









var BrowseProductsPage = /** @class */ (function () {
    function BrowseProductsPage(productService, activatedRoute, alertController, categoryService) {
        this.productService = productService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.categoryService = categoryService;
        this.errorMessage = '';
        this.category = new _entities_category__WEBPACK_IMPORTED_MODULE_8__["Category"]();
        this.searchControl = new _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormControl"]();
        this.errorMessage = '';
        // this.images = this.navParams.get('images'); //get product image URIs
        // this.grid = Array(Math.ceil(this.images.length/2)); 
    }
    BrowseProductsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.catId = parseInt(this.activatedRoute.snapshot.paramMap.get('catId'));
        console.log('CATEGORY ID IS: ' + this.catId);
        if (!isNaN(this.catId)) { // Localhost:8100/browse-products/id
            this.categoryService.retrieveCategoryById(this.catId).subscribe(function (response) {
                _this.category = response.category;
                console.log('category retrieved = ' + _this.category.categoryName);
            }, function (error) {
                console.log('Entered Error Message');
                _this.errorMessage = error;
                _this.presentAlert(_this.errorMessage.substring(37));
            });
            this.productService.retrieveProductByCat(this.catId).subscribe(function (response) {
                _this.retrievedProducts = response.products;
                _this.products = _this.retrievedProducts;
                _this.searchControl.valueChanges.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_6__["debounceTime"])(700)).subscribe(function (search) {
                    _this.products = _this.retrievedProducts;
                    _this.setFilteredItems(search);
                });
            }, function (error) {
                _this.errorMessage = error;
            });
        }
        else { // Localhost:8100/browse-products
            this.productService.retrieveAllUniqueProducts().subscribe(function (response) {
                _this.retrievedProducts = response.products;
                _this.products = _this.retrievedProducts;
                _this.searchControl.valueChanges.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_6__["debounceTime"])(700)).subscribe(function (search) {
                    _this.products = _this.retrievedProducts;
                    _this.setFilteredItems(search);
                });
            }, function (error) {
                _this.errorMessage = error;
                _this.presentAlert(_this.errorMessage.substring(37));
            });
        }
    };
    BrowseProductsPage.prototype.setFilteredItems = function (searchTerm) {
        this.products = this.filterItems(searchTerm);
    };
    BrowseProductsPage.prototype.filterItems = function (searchTerm) {
        console.log('PRODUCTS ARRAY IN FILTER ITEMS METHOD: ' + this.products);
        return this.products.filter(function (product) {
            console.log('Item Name = ' + product.productName);
            console.log('Search Term = ' + searchTerm);
            return product.productName.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;
        });
    };
    BrowseProductsPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'Error: ' + message,
                            buttons: ['OK']
                        })];
                    case 1:
                        alert = _a.sent();
                        return [4 /*yield*/, alert.present()];
                    case 2:
                        _a.sent();
                        return [2 /*return*/];
                }
            });
        });
    };
    BrowseProductsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-browse-products',
            template: __webpack_require__(/*! ./browse-products.page.html */ "./src/app/browse-products/browse-products.page.html"),
            styles: [__webpack_require__(/*! ./browse-products.page.scss */ "./src/app/browse-products/browse-products.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_product_service__WEBPACK_IMPORTED_MODULE_3__["ProductService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_4__["ActivatedRoute"], _ionic_angular__WEBPACK_IMPORTED_MODULE_2__["AlertController"], _services_category_service__WEBPACK_IMPORTED_MODULE_7__["CategoryService"]])
    ], BrowseProductsPage);
    return BrowseProductsPage;
}());



/***/ }),

/***/ "./src/app/entities/category.ts":
/*!**************************************!*\
  !*** ./src/app/entities/category.ts ***!
  \**************************************/
/*! exports provided: Category */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Category", function() { return Category; });
var Category = /** @class */ (function () {
    function Category(categoryId, categoryName, description, picturePath) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.picturePath = picturePath;
    }
    return Category;
}());



/***/ })

}]);
//# sourceMappingURL=browse-products-browse-products-module.js.map