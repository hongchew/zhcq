(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["product-details-product-details-module"],{

/***/ "./src/app/entities/cart.ts":
/*!**********************************!*\
  !*** ./src/app/entities/cart.ts ***!
  \**********************************/
/*! exports provided: ShoppingCart */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ShoppingCart", function() { return ShoppingCart; });
var ShoppingCart = /** @class */ (function () {
    function ShoppingCart(cartId, member, products, quantity) {
        this.cartId = cartId;
        this.member = member;
        this.products = products;
        this.quantity = quantity;
    }
    return ShoppingCart;
}());



/***/ }),

/***/ "./src/app/entities/product.ts":
/*!*************************************!*\
  !*** ./src/app/entities/product.ts ***!
  \*************************************/
/*! exports provided: ProductEntity */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductEntity", function() { return ProductEntity; });
var ProductEntity = /** @class */ (function () {
    function ProductEntity(productId, productName, description, unitPrice, dateAdded, quantityOnHand, sizeEnum, colourEnum, picturePath) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.dateAdded = dateAdded;
        this.quantityOnHand = quantityOnHand;
        this.sizeEnum = sizeEnum;
        this.colourEnum = colourEnum;
        this.picturePath = picturePath;
    }
    return ProductEntity;
}());



/***/ }),

/***/ "./src/app/product-details/product-details.module.ts":
/*!***********************************************************!*\
  !*** ./src/app/product-details/product-details.module.ts ***!
  \***********************************************************/
/*! exports provided: ProductDetailsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductDetailsPageModule", function() { return ProductDetailsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _product_details_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./product-details.page */ "./src/app/product-details/product-details.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _product_details_page__WEBPACK_IMPORTED_MODULE_6__["ProductDetailsPage"]
    }
];
var ProductDetailsPageModule = /** @class */ (function () {
    function ProductDetailsPageModule() {
    }
    ProductDetailsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"],
            ],
            declarations: [_product_details_page__WEBPACK_IMPORTED_MODULE_6__["ProductDetailsPage"]]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], ProductDetailsPageModule);
    return ProductDetailsPageModule;
}());



/***/ }),

