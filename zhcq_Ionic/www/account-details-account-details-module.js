(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["account-details-account-details-module"],{

/***/ "./src/app/account-details/account-details.module.ts":
/*!***********************************************************!*\
  !*** ./src/app/account-details/account-details.module.ts ***!
  \***********************************************************/
/*! exports provided: AccountDetailsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountDetailsPageModule", function() { return AccountDetailsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _account_details_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./account-details.page */ "./src/app/account-details/account-details.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _account_details_page__WEBPACK_IMPORTED_MODULE_6__["AccountDetailsPage"]
    }
];
var AccountDetailsPageModule = /** @class */ (function () {
    function AccountDetailsPageModule() {
    }
    AccountDetailsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_account_details_page__WEBPACK_IMPORTED_MODULE_6__["AccountDetailsPage"]]
        })
    ], AccountDetailsPageModule);
    return AccountDetailsPageModule;
}());



/***/ }),

/***/ "./src/app/account-details/account-details.page.html":
/*!***********************************************************!*\
  !*** ./src/app/account-details/account-details.page.html ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content padding class=\"background\" >\n  <div *ngIf=isLogin  >\n\n      <br/>\n      <br/>\n      <br/>\n      <br/>\n      <br/>\n      <br/>\n      <br/>\n      <br/>\n      <br/>\n      \n    <ion-card>\n      <ion-card-content>\n        \n          <ion-card-title>{{ member.firstName }} {{ member.lastName }}</ion-card-title>\n          <ion-card-subtitle>\n            <br/>\n            Username: {{ member.username}}<br/>\n            Points: {{ member.loyaltyPoints }}<br/>\n            Email: {{ member.email }}<br/>\n           </ion-card-subtitle>\n      </ion-card-content>\n      <!-- <img src =\"../../assets/white-sheets.jpg\"/> -->\n      <!-- <div class=\"card-title\">{{ member.firstName }} {{ member.lastName }}</div>\n      <div class=\"card-subtitle\">Username: {{ member.username}}<br/>Points: {{ member.loyaltyPoints }} </div> -->\n      \n    </ion-card>\n    \n      \n      <!-- <div *ngIf=\"transactions\"> -->\n        <h3><b>Transaction History</b></h3>\n        <!-- <ion-row *ngFor=\"let txn of transactions\" class=\"activated\"> -->\n          <!-- <ion-col> -->\n            <div *ngIf=\"transactions.length==0\" style=\"text-align: center\">\n              You have no transactions. (<i>yet</i>)\n            </div>\n            <ion-card *ngFor=\"let txn of transactions\">\n              <ion-card-content>\n                <ion-card-title>Transaction ID: {{ txn.saleTransactionId }} </ion-card-title>\n                <h2>Total : SGD {{ txn.totalPrice }}</h2><br>\n                <ion-card-subtitle>\n                  <ion-grid>\n                    <ion-row>\n                      <ion-col size=\"8\">\n                        <div>\n                          <b>Item</b>\n                        </div>\n                      </ion-col>\n                      <ion-col>\n                        <div>\n                          <b>Subtotal</b>\n                        </div>\n                      </ion-col>\n                    </ion-row>\n                    <div *ngFor=\"let item of txn.saleTransactionLineItems\">\n                      <ion-row>\n                        <ion-col size=\"8\">\n                          <div>\n                            {{ item.quantity}} {{ item.productEntity.productName}} ({{item.productEntity.colourEnum}})\n                          </div>\n                        </ion-col>\n                        <ion-col style=\"text-align: center\">\n                          <div *ngIf=\"(item.subTotal/item.quantity)<item.productEntity.unitPrice; else nopromo\">\n                            <s>${{ item.productEntity.unitPrice*item.quantity}}</s>&nbsp;<ion-icon name=\"pricetags\"></ion-icon> ${{item.subTotal}} \n                          </div>\n                          <div>\n                            <ng-template #nopromo>${{item.subTotal}} </ng-template>\n                          </div>\n                        \n                        </ion-col>\n                      </ion-row>\n                    </div>\n                    </ion-grid>\n                    \n                  </ion-card-subtitle>\n              </ion-card-content>\n            </ion-card>\n          <!-- </ion-col> -->\n        <!-- </ion-row> -->\n      <!-- </div> -->\n      <br/><br/><br/>\n     <ion-grid>\n      <ion-row>\n        <ion-col size=\"12\">\n            <ion-button color=\"dark\" expand=\"block\" (click)= \"logout()\">\n              Log Out \n            </ion-button>\n          </ion-col>\n        </ion-row>\n      </ion-grid>\n  </div>\n  \n  \n  <div *ngIf=!isLogin style=\"text-align: center\" >\n    <br/>\n    <br/>\n    <br/>\n    <br/>\n    <br/>\n    <br/>\n    <br/>\n    <br/>\n    <br/>\n    <h3><i><b>hi there.</b></i></h3>\n    <br/>\n    <h5>Welcome back!</h5>\n    <!-- <div class=buttons> -->\n        <ion-button color=\"light\" [routerLink]=\"['/login']\" expand=\"full\">\n          Log In\n        </ion-button>\n    <!-- </div> <br> -->\n    <h5>If you're new, join us here <ion-icon ios=\"ios-arrow-round-down\" md=\"md-arrow-round-down\"></ion-icon></h5>\n      <ion-button color=\"light\" [routerLink]=\"['/register']\" expand=\"full\">\n        Sign Up\n      </ion-button>\n    \n  </div>\n</ion-content>\n\n\n\n\n\n<app-footer></app-footer>"

/***/ }),

