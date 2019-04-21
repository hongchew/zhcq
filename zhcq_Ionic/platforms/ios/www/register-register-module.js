(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["register-register-module"],{

/***/ "./src/app/register/register.module.ts":
/*!*********************************************!*\
  !*** ./src/app/register/register.module.ts ***!
  \*********************************************/
/*! exports provided: RegisterPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterPageModule", function() { return RegisterPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _register_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./register.page */ "./src/app/register/register.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _register_page__WEBPACK_IMPORTED_MODULE_6__["RegisterPage"]
    }
];
var RegisterPageModule = /** @class */ (function () {
    function RegisterPageModule() {
    }
    RegisterPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_register_page__WEBPACK_IMPORTED_MODULE_6__["RegisterPage"]]
        })
    ], RegisterPageModule);
    return RegisterPageModule;
}());



/***/ }),

/***/ "./src/app/register/register.page.html":
/*!*********************************************!*\
  !*** ./src/app/register/register.page.html ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content color = \"floralwhite\">\n\n<div *ngIf=\"infoMessage\" class=\"infoText\">\n{{infoMessage}}\n</div>\n\n<div *ngIf=\"errorMessage\" class=\"errorText\">\n\t{{errorMessage}}\n</div>\n\n\n    <ion-grid>\n      <ion-row justify-content-center>\n        <ion-col align-self-center size-md=\"6\" size-lg=\"5\" size-xs=\"12\">\n          <div text-center>\n            <h6>Hi, seems like you're new here!</h6>\n            <h6>Create an account below <ion-icon ios=\"ios-arrow-round-down\" md=\"md-arrow-round-down\"></ion-icon></h6>\n          </div>\n          <div padding>\n            <ion-item>\n              <ion-label>First Name</ion-label>\n                <ion-input [(ngModel)]=\"firstName\"  placeholder=\"First Name\"  required></ion-input>\n            </ion-item> \n            <br/>\n            <ion-item>\n              <ion-label>Last Name</ion-label>\n                <ion-input [(ngModel)]=\"lastName\"  placeholder=\"Last Name\" required></ion-input>\n            </ion-item>\n            <br/>\n            <ion-item>\n              <ion-label>Username</ion-label>\n                <ion-input [(ngModel)]=\"username\" placeholder=\"Username\" required></ion-input>\n            </ion-item>\n            <br/>\n            <ion-item>\n              <ion-label>Email</ion-label>\n                <ion-input [(ngModel)]=\"email\" placeholder=\"zhcqmember@email.com\" required></ion-input>\n            </ion-item>\n            <br/>\n            <ion-item>\n              <ion-label>Password</ion-label>\n                <ion-input [(ngModel)]=\"password\" type=\"password\" placeholder=\"Password\" required></ion-input>\n            </ion-item>\n            <br/>\n            <ion-item>\n              <ion-label>Re-enter password</ion-label>\n                <ion-input [(ngModel)]=\"confirmPassword\" type=\"password\" placeholder=\"Re enter password\" required></ion-input>\n            </ion-item>\n          </div>\n          <div padding>\n            <ion-button color=\"light\" (click)=\"registerMember()\" size=\"small\" expand=\"block\">Register</ion-button>\n          </div>\n          </ion-col>\n      </ion-row>\n    </ion-grid>\n\n</ion-content>\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/register/register.page.scss":
/*!*********************************************!*\
  !*** ./src/app/register/register.page.scss ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3JlZ2lzdGVyL3JlZ2lzdGVyLnBhZ2Uuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/register/register.page.ts":
/*!*******************************************!*\
  !*** ./src/app/register/register.page.ts ***!
  \*******************************************/
/*! exports provided: RegisterPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterPage", function() { return RegisterPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_member_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/member.service */ "./src/app/services/member.service.ts");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");




var RegisterPage = /** @class */ (function () {
    function RegisterPage(memberService, alertController) {
        this.memberService = memberService;
        this.alertController = alertController;
    }
    RegisterPage.prototype.ngOnInit = function () {
    };
    RegisterPage.prototype.registerMember = function () {
        var _this = this;
        console.log(this.firstName);
        console.log(this.lastName);
        console.log(this.username);
        console.log(this.password);
        console.log(this.email);
        if (this.password != this.confirmPassword) {
            this.presentAlert("Password does not match");
        }
        else {
            this.memberService.createMember(this.firstName, this.lastName, this.username, this.password, this.email).subscribe(function (response) {
                _this.newMember = response.member;
                _this.presentAlert("Your account has been created, " + _this.firstName + " " + _this.lastName + "!");
            }, function (error) {
                _this.presentAlert(error.substring(37));
            });
        }
    };
    RegisterPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: "ERROR: " + message,
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
    RegisterPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-register',
            template: __webpack_require__(/*! ./register.page.html */ "./src/app/register/register.page.html"),
            styles: [__webpack_require__(/*! ./register.page.scss */ "./src/app/register/register.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_member_service__WEBPACK_IMPORTED_MODULE_2__["MemberService"], _ionic_angular__WEBPACK_IMPORTED_MODULE_3__["AlertController"]])
    ], RegisterPage);
    return RegisterPage;
}());



/***/ })

}]);
//# sourceMappingURL=register-register-module.js.map