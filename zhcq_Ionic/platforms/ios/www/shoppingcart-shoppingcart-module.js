(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["shoppingcart-shoppingcart-module"],{

/***/ "./src/app/shoppingcart/shoppingcart.module.ts":
/*!*****************************************************!*\
  !*** ./src/app/shoppingcart/shoppingcart.module.ts ***!
  \*****************************************************/
/*! exports provided: ShoppingcartPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ShoppingcartPageModule", function() { return ShoppingcartPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _shoppingcart_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./shoppingcart.page */ "./src/app/shoppingcart/shoppingcart.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _shoppingcart_page__WEBPACK_IMPORTED_MODULE_6__["ShoppingcartPage"]
    }
];
var ShoppingcartPageModule = /** @class */ (function () {
    function ShoppingcartPageModule() {
    }
    ShoppingcartPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_shoppingcart_page__WEBPACK_IMPORTED_MODULE_6__["ShoppingcartPage"]]
        })
    ], ShoppingcartPageModule);
    return ShoppingcartPageModule;
}());



/***/ }),

/***/ "./src/app/shoppingcart/shoppingcart.page.html":
/*!*****************************************************!*\
  !*** ./src/app/shoppingcart/shoppingcart.page.html ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n    <title> zhcq & co.</title>\n    <ion-toolbar>\n    <ion-buttons slot=\"start\">\n        <ion-back-button *ngIf='latestPdtId' defaultHref = \"product-details/{{ latestPdtId }}\"></ion-back-button>\n        <ion-back-button *ngIf='!latestPdtId' defaultHref = \"categories\"></ion-back-button>\n    </ion-buttons>\n    <ion-buttons slot=\"primary\">\n      <ion-button href=\"/browse-products\">\n        <ion-icon slot=\"icon-only\" name=\"search\"></ion-icon>\n      </ion-button>\n    </ion-buttons>\n  \n    <ion-title style=\"text-align:center\" >zhcq & co. </ion-title>\n    </ion-toolbar>\n  </ion-header>\n\n<ion-content>\n  <br/>\n  <h2 text-center style=\"font-weight: bold\">S&nbsp;&nbsp;H&nbsp;&nbsp;O&nbsp;&nbsp;P&nbsp;&nbsp;P&nbsp;&nbsp;I&nbsp;&nbsp;N&nbsp;&nbsp;G &nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;A&nbsp;&nbsp;G</h2>\n  <br/>\n  <ion-card *ngIf=\"products.length==0\">\n      <ion-card-content>\n        <ion-card-title style=\"text-align: center\" >oops</ion-card-title>\n        <ion-card-header style=\"text-align: center\"> Your Shopping Bag is Empty! :(</ion-card-header>\n      </ion-card-content>\n    </ion-card>\n  <ion-grid> \n    <ion-row *ngFor=\"let product of products\" class=\"activated\">\n      <ion-col size=\"5\">\n        <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{product.picturePath}}\" class=\"img-responsive\"/>\n      </ion-col>\n      <ion-col size=\"7\">\n        <h5><b> {{ product.productName }} </b></h5>\n        <label>Size: {{ product.sizeEnum }}</label> <br>\n        <!-- <label>Quantity: </label> \n        \n        <ion-icon name=\"remove\" (click)=decrement(product)></ion-icon> \n     {{quantity[products.indexOf(product)]}}\n        <ion-icon name=\"add\" (click) = increment(product)></ion-icon> -->\n\n          <ion-button fill=\"clear\" color=\"dark\" strong >\n              <strong> Qty: </strong>\n          </ion-button>\n          <ion-button fill=\"clear\" (click)=decrement(product) color=\"dark\" size=\"small\">\n            <ion-icon name=\"remove\" ></ion-icon>\n          </ion-button>\n          <ion-button fill=\"outline\" color=\"dark\" size=\"small\" strong>\n              <strong>{{ quantity[products.indexOf(product)] }}</strong>\n          </ion-button>\n          <ion-button fill=\"clear\" (click) = increment(product) color=\"dark\" size=\"small\">\n            <ion-icon name=\"add\" ></ion-icon>\n          </ion-button>\n         \n        <!--<ion-input style=\"border: 1px;\" [(ngModel)]=\"quantity[products.indexOf(product)]\" required></ion-input> -->\n        <br/>\n        <label *ngIf=\"product.promotion == undefined\"><b>Subtotal: </b> SGD {{subtotal[products.indexOf(product)]}}</label> \n        <!-- <label *ngIf=\"product.promotion != undefined\">\n        <s>Subtotal: SGD {{subtotal[products.indexOf(product)]}}</s>\n        </label>  -->\n        \n        <label *ngIf=\"product.promotion != undefined\"><b>Subtotal: </b>\n         SGD <s>{{ subtotal[products.indexOf(product)] }}</s>&nbsp;<strong style=\"color: crimson\">{{ subtotal[products.indexOf(product)]*(1-product.promotion.discountRate) }}</strong><ion-icon name=\"pricetags\" style=\"color: crimson\"></ion-icon>\n        </label> \n        <br><br>\n        <ion-button (click)=\"removeProduct(product)\"  fill=\"outline\" color=\"dark\"  size=\"small\" >Remove Item\n        </ion-button>\n      </ion-col>\n    </ion-row>\n  </ion-grid>\n  <ion-button color=\"dark\" expand=\"full\" (click)=\"updateCart()\" >Update Bag</ion-button>\n  \n  <ion-button color=\"dark\" expand=\"full\" (click)=\"checkoutInitial()\">Check Out</ion-button>\n  <!-- <ion-button color=\"dark\" expand=\"full\" (click)=\"checkout()\">c h e c k &nbsp; o u t</ion-button> -->\n  <!-- <ion-button color=\"dark\" expand=\"full\" (click)=\"checkoutWithPoints()\">c h e c k &nbsp; o u t &nbsp; w i t h &nbsp; P o i n t s</ion-button> -->\n</ion-content>\n    \n\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/shoppingcart/shoppingcart.page.scss":
/*!*****************************************************!*\
  !*** ./src/app/shoppingcart/shoppingcart.page.scss ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3Nob3BwaW5nY2FydC9zaG9wcGluZ2NhcnQucGFnZS5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/shoppingcart/shoppingcart.page.ts":
/*!***************************************************!*\
  !*** ./src/app/shoppingcart/shoppingcart.page.ts ***!
  \***************************************************/
