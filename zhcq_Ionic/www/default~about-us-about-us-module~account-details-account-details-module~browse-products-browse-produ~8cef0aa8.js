(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["default~about-us-about-us-module~account-details-account-details-module~browse-products-browse-produ~8cef0aa8"],{

/***/ "./src/app/components/footer/footer.component.html":
/*!*********************************************************!*\
  !*** ./src/app/components/footer/footer.component.html ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-footer>\n  <ion-buttons color=\"light\">\n    <ion-col>\n      <ion-button href=\"/\">\n        <ion-icon slot=\"icon-only\" name=\"home\"></ion-icon>\n      </ion-button>\n    </ion-col>\n    <ion-col>\n        <ion-button href=\"/categories\">\n          <ion-icon slot=\"icon-only\" name=\"list-box\"></ion-icon>\n        </ion-button>\n    </ion-col>\n    <ion-col>\n      <ion-button href=\"/shoppingcart\">\n        <ion-icon slot=\"icon-only\" name=\"basket\"></ion-icon>\n      </ion-button>\n    </ion-col>\n    <ion-col>\n      <ion-button href=\"/wishlist\">\n        <ion-icon slot=\"icon-only\" name=\"heart\"></ion-icon>\n      </ion-button>\n    </ion-col>\n    <ion-col>\n      <ion-button href=\"/account-details\">\n        <ion-icon slot=\"icon-only\" name=\"contact\"></ion-icon>\n      </ion-button>\n      <!--<div *ngIf=\"!loggedIn\">\n          <ion-button href=\"/register\" >\n            <ion-icon slot=\"icon-only\" name=\"add\"></ion-icon>\n          </ion-button>\n        </div>-->\n      \n    </ion-col>\n  </ion-buttons>\n  \n</ion-footer>\n"

/***/ }),

/***/ "./src/app/components/footer/footer.component.scss":
/*!*********************************************************!*\
  !*** ./src/app/components/footer/footer.component.scss ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvZm9vdGVyL2Zvb3Rlci5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/components/footer/footer.component.ts":
/*!*******************************************************!*\
  !*** ./src/app/components/footer/footer.component.ts ***!
  \*******************************************************/
/*! exports provided: FooterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FooterComponent", function() { return FooterComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/shoppingcart.service */ "./src/app/services/shoppingcart.service.ts");
/* harmony import */ var _services_wishlist_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/wishlist.service */ "./src/app/services/wishlist.service.ts");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");






var FooterComponent = /** @class */ (function () {
    function FooterComponent(cartService, wishListService, alertController, storage) {
        var _this = this;
        this.cartService = cartService;
        this.alertController = alertController;
        this.storage = storage;
        storage.get('currentCustomer').then(function (data) {
            _this.member = data;
        });
        storage.get('isLogin').then(function (data) {
            _this.loggedIn = data;
        });
    }
    FooterComponent.prototype.ngOnInit = function () {
    };
    FooterComponent.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            message: message,
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
    FooterComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-footer',
            template: __webpack_require__(/*! ./footer.component.html */ "./src/app/components/footer/footer.component.html"),
            styles: [__webpack_require__(/*! ./footer.component.scss */ "./src/app/components/footer/footer.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_2__["ShoppingCartService"], _services_wishlist_service__WEBPACK_IMPORTED_MODULE_3__["WishListService"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["AlertController"], _ionic_storage__WEBPACK_IMPORTED_MODULE_4__["Storage"]])
    ], FooterComponent);
    return FooterComponent;
}());



/***/ }),