/***/ "./src/app/product-details/product-details.page.html":
/*!***********************************************************!*\
  !*** ./src/app/product-details/product-details.page.html ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n  <title> zhcq & co.</title>\n  <ion-toolbar>\n  <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n  </ion-buttons>\n  <ion-buttons slot=\"primary\">\n    <ion-button href=\"/browse-products\">\n      <ion-icon slot=\"icon-only\" name=\"search\"></ion-icon>\n    </ion-button>\n  </ion-buttons>\n\n  <ion-item href=\"/home\" detail=\"false\" lines=\"none\">\n    <ion-title style=\"text-align:center\"><b>   zhcq & co. </b></ion-title>\n  </ion-item>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content>\n  <div *ngIf=\"errorMessage\">\n    {{errorMessage}}\n  </div>\n\n  <!-- SELECTED PRODUCT -->\n  <div *ngIf=\"!errorMessage\">\n    <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{selectedProduct.picturePath}}\" class=\"img-responsive\"/>\n\n    <ion-list lines=\"none\" style=\"margin-bottom: 0\">\n      <ion-item>\n          <h4 slot=\"start\" style=\"margin-inline-end: 0%\">{{selectedProduct.productName}}</h4> \n          <ion-button type=\"submit\" size=\"small\" fill=\"transparent\" size=\"default\" (click)=\"addToWishList()\">\n              <ion-icon name=\"heart\"></ion-icon>\n          </ion-button>\n          <h4 *ngIf=\"!onPromotion\" slot=\"end\">${{selectedProduct.unitPrice}}</h4> \n          <h4 *ngIf=\"onPromotion\" slot=\"end\"><s>${{selectedProduct.unitPrice}}</s></h4> <br/>\n      </ion-item>\n     <ion-item *ngIf=\"onPromotion\" >\n        <h4 slot=\"end\" style=\"color: darkred; margin-top: 0%; margin-bottom: 0%\"><ion-icon ios=\"ios-pricetag\" md=\"md-pricetag\"></ion-icon>&nbsp; ${{promotionalPrice}}</h4>\n     </ion-item>\n     <h4 *ngIf=\"selectedProduct.quantityOnHand > 10\" class=\"inStock\" style=\"margin-top: 0px\"> \n        &nbsp;&nbsp;&nbsp;&nbsp;<i>In stock </i></h4>\n     <h4 *ngIf=\"selectedProduct.quantityOnHand <=10 && selectedProduct.quantityOnHand > 0\" style=\"color:rgba(233, 103, 27, 0.938); margin-top: 0px\">\n        &nbsp;&nbsp;&nbsp;&nbsp;<i>Running out</i> </h4>\n     <h4 *ngIf=\"selectedProduct.quantityOnHand == 0\" style=\"color: rgb(216, 15, 15); margin-top: 0px\"> \n        &nbsp;&nbsp;&nbsp;&nbsp;<i>Out of stock</i> </h4>\n\n     <ion-item>\n        <div style=\"line-height:25px\">\n          {{selectedProduct.description}}\n        </div>\n        \n     </ion-item>\n     <ion-item>\n        <h6>Size: {{selectedProduct.sizeEnum}}</h6>  \n      </ion-item>\n    </ion-list>\n  <!-- DIFF COLORS -->\n    \n    <ion-grid> \n      <ion-row>\n        &nbsp;&nbsp;\n        <ion-col size=\"1.5\" *ngFor=\"let product of diffColours\" [routerLink]=\"['/product-details',product.productId]\" class=\"activated\">\n          <img src=\"http://localhost:8080/ZhcqRetailSystem-war/images/{{product.colourEnum}}.jpg\" width=\"30\" height=\"30\"/>\n        </ion-col>\n      </ion-row>\n    </ion-grid>\n\n    <!-- DIFF SIZES -->\n    <ion-row>\n      <br/>\n        &nbsp;&nbsp;&nbsp;<h6>Select size</h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n        <!-- SIZE GUIDE DONT LAUGH AT THIS ^ PLS LOL SLOT=END NOT WORKING IDEK WHY-->\n        <ion-col >\n            &nbsp;\n          <ion-button color=\"light\" size=\"small\" (click)=\"openSizeGuide()\"> <ion-icon ios=\"ios-shirt\" md=\"md-shirt\"></ion-icon>&nbsp; Size Guide</ion-button>\n        </ion-col>\n        <br/>\n      \n      <!-- <div *ngIf=\"diffSizes.length == 0\">\n        &nbsp;&nbsp;&nbsp;<h6>No Other Available Sizes!</h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n       \n        <ion-col >\n            &nbsp;\n          <ion-button color=\"light\" size=\"small\" (click)=\"openSizeGuide()\"> <ion-icon ios=\"ios-shirt\" md=\"md-shirt\"></ion-icon>&nbsp; Size Guide</ion-button>\n        </ion-col>\n        <br>\n      </div> -->\n        \n    </ion-row>\n    <ion-row>\n      <div style=\"align-content: center\">\n          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"sizeXS\" [routerLink]=\"['/product-details',sizeXSId]\"> XS </ion-button> \n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"!sizeXS && !currentSizeXS\" disabled><s>XS</s></ion-button>\n        <ion-button color=\"dark\" fill=\"solid\" *ngIf=\"currentSizeXS\">XS</ion-button> &nbsp; \n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"sizeS\" [routerLink]=\"['/product-details',sizeSId]\"> S </ion-button> \n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"!sizeS && !currentSizeS\" disabled><s>S</s></ion-button> \n        <ion-button color=\"dark\" fill=\"solid\" *ngIf=\"currentSizeS\">S</ion-button> &nbsp;\n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"sizeM\" [routerLink]=\"['/product-details',sizeMId]\"> M </ion-button>\n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"!sizeM && !currentSizeM\" disabled><s>M</s></ion-button>\n        <ion-button color=\"dark\" fill=\"solid\" *ngIf=\"currentSizeM\">M</ion-button> &nbsp;\n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"sizeL\" [routerLink]=\"['/product-details',sizeLId]\"> L </ion-button>\n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"!sizeL && !currentSizeL\"disabled><s>L</s></ion-button> \n        <ion-button color=\"dark\" fill=\"solid\" *ngIf=\"currentSizeL\">L</ion-button>&nbsp;\n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"sizeXL\" [routerLink]=\"['/product-details',sizeXLId]\"> XL </ion-button> \n        <ion-button color=\"dark\" fill=\"outline\" *ngIf=\"!sizeXL && !currentSizeXL\" disabled><s>XL</s></ion-button> \n        <ion-button color=\"dark\" fill=\"solid\" *ngIf=\"currentSizeXL\">XL</ion-button>\n      </div>\n        &nbsp;&nbsp;&nbsp;\n      <!-- <div *ngFor=\"let pdt of diffSizes\" [routerLink]=\"['/product-details',pdt.productId]\" class=\"activated\">\n          <ion-button color=\"dark\" > {{pdt.sizeEnum}} </ion-button> &nbsp;&nbsp;\n      </div> -->\n    </ion-row>\n    <ion-card>\n      <ion-button fill=\"clear\" disabled color=\"dark\" strong>\n          <strong>Select Quantity: </strong>\n      </ion-button>\n      <ion-button fill=\"clear\" (click)=decrement() color=\"dark\">\n        <ion-icon name=\"remove\" ></ion-icon>\n      </ion-button>\n      <ion-button fill=\"outline\" disabled color=\"dark\"> \n          <strong>{{ quantity }}</strong>\n     </ion-button> \n      <ion-button fill=\"clear\" (click) = increment() color=\"dark\">\n        <ion-icon name=\"add\" ></ion-icon>\n      </ion-button>\n    </ion-card>\n    \n    <ion-button type=\"submit\" color=\"dark\" expand=\"full\" (click)=\"addToCart()\">\n        <ion-icon name=\"ios-cart\"></ion-icon> &nbsp;&nbsp; add to bag\n    </ion-button>\n    \n    \n    \n    <h6 text-center>other cute stuff you'll love <ion-icon ios=\"ios-arrow-round-down\" md=\"md-arrow-round-down\"></ion-icon></h6>\n    <ion-card>\n      <ion-slides [options]=\"sliderOpts\">\n          <ion-slide *ngFor=\"let pdt of suggestedProducts\" [routerLink]=\"['/product-details',pdt.productId]\" >\n              <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{pdt.picturePath}}\" class=\"thumb-img\"/>\n          </ion-slide>\n        </ion-slides>\n    </ion-card>\n    \n      \n  </div>\n  \n  \n\n</ion-content>\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/product-details/product-details.page.scss":
/*!***********************************************************!*\
  !*** ./src/app/product-details/product-details.page.scss ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".thumb-img {\n  height: 300px;\n  width: 220px; }\n\n.inStock {\n  color: cadetblue; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvcHJvZHVjdC1kZXRhaWxzL3Byb2R1Y3QtZGV0YWlscy5wYWdlLnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQ0E7RUFFSSxhQUFhO0VBQ2IsWUFBWSxFQUFBOztBQUdoQjtFQUVJLGdCQUFnQixFQUFBIiwiZmlsZSI6InNyYy9hcHAvcHJvZHVjdC1kZXRhaWxzL3Byb2R1Y3QtZGV0YWlscy5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyJcbi50aHVtYi1pbWd7XG4gICAgLy8gcGFkZGluZzogNXB4O1xuICAgIGhlaWdodDogMzAwcHg7XG4gICAgd2lkdGg6IDIyMHB4O1xufVxuXG4uaW5TdG9ja3tcbiAgICBcbiAgICBjb2xvcjogY2FkZXRibHVlOyBcbn1cbiJdfQ== */"

