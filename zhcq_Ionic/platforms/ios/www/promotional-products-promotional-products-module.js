(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["promotional-products-promotional-products-module"],{

/***/ "./src/app/promotional-products/promotional-products.module.ts":
/*!*********************************************************************!*\
  !*** ./src/app/promotional-products/promotional-products.module.ts ***!
  \*********************************************************************/
/*! exports provided: PromotionalProductsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PromotionalProductsPageModule", function() { return PromotionalProductsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _promotional_products_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./promotional-products.page */ "./src/app/promotional-products/promotional-products.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _promotional_products_page__WEBPACK_IMPORTED_MODULE_6__["PromotionalProductsPage"]
    }
];
var PromotionalProductsPageModule = /** @class */ (function () {
    function PromotionalProductsPageModule() {
    }
    PromotionalProductsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_promotional_products_page__WEBPACK_IMPORTED_MODULE_6__["PromotionalProductsPage"]]
        })
    ], PromotionalProductsPageModule);
    return PromotionalProductsPageModule;
}());



/***/ }),

/***/ "./src/app/promotional-products/promotional-products.page.html":
/*!*********************************************************************!*\
  !*** ./src/app/promotional-products/promotional-products.page.html ***!
  \*********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n  <div *ngIf='errorMessage' class=\"errorText\">\n        {{errorMessage}}\n  </div>\n  <div *ngIf='!errorMessage'>\n    <ion-img src=\"assets/sale-header.jpg\"></ion-img>\n\n    <ion-grid *ngFor=\"let promo of promotions\">\n      <ion-row>\n        <ion-col>\n            <h4 style=\"text-align: center\" class=\"promotionName\"><b>{{ promo.promotionName }}</b></h4>\n        </ion-col>\n      </ion-row>\n        \n      <ion-slides [options]=\"sliderOpts\" >\n          <ion-slide *ngFor=\"let pdt of promo.promotionalProducts\" [routerLink]=\"['/product-details', pdt.productId]\" >\n              <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{pdt.picturePath}}\" class=\"thumb-img\"/>\n        </ion-slide>\n      </ion-slides>\n    </ion-grid>\n  </div>\n</ion-content>\n\n<app-footer></app-footer>"

/***/ }),

/***/ "./src/app/promotional-products/promotional-products.page.scss":
/*!*********************************************************************!*\
  !*** ./src/app/promotional-products/promotional-products.page.scss ***!
  \*********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".thumb-img {\n  height: 168px;\n  width: 125px; }\n\n.promotionName {\n  font-weight: bold;\n  padding: 5%;\n  background-color: #bd0b2f;\n  color: white; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvcHJvbW90aW9uYWwtcHJvZHVjdHMvcHJvbW90aW9uYWwtcHJvZHVjdHMucGFnZS5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBRUksYUFBYTtFQUNiLFlBQVksRUFBQTs7QUFHaEI7RUFDSSxpQkFBaUI7RUFDakIsV0FBVztFQUVYLHlCQUFrQztFQUNsQyxZQUFXLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9wcm9tb3Rpb25hbC1wcm9kdWN0cy9wcm9tb3Rpb25hbC1wcm9kdWN0cy5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIudGh1bWItaW1ne1xuICAgIC8vIHBhZGRpbmc6IDVweDtcbiAgICBoZWlnaHQ6IDE2OHB4O1xuICAgIHdpZHRoOiAxMjVweDtcbn1cblxuLnByb21vdGlvbk5hbWV7XG4gICAgZm9udC13ZWlnaHQ6IGJvbGQ7XG4gICAgcGFkZGluZzogNSU7XG4gICAgLy8gYm9yZGVyOiAxcHggc29saWQgYmxhY2s7XG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmdiKDE4OSwgMTEsIDQ3KTtcbiAgICBjb2xvcjp3aGl0ZTtcbn1cbiJdfQ== */"

/***/ }),

/***/ "./src/app/promotional-products/promotional-products.page.ts":
/*!*******************************************************************!*\
  !*** ./src/app/promotional-products/promotional-products.page.ts ***!
  \*******************************************************************/
/*! exports provided: PromotionalProductsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PromotionalProductsPage", function() { return PromotionalProductsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_promotion_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/promotion.service */ "./src/app/services/promotion.service.ts");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");




var PromotionalProductsPage = /** @class */ (function () {
    function PromotionalProductsPage(promotionService, alertController) {
        this.promotionService = promotionService;
        this.alertController = alertController;
        this.errorMessage = '';
        this.sliderOpts = {
            zoom: false,
            slidesPerView: 2.5,
            spaceBetween: true
        };
    }
    PromotionalProductsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.promotionService.retrieveAllPromotions().subscribe(function (response) {
            _this.promotions = response.promotions;
        }, function (error) {
            _this.errorMessage = error;
            _this.presentAlert(_this.errorMessage.substring(37));
        });
    };
    PromotionalProductsPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'ERROR: ' + message,
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
    PromotionalProductsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-promotional-products',
            template: __webpack_require__(/*! ./promotional-products.page.html */ "./src/app/promotional-products/promotional-products.page.html"),
            styles: [__webpack_require__(/*! ./promotional-products.page.scss */ "./src/app/promotional-products/promotional-products.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_promotion_service__WEBPACK_IMPORTED_MODULE_2__["PromotionService"], _ionic_angular__WEBPACK_IMPORTED_MODULE_3__["AlertController"]])
    ], PromotionalProductsPage);
    return PromotionalProductsPage;
}());



/***/ }),

/***/ "./src/app/services/promotion.service.ts":
/*!***********************************************!*\
  !*** ./src/app/services/promotion.service.ts ***!
  \***********************************************/
/*! exports provided: PromotionService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PromotionService", function() { return PromotionService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");





var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({ 'Content-Type': 'application/json' })
};
var PromotionService = /** @class */ (function () {
    function PromotionService(httpClient) {
        this.httpClient = httpClient;
        this.baseUrl = 'http://localhost:8080/ZhcqRetailSystem-war/Resources/Promotion';
    }
    PromotionService.prototype.retrieveAllPromotions = function () {
        return this.httpClient.get(this.baseUrl + '/retrieveAllPromotions').pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    PromotionService.prototype.handleError = function (error) {
        var errorMessage = "";
        if (error.error instanceof ErrorEvent) {
            errorMessage = 'An unknown error has occurred: ' + error.error.message;
        }
        else {
            errorMessage = 'A HTTP error has occurred: ' + ("HTTP " + error.status + ": " + error.error.message);
        }
        console.error(errorMessage);
        return Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["throwError"])(errorMessage);
    };
    PromotionService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], PromotionService);
    return PromotionService;
}());



/***/ })

}]);
//# sourceMappingURL=promotional-products-promotional-products-module.js.map