/*! exports provided: ShoppingcartPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ShoppingcartPage", function() { return ShoppingcartPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/shoppingcart.service */ "./src/app/services/shoppingcart.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_member_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../services/member.service */ "./src/app/services/member.service.ts");







var ShoppingcartPage = /** @class */ (function () {
    function ShoppingcartPage(storage, alertController, shoppingCartService, router, memberService) {
        this.storage = storage;
        this.alertController = alertController;
        this.shoppingCartService = shoppingCartService;
        this.router = router;
        this.memberService = memberService;
        this.errorMessage = '';
        this.products = new Array();
    }
    ShoppingcartPage.prototype.ngOnInit = function () {
        this.subtotal = [];
    };
    ShoppingcartPage.prototype.ionViewWillEnter = function () {
        var _this = this;
        this.storage.get('currentCustomer').then(function (data) {
            _this.member = data;
        });
        this.storage.get('isLogin').then(function (data) {
            _this.isLogin = data;
            console.log('lOGIN Status: ' + _this.isLogin);
            console.log('Member: ' + _this.member);
            _this.initialiseCart();
        });
    };
    ShoppingcartPage.prototype.initialiseCart = function () {
        var _this = this;
        if (this.isLogin) {
            this.shoppingCartService.retrieveShoppingCart(this.member.memberId).subscribe(function (response) {
                _this.cart = response.userShoppingCart;
                _this.products = _this.cart.products;
                _this.quantity = _this.cart.quantity;
                for (var i = 0; i < _this.products.length; i++) {
                    _this.subtotal.push(_this.products[i].unitPrice * _this.quantity[i]);
                    console.log('initialized product ' + _this.products[i].productName);
                    console.log('initialized quantity ' + _this.quantity[i]);
                    console.log('initialized subtotal ' + _this.subtotal[i]);
                }
                _this.latestPdtId = _this.products[_this.products.length - 1].productId;
                console.log("Latest Product ID = " + _this.latestPdtId);
            }, function (error) {
                _this.presentAlert(error);
                _this.router.navigate(['/home']);
            });
        }
        else {
            this.presentAlert('You are not logged in!');
            this.router.navigate(['/home']);
        }
    };
    ShoppingcartPage.prototype.increment = function (product) {
        var idx = this.products.indexOf(product);
        this.quantity[idx]++;
        this.subtotal[idx] = this.quantity[idx] * this.products[idx].unitPrice;
    };
    ShoppingcartPage.prototype.decrement = function (product) {
        var idx = this.products.indexOf(product);
        if (this.quantity[idx] === 0) {
            this.presentAlert('Quantity cannot be < 0');
        }
        else {
            this.quantity[idx]--;
            this.subtotal[idx] = this.quantity[idx] * this.products[idx].unitPrice;
        }
    };
    ShoppingcartPage.prototype.updateCart = function () {
        var _this = this;
        for (var i = 0; i < this.quantity.length; i++) {
            // if(this.quantity[i] <= 0) {
            //   this.quantity[i] = 0;
            //   this.presentAlert("Quantity of " + this.products[i].productName + " must be >= 0");
            // }
            this.shoppingCartService.updateCart(this.cart.cartId, this.products[i].productId, this.quantity[i]).subscribe(function (response) {
                console.log('Successfully updated cart!');
                _this.presentAlert('Successfully updated cart!');
            }, function (error) {
                _this.errorMessage = error;
                _this.presentAlert(_this.errorMessage.substring(37));
            });
        }
    };
    // checkout() {
    //   this.shoppingCartService.checkout(this.cart.cartId).subscribe(
    //     response => {
    //       this.transaction = response.txn;
    //       console.log('transaction ID =' + this.transaction.saleTransactionId);
    //       this.presentAlert('Successfully checked out! Sale transaction Id: ' + this.transaction.saleTransactionId);
    //       this.router.navigate(['/home']);
    //     },
    //     error => {
    //       this.errorMessage = error;
    //       this.presentAlert(this.errorMessage.substring(37));
    //     }
    //   );
    // }
    ShoppingcartPage.prototype.checkoutInitial = function () {
        var _this = this;
        this.memberService.retrieveMember(this.member.memberId).subscribe(function (response) {
            _this.member = response.member;
            console.log('Current member id = ' + _this.member.memberId);
            console.log('Current member points = ' + _this.member.loyaltyPoints);
            _this.checkout();
        }, function (error) {
            _this.errorMessage = error;
            _this.presentAlert(_this.errorMessage.substring(37) + ' cannot get member');
        });
    };
    ShoppingcartPage.prototype.checkout = function () {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert_1, alert_2;
            var _this = this;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0:
                        if (!(this.member.loyaltyPoints > 0)) return [3 /*break*/, 3];
                        return [4 /*yield*/, this.alertController.create({
                                header: 'C H E C K ' + ' O U T',
                                subHeader: 'You have ' + this.member.loyaltyPoints + ' points!',
                                message: 'You can redeem $' + (this.member.loyaltyPoints / 10) + ' for your purchase.',
                                buttons: [
                                    {
                                        text: 'Don\'t Redeem',
                                        cssClass: 'secondary',
                                        handler: function () {
                                            _this.shoppingCartService.checkout(_this.cart.cartId).subscribe(function (response) {
                                                _this.transaction = response.txn;
                                                console.log('transaction ID =' + _this.transaction.saleTransactionId);
                                                _this.presentAlert('Successfully checked out! Sale transaction Id: ' + _this.transaction.saleTransactionId);
                                                _this.router.navigate(['/account-details']);
                                            }, function (error) {
                                                _this.errorMessage = error;
                                                _this.presentAlert(_this.errorMessage.substring(37));
                                            });
                                        }
                                    }, {
                                        text: 'Redeem!',
                                        handler: function () {
                                            _this.checkoutWithPoints();
                                        }
                                    }
                                ]
                            })];
                    case 1:
                        alert_1 = _a.sent();
                        return [4 /*yield*/, alert_1.present()];
                    case 2:
                        _a.sent();
                        return [3 /*break*/, 6];
                    case 3: return [4 /*yield*/, this.alertController.create({
                            header: 'Confirm purchase?',
                            buttons: [
                                {
                                    text: 'Yes',
                                    cssClass: 'secondary',
                                    handler: function () {
                                        _this.shoppingCartService.checkout(_this.cart.cartId).subscribe(function (response) {
                                            _this.transaction = response.txn;
                                            console.log('transaction ID =' + _this.transaction.saleTransactionId);
                                            _this.presentAlert('Successfully checked out! Sale transaction Id: ' + _this.transaction.saleTransactionId);
                                            _this.router.navigate(['/account-details']);
                                        }, function (error) {
                                            _this.errorMessage = error;
                                            _this.presentAlert(_this.errorMessage.substring(37));
                                        });
                                    }
                                }, 'Nope'
                            ]
                        })];
                    case 4:
                        alert_2 = _a.sent();
                        return [4 /*yield*/, alert_2.present()];
                    case 5:
                        _a.sent();
                        _a.label = 6;
                    case 6: return [2 /*return*/];
                }
            });
        });
    };
    ShoppingcartPage.prototype.checkoutWithPoints = function () {
        var _this = this;
        this.shoppingCartService.checkoutWithPoints(this.cart.cartId).subscribe(function (response) {
            _this.transaction = response.txn;
            console.log('transaction ID =' + _this.transaction.saleTransactionId);
            _this.presentAlert('Successfully checked out! Sale transaction Id: ' + _this.transaction.saleTransactionId);
            _this.router.navigate(['/account-details']);
        }, function (error) {
            _this.errorMessage = error;
            _this.presentAlert(_this.errorMessage.substring(37));
        });
    };
    // removeProduct(product: ProductEntity) {
    //   console.log("start");
    //   this.presentAlertConfirm(product);
    // }
    ShoppingcartPage.prototype.removeProduct = function (product) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            var _this = this;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'Confirm!',
                            message: 'Remove Item? <ion-icon ios="ios-sad" md="md-sad"></ion-icon>',
                            buttons: [
                                {
                                    text: 'Cancel',
                                    role: 'cancel',
                                    cssClass: 'secondary',
                                    handler: function () {
                                        console.log('Cancelled remove product');
                                    }
                                }, {
                                    text: 'Confirm',
                                    handler: function () {
                                        console.log('attempt to remove product');
                                        _this.shoppingCartService.removeFromCart(_this.cart.cartId, product.productId).subscribe(function (response) {
                                            var index = _this.products.indexOf(product);
                                            if (index != -1) {
                                                _this.products.splice(index, 1);
                                                _this.quantity.splice(index, 1);
                                                _this.subtotal.splice(index, 1);
                                                console.log('successfully removed product!');
                                            }
                                            _this.router.navigate(['/product-details', product.productId]);
                                            _this.presentAlert('Item removed from bag');
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
    // const alert = await this.alertController.create({
    //   header: 'Confirm',
    //   message: 'Remove Item? <ion-icon ios="ios-sad" md="md-sad"></ion-icon>',
    //   buttons: [
    //     {
    //       text: 'Cancel',
    //       role: 'cancel',
    //       cssClass: 'secondary',
    //       handler: () => {
    //         console.log('Cancelled remove Product');
    //       }
    //     }, {
    //       text: 'Confirm',
    //       handler: () => {
    //         console.log('attempt to remove product');
    //         this.shoppingCartService.removeFromCart(this.cart.cartId, product.productId).subscribe(
    //           response => {
    //            const index:number = this.products.indexOf(product);
    //             if(index != -1) {
    //               this.products.splice(index,1);
    //               this.subtotal.splice(index,1);
    //               console.log('successfully removed product!');
    //             }
    //             this.presentAlert('Item removed from bag');
    //           },
    //           error => {
    //             this.presentAlert(error);
    //           }
    //         );
    //       }
    //     }
    //   ]
    // });
    //   }
    ShoppingcartPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: message,
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
    ShoppingcartPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-shoppingcart',
            template: __webpack_require__(/*! ./shoppingcart.page.html */ "./src/app/shoppingcart/shoppingcart.page.html"),
            styles: [__webpack_require__(/*! ./shoppingcart.page.scss */ "./src/app/shoppingcart/shoppingcart.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_ionic_storage__WEBPACK_IMPORTED_MODULE_2__["Storage"], _ionic_angular__WEBPACK_IMPORTED_MODULE_3__["AlertController"], _services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_4__["ShoppingCartService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"], _services_member_service__WEBPACK_IMPORTED_MODULE_6__["MemberService"]])
    ], ShoppingcartPage);
    return ShoppingcartPage;
}());



/***/ })

}]);
//# sourceMappingURL=shoppingcart-shoppingcart-module.js.map