/***/ }),

/***/ "./src/app/product-details/product-details.page.ts":
/*!*********************************************************!*\
  !*** ./src/app/product-details/product-details.page.ts ***!
  \*********************************************************/
/*! exports provided: ProductDetailsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductDetailsPage", function() { return ProductDetailsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_product_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/product.service */ "./src/app/services/product.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _entities_product__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../entities/product */ "./src/app/entities/product.ts");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");
/* harmony import */ var _entities_cart__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../entities/cart */ "./src/app/entities/cart.ts");
/* harmony import */ var _services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../services/shoppingcart.service */ "./src/app/services/shoppingcart.service.ts");
/* harmony import */ var _services_wishlist_service__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../services/wishlist.service */ "./src/app/services/wishlist.service.ts");
/* harmony import */ var _sizeguide_sizeguide_page__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../sizeguide/sizeguide.page */ "./src/app/sizeguide/sizeguide.page.ts");











var ProductDetailsPage = /** @class */ (function () {
    function ProductDetailsPage(productService, modalController, activatedRoute, alertController, storage, shoppingCartService, wishListService, router) {
        var _this = this;
        this.productService = productService;
        this.modalController = modalController;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.storage = storage;
        this.shoppingCartService = shoppingCartService;
        this.wishListService = wishListService;
        this.router = router;
        this.errorMessage = '';
        this.cart = new _entities_cart__WEBPACK_IMPORTED_MODULE_7__["ShoppingCart"]();
        this.sliderOpts = {
            zoom: false,
            slidesPerView: 1.5,
            spaceBetween: true
        };
        this.onPromotion = false;
        console.log("Promotion Status: " + this.onPromotion);
        this.selectedProduct = new _entities_product__WEBPACK_IMPORTED_MODULE_5__["ProductEntity"]();
        storage.get('currentCustomer').then(function (data) {
            _this.member = data;
            if (_this.member != null && _this.member !== undefined) {
                _this.shoppingCartService.retrieveShoppingCart(_this.member.memberId).subscribe(function (response) {
                    _this.cart = response.userShoppingCart;
                    if (_this.cart !== null) {
                        _this.cartId = _this.cart.cartId;
                        console.log('cartId = ' + _this.cartId);
                    }
                });
            }
        });
        storage.get('isLogin').then(function (data) {
            _this.isLogin = data;
            console.log('lOGIN Status: ' + _this.isLogin);
            console.log('Member: ' + _this.member);
            // if (this.isLogin) {
            //   this.cartId = this.member.shoppingCart.cartId;
            //   console.log("cartID = " + this.cartId);
            // }
        });
        this.quantity = 1;
    }
    ProductDetailsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.id = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
        console.log('Selected productid = ' + this.id);
        if (!isNaN(this.id)) {
            console.log('Entered method 1');
            this.productService.retrieveProductById(this.id).subscribe(function (response) {
                console.log('Entered method 2');
                console.log('response = ' + response);
                _this.selectedProduct = response.selectedProduct;
                _this.selectedCategoryName = _this.selectedProduct.productCategory.categoryName;
                _this.diffColours = response.diffColours;
                _this.diffSizes = response.diffSizes;
                _this.suggestedProducts = response.suggestedProducts;
                if (_this.selectedProduct.sizeEnum.toString() === 'XS') {
                    _this.currentSizeXS = true;
                    _this.sizeXSId = _this.selectedProduct.productId;
                }
                if (_this.selectedProduct.sizeEnum.toString() === 'S') {
                    _this.currentSizeS = true;
                    _this.sizeSId = _this.selectedProduct.productId;
                }
                if (_this.selectedProduct.sizeEnum.toString() === 'M') {
                    _this.currentSizeM = true;
                    _this.sizeMId = _this.selectedProduct.productId;
                }
                if (_this.selectedProduct.sizeEnum.toString() === 'L') {
                    _this.currentSizeL = true;
                    _this.sizeLId = _this.selectedProduct.productId;
                }
                if (_this.selectedProduct.sizeEnum.toString() === 'XL') {
                    _this.currentSizeXL = true;
                    _this.sizeXLId = _this.selectedProduct.productId;
                }
                // Check for promotion
                if (_this.selectedProduct.promotion != null) {
                    _this.onPromotion = true;
                    _this.promotionalPrice = ((1 - _this.selectedProduct.promotion.discountRate) * _this.selectedProduct.unitPrice);
                    console.log('Product Price is: ' + _this.selectedProduct.unitPrice);
                    console.log('Promotional rate is: ' + _this.selectedProduct.promotion.discountRate);
                    console.log('Calculated Promotional Price is: ' + _this.promotionalPrice);
                }
                else {
                    _this.onPromotion = false;
                }
                if (_this.diffSizes.length !== 0) {
                    for (var _i = 0, _a = _this.diffSizes; _i < _a.length; _i++) {
                        var product = _a[_i];
                        if (product.sizeEnum.toString() === 'XS') {
                            _this.sizeXS = true;
                            _this.sizeXSId = product.productId;
                        }
                        if (product.sizeEnum.toString() === 'S') {
                            _this.sizeS = true;
                            _this.sizeSId = product.productId;
                        }
                        if (product.sizeEnum.toString() === 'M') {
                            _this.sizeM = true;
                            _this.sizeMId = product.productId;
                        }
                        if (product.sizeEnum.toString() === 'L') {
                            _this.sizeL = true;
                            _this.sizeLId = product.productId;
                        }
                        if (product.sizeEnum.toString() === 'XL') {
                            _this.sizeXL = true;
                            _this.sizeXLId = product.productId;
                        }
                    }
                }
            }, function (error) {
                _this.presentAlert(error.substring(37));
            });
        }
        else {
            console.log('Entered method 3');
            this.errorMessage = 'Product Not Found';
            this.presentAlert(this.errorMessage);
            this.router.navigate(['categories']);
        }
    };
    ProductDetailsPage.prototype.addToWishList = function () {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var listSuccess;
            var _this = this;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'added to wish list!'
                        })];
                    case 1:
                        listSuccess = _a.sent();
                        if (this.isLogin) {
                            console.log('Entered into add to wishlist method');
                            this.wishListService.addToWishList(this.member.memberId, this.id).subscribe(function (response) {
                                console.log('response = ' + response);
                                listSuccess.present();
                            }, function (error) {
                                _this.presentAlert(error.substring(37));
                                // this.ngOnInit();
                            });
                        }
                        else {
                            this.presentAlert('Please Login!');
                        }
                        return [2 /*return*/];
                }
            });
        });
    };
    ProductDetailsPage.prototype.addToCart = function () {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var cartAlert;
            var _this = this;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'Added to Bag!',
                            buttons: [
                                {
                                    text: 'View Cart',
                                    cssClass: 'secondary',
                                    handler: function () {
                                        _this.router.navigate(['shoppingcart']);
                                    }
                                }, {
                                    text: 'Continue Shopping',
                                    cssClass: 'secondary',
                                    handler: function () {
                                    }
                                }
                            ]
                        })];
                    case 1:
                        cartAlert = _a.sent();
                        if (this.isLogin) {
                            if (this.quantity <= this.selectedProduct.quantityOnHand) {
                                if (this.quantity === 0) {
                                    this.presentAlert('Please Enter A Quantity!');
                                }
                                else {
                                    this.shoppingCartService.addToCart(this.cartId, this.id, this.quantity).subscribe(function (response) {
                                        console.log('response = ' + response);
                                        cartAlert.present();
                                    }, function (error) {
                                        _this.presentAlert('ERROR FROM ADDING TO CART: ' + error.substring(37));
                                        // this.ngOnInit();
                                    });
                                }
                            }
                            else {
                                this.presentAlert('OUT OF STOCK!');
                            }
                        }
                        else {
                            this.presentAlert('Please Login!');
                        }
                        return [2 /*return*/];
                }
            });
        });
    };
    ProductDetailsPage.prototype.increment = function () {
        if (this.quantity === this.selectedProduct.quantityOnHand) {
            this.presentAlert('Oops! No Available Pieces Left, that\'s the last one!');
        }
        else {
            this.quantity++;
        }
    };
    ProductDetailsPage.prototype.decrement = function (product) {
        if (this.quantity === 0) {
            this.presentAlert('Quantity cannot be < 0');
        }
        else {
            this.quantity--;
        }
    };
    ProductDetailsPage.prototype.presentAlert = function (message) {
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
    ProductDetailsPage.prototype.openSizeGuide = function () {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var modal;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.modalController.create({
                            component: _sizeguide_sizeguide_page__WEBPACK_IMPORTED_MODULE_10__["SizeguidePage"],
                            componentProps: { value: this.selectedCategoryName }
                        })];
                    case 1:
                        modal = _a.sent();
                        modal.present();
                        return [2 /*return*/];
                }
            });
        });
    };
    ProductDetailsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-product-details',
            template: __webpack_require__(/*! ./product-details.page.html */ "./src/app/product-details/product-details.page.html"),
            styles: [__webpack_require__(/*! ./product-details.page.scss */ "./src/app/product-details/product-details.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_product_service__WEBPACK_IMPORTED_MODULE_3__["ProductService"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_2__["ModalController"],
            _angular_router__WEBPACK_IMPORTED_MODULE_4__["ActivatedRoute"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_2__["AlertController"],
            _ionic_storage__WEBPACK_IMPORTED_MODULE_6__["Storage"],
            _services_shoppingcart_service__WEBPACK_IMPORTED_MODULE_8__["ShoppingCartService"],
            _services_wishlist_service__WEBPACK_IMPORTED_MODULE_9__["WishListService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"]])
    ], ProductDetailsPage);
    return ProductDetailsPage;
}());



/***/ })

}]);
//# sourceMappingURL=product-details-product-details-module.js.map