(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["about-us-about-us-module"],{

/***/ "./src/app/about-us/about-us.module.ts":
/*!*********************************************!*\
  !*** ./src/app/about-us/about-us.module.ts ***!
  \*********************************************/
/*! exports provided: AboutUsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AboutUsPageModule", function() { return AboutUsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _about_us_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./about-us.page */ "./src/app/about-us/about-us.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _about_us_page__WEBPACK_IMPORTED_MODULE_6__["AboutUsPage"]
    }
];
var AboutUsPageModule = /** @class */ (function () {
    function AboutUsPageModule() {
    }
    AboutUsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_about_us_page__WEBPACK_IMPORTED_MODULE_6__["AboutUsPage"]]
        })
    ], AboutUsPageModule);
    return AboutUsPageModule;
}());



/***/ }),

/***/ "./src/app/about-us/about-us.page.html":
/*!*********************************************!*\
  !*** ./src/app/about-us/about-us.page.html ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n  <ion-img src=\"/assets/about-us-header.jpg\" ></ion-img>\n  <ion-grid>\n    <ion-row style=\"padding: 5%\">\n      <ion-col >\n        <ion-img src=\"/assets/about-us-1.jpg\" ></ion-img>\n      </ion-col>\n      <ion-col>\n        <ion-row style=\"padding-top: 50%; padding-left: 20%\">\n            zhcq's design mission is to make effortless silhouettes that celebrate the feminine figure, all whilst making fashion effortless. \n        </ion-row>\n        \n      </ion-col>\n    </ion-row>\n      \n    <ion-row style=\"padding: 10%\">\n      <ion-col style=\"text-align: center\">\n        <ion-img src=\"/assets/about-us-3.jpg\" ></ion-img><br/>\n        <b>No time for fashion</b> but still want to <b>look your best</b>?\n        Most fashion is designed 12-18 months before it’s released, but at zhcq, a sketch of the latest trends can become a dress in about a month. Fast, trendy, effortless.\n      </ion-col>\n    </ion-row>\n    <ion-row>\n        <ion-col style=\"padding-top: 10% ; padding-left: 10%\">\n          We believe you, the customer, is at the heart of what we do. That's why our 365 Days Free return policy is award winning for best customer service. \n          \n        </ion-col>\n        <ion-col>\n          <ion-img src=\"/assets/about-us-2.jpg\" ></ion-img>\n        </ion-col>\n      </ion-row>\n    \n  </ion-grid>\n  \n  \n</ion-content>\n<app-footer></app-footer>"

/***/ }),

/***/ "./src/app/about-us/about-us.page.scss":
/*!*********************************************!*\
  !*** ./src/app/about-us/about-us.page.scss ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Fib3V0LXVzL2Fib3V0LXVzLnBhZ2Uuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/about-us/about-us.page.ts":
/*!*******************************************!*\
  !*** ./src/app/about-us/about-us.page.ts ***!
  \*******************************************/
/*! exports provided: AboutUsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AboutUsPage", function() { return AboutUsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var AboutUsPage = /** @class */ (function () {
    function AboutUsPage() {
    }
    AboutUsPage.prototype.ngOnInit = function () {
    };
    AboutUsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-about-us',
            template: __webpack_require__(/*! ./about-us.page.html */ "./src/app/about-us/about-us.page.html"),
            styles: [__webpack_require__(/*! ./about-us.page.scss */ "./src/app/about-us/about-us.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], AboutUsPage);
    return AboutUsPage;
}());



/***/ })

}]);
//# sourceMappingURL=about-us-about-us-module.js.map