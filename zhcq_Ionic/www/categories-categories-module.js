(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["categories-categories-module"],{

/***/ "./src/app/categories/categories.module.ts":
/*!*************************************************!*\
  !*** ./src/app/categories/categories.module.ts ***!
  \*************************************************/
/*! exports provided: CategoriesPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CategoriesPageModule", function() { return CategoriesPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _categories_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./categories.page */ "./src/app/categories/categories.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _categories_page__WEBPACK_IMPORTED_MODULE_6__["CategoriesPage"]
    }
];
var CategoriesPageModule = /** @class */ (function () {
    function CategoriesPageModule() {
    }
    CategoriesPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_categories_page__WEBPACK_IMPORTED_MODULE_6__["CategoriesPage"]]
        })
    ], CategoriesPageModule);
    return CategoriesPageModule;
}());



/***/ }),

/***/ "./src/app/categories/categories.page.html":
/*!*************************************************!*\
  !*** ./src/app/categories/categories.page.html ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n    <h1 style=\"text-align: center\"><b>S &nbsp;&nbsp;H&nbsp;&nbsp;O&nbsp;&nbsp;P</b></h1>\n\n  <ion-card [routerLink]=\"['/browse-products']\" style=\"background-color: rgb(240, 237, 237)\">\n    <ion-grid>\n      <ion-row>\n        <ion-col size=\"8\">\n            <ion-card-content style=\"height: 20%\">\n              <h1 style=\"padding-top: 13%\"><b>ALL</b></h1>\n            </ion-card-content>\n        </ion-col>\n        <ion-col size=\"4\">\n          <img class=\"images\" src=\"http://localhost:8080/ZhcqRetailSystem-war/images/ALL.jpg\"/>\n        </ion-col>\n      </ion-row>\n    </ion-grid>\n    \n  </ion-card>\n  \n  <ion-card *ngFor=\" let cat of categories\" style=\"background-color: rgb(240, 237, 237)\" [routerLink]=\"['/browse-products',cat.categoryId]\">\n    <ion-grid>\n      <ion-row>\n        <ion-col size=\"8\">\n          <ion-card-content style=\"height: 25%\">\n            <h1 style=\"padding-top: 13%\" ><b>{{ cat.categoryName }}</b></h1>\n          </ion-card-content>\n        </ion-col>\n        <ion-col size=\"4\">\n          <img class=\"images\" src=\"http://localhost:8080/ZhcqRetailSystem-war/{{ cat.picturePath }}\"/>\n        </ion-col>\n      </ion-row>\n    </ion-grid>\n  </ion-card>\n</ion-content>\n\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/categories/categories.page.scss":
/*!*************************************************!*\
  !*** ./src/app/categories/categories.page.scss ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".images {\n  width: 80%;\n  height: 100%; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvY2F0ZWdvcmllcy9jYXRlZ29yaWVzLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLFVBQVU7RUFDVixZQUFXLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9jYXRlZ29yaWVzL2NhdGVnb3JpZXMucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmltYWdlc3tcbiAgICB3aWR0aDogODAlO1xuICAgIGhlaWdodDoxMDAlO1xufVxuXG4iXX0= */"

/***/ }),

/***/ "./src/app/categories/categories.page.ts":
/*!***********************************************!*\
  !*** ./src/app/categories/categories.page.ts ***!
  \***********************************************/
/*! exports provided: CategoriesPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CategoriesPage", function() { return CategoriesPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_category_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/category.service */ "./src/app/services/category.service.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");






var CategoriesPage = /** @class */ (function () {
    function CategoriesPage(categoryService, activatedRoute, alertController) {
        this.categoryService = categoryService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.errorMessage = '';
        this.searchControl = new _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormControl"]();
    }
    CategoriesPage.prototype.ngOnInit = function () {
        var _this = this;
        this.categoryService.retrieveAllCategories().subscribe(function (response) {
            _this.categories = response.categories;
        }, function (error) {
            _this.errorMessage = error;
        });
    };
    CategoriesPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-categories',
            template: __webpack_require__(/*! ./categories.page.html */ "./src/app/categories/categories.page.html"),
            styles: [__webpack_require__(/*! ./categories.page.scss */ "./src/app/categories/categories.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_category_service__WEBPACK_IMPORTED_MODULE_2__["CategoryService"], _angular_router__WEBPACK_IMPORTED_MODULE_4__["ActivatedRoute"], _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["AlertController"]])
    ], CategoriesPage);
    return CategoriesPage;
}());



/***/ })

}]);
//# sourceMappingURL=categories-categories-module.js.map