/***/ "./src/app/account-details/account-details.page.scss":
/*!***********************************************************!*\
  !*** ./src/app/account-details/account-details.page.scss ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "ion-content.background {\n  --background: url('account.jpg')  no-repeat center center / cover; }\n\n.buttons {\n  width: 70px;\n  height: 10px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvYWNjb3VudC1kZXRhaWxzL2FjY291bnQtZGV0YWlscy5wYWdlLnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxpRUFBYSxFQUFBOztBQUdqQjtFQUNJLFdBQVc7RUFDWCxZQUFZLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9hY2NvdW50LWRldGFpbHMvYWNjb3VudC1kZXRhaWxzLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbImlvbi1jb250ZW50LmJhY2tncm91bmQge1xuICAgIC0tYmFja2dyb3VuZDogdXJsKFwiLi4vLi4vYXNzZXRzL2FjY291bnQuanBnXCIpICBuby1yZXBlYXQgY2VudGVyIGNlbnRlciAvIGNvdmVyO1xufVxuXG4uYnV0dG9uc3tcbiAgICB3aWR0aDogNzBweDtcbiAgICBoZWlnaHQ6IDEwcHg7XG59XG5cbiJdfQ== */"

/***/ }),

/***/ "./src/app/account-details/account-details.page.ts":
/*!*********************************************************!*\
  !*** ./src/app/account-details/account-details.page.ts ***!
  \*********************************************************/
/*! exports provided: AccountDetailsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountDetailsPage", function() { return AccountDetailsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _entities_member__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../entities/member */ "./src/app/entities/member.ts");
/* harmony import */ var _services_member_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/member.service */ "./src/app/services/member.service.ts");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_salestransaction_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../services/salestransaction.service */ "./src/app/services/salestransaction.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");








var AccountDetailsPage = /** @class */ (function () {
    function AccountDetailsPage(memberService, storage, alertController, saleTransactionService, router) {
        this.memberService = memberService;
        this.storage = storage;
        this.alertController = alertController;
        this.saleTransactionService = saleTransactionService;
        this.router = router;
        this.errorMessage = '';
        this.member = new _entities_member__WEBPACK_IMPORTED_MODULE_2__["Member"]();
        this.transactions = new Array();
    }
    AccountDetailsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.storage.get('currentCustomer').then(function (data) {
            _this.member = data;
            _this.memberId = _this.member.memberId;
            console.log('lOGGED IN CUSTOMER: ' + _this.memberId);
        });
        this.storage.get('isLogin').then(function (data) {
            _this.isLogin = data;
            _this.retrieveSalesTransactions();
            _this.getMember();
        });
    };
    AccountDetailsPage.prototype.ionViewWillEnter = function () {
    };
    AccountDetailsPage.prototype.retrieveSalesTransactions = function () {
        var _this = this;
        if (this.isLogin) {
            this.memberId = this.member.memberId;
            console.log('member name = ' + this.member.firstName);
            // this.viewAccountDetails();
            this.saleTransactionService.retrieveSalesTransactionByUserId(this.member.memberId).subscribe(function (response) {
                _this.transactions = response.salesTransactions;
                console.log('transaction Length= ' + _this.transactions.length);
                console.log(_this.transactions);
            }, function (error) {
                _this.errorMessage = error;
                _this.presentAlert(_this.errorMessage.substring(37));
            });
        }
    };
    AccountDetailsPage.prototype.getMember = function () {
        var _this = this;
        if (this.isLogin) {
            this.memberService.retrieveMember(this.member.memberId).subscribe(function (response) {
                _this.member = response.member;
                console.log("Current member id = " + _this.member.memberId);
            }, function (error) {
                _this.errorMessage = error;
                _this.presentAlert(_this.errorMessage.substring(37) + " cannot get member");
            });
        }
    };
    AccountDetailsPage.prototype.presentAlert = function (message) {
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
    AccountDetailsPage.prototype.logout = function () {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var logoutSuccess;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'Successfully Logged Out!',
                            buttons: ['OK']
                        })];
                    case 1:
                        logoutSuccess = _a.sent();
                        this.isLogin = false;
                        this.storage.set("isLogin", false);
                        this.storage.set("currentCustomer", undefined);
                        logoutSuccess.present();
                        this.router.navigate(['/home']);
                        return [2 /*return*/];
                }
            });
        });
    };
    AccountDetailsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-account-details',
            template: __webpack_require__(/*! ./account-details.page.html */ "./src/app/account-details/account-details.page.html"),
            styles: [__webpack_require__(/*! ./account-details.page.scss */ "./src/app/account-details/account-details.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_member_service__WEBPACK_IMPORTED_MODULE_3__["MemberService"], _ionic_storage__WEBPACK_IMPORTED_MODULE_4__["Storage"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["AlertController"], _services_salestransaction_service__WEBPACK_IMPORTED_MODULE_6__["SalestransactionService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_7__["Router"]])
    ], AccountDetailsPage);
    return AccountDetailsPage;
}());



/***/ }),

/***/ "./src/app/entities/member.ts":
/*!************************************!*\
  !*** ./src/app/entities/member.ts ***!
  \************************************/
/*! exports provided: Member */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Member", function() { return Member; });
var Member = /** @class */ (function () {
    function Member(memberId, firstName, lastName, username, password, loyaltyPoints, email, salt, wishList, saleTransactions, shoppingCart) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.loyaltyPoints = loyaltyPoints;
        this.salt = salt;
        this.wishList = wishList;
        this.saleTransactions = saleTransactions;
        this.shoppingCart = shoppingCart;
        this.email = email;
    }
    return Member;
}());



/***/ })

}]);
//# sourceMappingURL=account-details-account-details-module.js.map