/***/ "./src/app/components/header/header.component.html":
/*!*********************************************************!*\
  !*** ./src/app/components/header/header.component.html ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n  <title> zhcq & co.</title>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-menu-button></ion-menu-button>\n    </ion-buttons>\n    <ion-buttons slot=\"primary\">\n      <ion-button href=\"/browse-products\">\n        <ion-icon slot=\"icon-only\" name=\"search\"></ion-icon>\n      </ion-button>\n    </ion-buttons>\n    \n    <ion-item href=\"/home\" detail=\"false\" lines=\"none\">\n      <ion-title style=\"text-align:center\"><b>   zhcq & co. </b></ion-title>\n    </ion-item>\n    <!-- <ion-row>\n        <ion-col href=\"/home\">\n          <h1><b>   zhcq & co.  </b></h1>\n        </ion-col>\n    </ion-row> -->\n      \n    \n    \n    \n    \n  </ion-toolbar>\n</ion-header>\n\n\n\n"

/***/ }),

/***/ "./src/app/components/header/header.component.scss":
/*!*********************************************************!*\
  !*** ./src/app/components/header/header.component.scss ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NvbXBvbmVudHMvaGVhZGVyL2hlYWRlci5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/components/header/header.component.ts":
/*!*******************************************************!*\
  !*** ./src/app/components/header/header.component.ts ***!
  \*******************************************************/
/*! exports provided: HeaderComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HeaderComponent", function() { return HeaderComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/shoppingcart.service */ "./src/app/services/shoppingcart.service.ts");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");





var HeaderComponent = /** @class */ (function () {
    function HeaderComponent(cartService, alertController, storage) {
        var _this = this;
        this.cartService = cartService;
        this.alertController = alertController;
        this.storage = storage;
        storage.get('currentCustomer').then(function (data) {
            _this.member = data;
        });
        storage.get('isLogin').then(function (data) {
            _this.loggedIn = data;
        });
    }
    HeaderComponent.prototype.ngOnInit = function () {
    };
    HeaderComponent.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            message: message,
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
    HeaderComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-header',
            template: __webpack_require__(/*! ./header.component.html */ "./src/app/components/header/header.component.html"),
            styles: [__webpack_require__(/*! ./header.component.scss */ "./src/app/components/header/header.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_2__["ShoppingCartService"], _ionic_angular__WEBPACK_IMPORTED_MODULE_4__["AlertController"], _ionic_storage__WEBPACK_IMPORTED_MODULE_3__["Storage"]])
    ], HeaderComponent);
    return HeaderComponent;
}());



/***/ }),

/***/ "./src/app/components/shared-components.module.ts":
/*!********************************************************!*\
  !*** ./src/app/components/shared-components.module.ts ***!
  \********************************************************/
/*! exports provided: SharedComponentsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SharedComponentsModule", function() { return SharedComponentsModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _header_header_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./header/header.component */ "./src/app/components/header/header.component.ts");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _footer_footer_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./footer/footer.component */ "./src/app/components/footer/footer.component.ts");






// import { RouterModule, Routes } from '@angular/router';
// import { BrowseProductsPage } from '../browse-products/browse-products.page';
// import { ShoppingcartPage } from '../shoppingcart/shoppingcart.page';
// import { WishlistPage } from '../wishlist/wishlist.page';
// const routes: Routes = [
//   {
//     path: 'viewProducts',
//     component: BrowseProductsPage
//   },
//   {
//     path: 'shoppingCart',
//     component: ShoppingcartPage
//   },
//   {
//     path: 'wishList',
//     component: WishlistPage
//   }
// ];
var SharedComponentsModule = /** @class */ (function () {
    function SharedComponentsModule() {
    }
    SharedComponentsModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_header_header_component__WEBPACK_IMPORTED_MODULE_3__["HeaderComponent"], _footer_footer_component__WEBPACK_IMPORTED_MODULE_5__["FooterComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_4__["IonicModule"]
                // RouterModule.forChild(routes),
            ],
            exports: [_header_header_component__WEBPACK_IMPORTED_MODULE_3__["HeaderComponent"], _footer_footer_component__WEBPACK_IMPORTED_MODULE_5__["FooterComponent"]]
        })
    ], SharedComponentsModule);
    return SharedComponentsModule;
}());



/***/ }),

/***/ "./src/app/services/shoppingcart.service.ts":
/*!**************************************************!*\
  !*** ./src/app/services/shoppingcart.service.ts ***!
  \**************************************************/
