(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["wishlist-wishlist-module"],{

/***/ "./src/app/wishlist/wishlist.module.ts":
/*!*********************************************!*\
  !*** ./src/app/wishlist/wishlist.module.ts ***!
  \*********************************************/
/*! exports provided: WishlistPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WishlistPageModule", function() { return WishlistPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _wishlist_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./wishlist.page */ "./src/app/wishlist/wishlist.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _wishlist_page__WEBPACK_IMPORTED_MODULE_6__["WishlistPage"]
    }
];
var WishlistPageModule = /** @class */ (function () {
    function WishlistPageModule() {
    }
    WishlistPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_wishlist_page__WEBPACK_IMPORTED_MODULE_6__["WishlistPage"]]
        })
    ], WishlistPageModule);
    return WishlistPageModule;
}());



/***/ }),

/***/ "./src/app/wishlist/wishlist.page.html":
/*!*********************************************!*\
  !*** ./src/app/wishlist/wishlist.page.html ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n  <br/>\n  <h2 text-center style=\"font-weight: bold\">W&nbsp;&nbsp;I&nbsp;&nbsp;S&nbsp;&nbsp;H&nbsp;&nbsp; L&nbsp;&nbsp;I&nbsp;&nbsp;S&nbsp;&nbsp;T</h2>\n  <br/>\n  <ion-grid *ngIf=exists> \n    <ion-row *ngFor=\"let product of products\"  [routerLink]=\"['/product-details',product.productId]\" class=\"activated\" >\n      <ion-col size=\"5\">\n        <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{product.picturePath}}\" class=\"img-responsive\"/>\n      </ion-col>\n      <ion-col size=\"7\">\n        <h6>{{product.productName}} &nbsp;&nbsp;${{product.unitPrice}}</h6>\n        <label>Size: {{product.sizeEnum}}</label> <br/>\n\n        \t\t<ion-button color=\"light\" (click)=\"removeProduct(product)\">Remove Item\n            </ion-button>\n        \n      </ion-col>\n    </ion-row>\n  </ion-grid>\n\n  <ion-card *ngIf=!exists>\n    <ion-card-content>\n      <ion-card-title style=\"text-align: center\" >oops</ion-card-title>\n      <ion-card-header style=\"text-align: center\"> Your WishList is Empty! :(</ion-card-header>\n    </ion-card-content>\n  </ion-card>\n</ion-content>\n    \n\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/wishlist/wishlist.page.scss":
/*!*********************************************!*\
  !*** ./src/app/wishlist/wishlist.page.scss ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3dpc2hsaXN0L3dpc2hsaXN0LnBhZ2Uuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/wishlist/wishlist.page.ts":
/*!*******************************************!*\
  !*** ./src/app/wishlist/wishlist.page.ts ***!
  \*******************************************/
/*! exports provided: WishlistPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "WishlistPage", function() { return WishlistPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_wishlist_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/wishlist.service */ "./src/app/services/wishlist.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");






var WishlistPage = /** @class */ (function () {
    function WishlistPage(storage, alertController, wishListService, router) {
        this.storage = storage;
        this.alertController = alertController;
        this.wishListService = wishListService;
        this.router = router;
    }
    WishlistPage.prototype.ngOnInit = function () {
    };
    WishlistPage.prototype.ionViewWillEnter = function () {
        var _this = this;
        this.storage.get('currentCustomer').then(function (data) {
            _this.member = data;
        });
        this.storage.get('isLogin').then(function (data) {
            _this.isLogin = data;
            _this.viewWishList();
        });
    };
    WishlistPage.prototype.viewWishList = function () {
        var _this = this;
        if (this.isLogin) {
            this.wishListService.retrieveWishList(this.member.memberId).subscribe(function (response) {
                _this.wishlist = response.wishlist;
                if (_this.wishlist.productEntities.length > 0) {
                    _this.exists = true;
                }
                _this.products = _this.wishlist.productEntities;
                console.log('LENGTH OF PRODUCTS = ' + _this.products.length);
            }, function (error) {
                _this.presentAlert(error);
            });
        }
        else {
            this.presentAlert('You are not logged in!');
            this.router.navigate(['/home']);
        }
    };
    WishlistPage.prototype.removeProduct = function (product) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            var _this = this;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'Confirm',
                            message: 'Remove Item? <ion-icon ios="ios-sad" md="md-sad"></ion-icon>',
                            buttons: [
                                {
                                    text: 'Cancel',
                                    role: 'cancel',
                                    cssClass: 'secondary',
                                    handler: function () {
                                        console.log('Cancelled remove Product');
                                    }
                                }, {
                                    text: 'Confirm',
                                    handler: function () {
                                        console.log("attempt to remove product");
                                        _this.wishListService.removeFromWishList(_this.member.memberId, product.productId).subscribe(function (response) {
                                            var index = _this.products.indexOf(product);
                                            if (index != -1) {
                                                _this.products.splice(index, 1);
                                                console.log("successfully removed product!");
                                            }
                                            _this.presentAlert("Successfully removed product!");
                                        }, function (error) {
                                            _this.presentAlert(error);
                                        });
                                    }
                                }
                            ]
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
    WishlistPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            message: message,
                            buttons: [{
                                    text: 'OK'
                                }]
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
    WishlistPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-wishlist',
            template: __webpack_require__(/*! ./wishlist.page.html */ "./src/app/wishlist/wishlist.page.html"),
            styles: [__webpack_require__(/*! ./wishlist.page.scss */ "./src/app/wishlist/wishlist.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_ionic_storage__WEBPACK_IMPORTED_MODULE_2__["Storage"], _ionic_angular__WEBPACK_IMPORTED_MODULE_3__["AlertController"], _services_wishlist_service__WEBPACK_IMPORTED_MODULE_4__["WishListService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"]])
    ], WishlistPage);
    return WishlistPage;
}());



/***/ })

}]);
//# sourceMappingURL=wishlist-wishlist-module.js.map