/*! exports provided: ShoppingCartService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ShoppingCartService", function() { return ShoppingCartService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");





var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({ 'Content-Type': 'application/json' })
};
var ShoppingCartService = /** @class */ (function () {
    function ShoppingCartService(httpClient) {
        this.httpClient = httpClient;
        this.baseUrl = 'http://localhost:8080/ZhcqRetailSystem-war/Resources/ShoppingCart';
    }
    ShoppingCartService.prototype.retrieveShoppingCart = function (userId) {
        return this.httpClient.get(this.baseUrl + '/retrieveShoppingCart?userId=' + userId).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    ShoppingCartService.prototype.addToCart = function (cartId, productId, quantity) {
        return this.httpClient.post(this.baseUrl + '/addToCart?cartId=' + cartId + '&productId=' + productId + "&quantity=" + quantity, null, httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    ShoppingCartService.prototype.updateCart = function (cartId, productId, quantity) {
        return this.httpClient.post(this.baseUrl + '/updateCart?cartId=' + cartId + '&productId=' + productId + "&quantity=" + quantity, null, httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    ShoppingCartService.prototype.removeFromCart = function (cartId, productId) {
        return this.httpClient.delete(this.baseUrl + '?cartId=' + cartId + '&productId=' + productId).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    ShoppingCartService.prototype.checkout = function (cartId) {
        return this.httpClient.post(this.baseUrl + '/checkout?cartId=' + cartId, null, httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    ShoppingCartService.prototype.checkoutWithPoints = function (cartId) {
        return this.httpClient.post(this.baseUrl + '/checkoutWithPoints?cartId=' + cartId, null, httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    ShoppingCartService.prototype.handleError = function (error) {
        var errorMessage = "";
        if (error.error instanceof ErrorEvent) {
            errorMessage = "An unknown error has occurred: " + error.error.message;
        }
        else {
            errorMessage = "A HTTP error has occurred: " + ("HTTP " + error.status + ": " + error.error.message);
        }
        console.error(errorMessage);
        return Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["throwError"])(errorMessage);
    };
    ShoppingCartService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], ShoppingCartService);
    return ShoppingCartService;
}());



/***/ }),

/***/ "./src/app/services/wishlist.service.ts":
/*!**********************************************!*\
  !*** ./src/app/services/wishlist.service.ts ***!
  \**********************************************/
/*! exports provided: WishListService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WishListService", function() { return WishListService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");





var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({ 'Content-Type': 'application/json' })
};
var WishListService = /** @class */ (function () {
    function WishListService(httpClient) {
        this.httpClient = httpClient;
        this.baseUrl = "http://localhost:8080/ZhcqRetailSystem-war/Resources/Wishlist";
    }
    WishListService.prototype.addToWishList = function (userId, productId) {
        return this.httpClient.post(this.baseUrl + "/addToWishlist?userId=" + userId + "&productId=" + productId, null, httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    WishListService.prototype.retrieveWishList = function (userId) {
        return this.httpClient.get(this.baseUrl + '/retrieveWishList?userId=' + userId).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    WishListService.prototype.removeFromWishList = function (userId, productId) {
        return this.httpClient.delete(this.baseUrl + '?userId=' + userId + '&productId=' + productId).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["catchError"])(this.handleError));
    };
    WishListService.prototype.handleError = function (error) {
        var errorMessage = "";
        if (error.error instanceof ErrorEvent) {
            errorMessage = "An unknown error has occurred: " + error.error.message;
        }
        else {
            errorMessage = "A HTTP error has occurred: " + ("HTTP " + error.status + ": " + error.error.message);
        }
        console.error(errorMessage);
        return Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["throwError"])(errorMessage);
    };
    WishListService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], WishListService);
    return WishListService;
}());



/***/ })

}]);
//# sourceMappingURL=default~about-us-about-us-module~account-details-account-details-module~browse-products-browse-produ~8cef0aa8.js.map