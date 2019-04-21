(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["login-login-module"],{

/***/ "./src/app/login/login.module.ts":
/*!***************************************!*\
  !*** ./src/app/login/login.module.ts ***!
  \***************************************/
/*! exports provided: LoginPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginPageModule", function() { return LoginPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");
/* harmony import */ var _login_page__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./login.page */ "./src/app/login/login.page.ts");








var routes = [
    {
        path: '',
        component: _login_page__WEBPACK_IMPORTED_MODULE_7__["LoginPage"]
    }
];
var LoginPageModule = /** @class */ (function () {
    function LoginPageModule() {
    }
    LoginPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes), _components_shared_components_module__WEBPACK_IMPORTED_MODULE_6__["SharedComponentsModule"]
            ],
            declarations: [_login_page__WEBPACK_IMPORTED_MODULE_7__["LoginPage"]]
        })
    ], LoginPageModule);
    return LoginPageModule;
}());



/***/ }),

/***/ "./src/app/login/login.page.html":
/*!***************************************!*\
  !*** ./src/app/login/login.page.html ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n<br/>\n<ion-title style=\"text-align: center\" >Sign In</ion-title>\n<ion-grid>\n\t<ion-row justify-content-center>\n\t\t<ion-col size-md=\"6\" size-lg=\"5\" size-xs=\"12\">\n\t\t\t<div text-center>\n\t\t\t\t<h6>Welcome back <ion-icon ios=\"ios-happy\" md=\"md-happy\"></ion-icon></h6>\n\t\t\t</div>\n\t\t\t<div padding>\n\t\t\t\t<ion-item>\n        \t\t\t<ion-label>Username</ion-label>\n\t\t\t\t\t<ion-input type=\"text\" [(ngModel)]=\"username\" placeholder=\"Username\"></ion-input>\n\t\t\t\t</ion-item>\n\n\t\t\t\t<ion-item>\n        \t\t\t<ion-label>Password</ion-label>\n\t\t\t\t\t<ion-input type=\"password\" [(ngModel)] =\"password\" placeholder=\"Password\"></ion-input>\n\t\t\t\t</ion-item>\n\t\t\t</div>\n\n\t\t\t<div padding>\n\t\t\t\t<ion-button color=\"light\" size=\"small\" (click)=\"login()\" expand=\"block\">Login</ion-button>\n\t\t\t</div>\n\t\t</ion-col>\n\t</ion-row>\n</ion-grid>\n</ion-content>\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/login/login.page.scss":
/*!***************************************!*\
  !*** ./src/app/login/login.page.scss ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "ion-grid {\n  width: 100%;\n  height: 100%;\n  background: #dfdfdf; }\n\nion-row {\n  height: 100%; }\n\nion-col {\n  border: 1px solid #488aff;\n  background: #fff; }\n\nion-button {\n  font-weight: 300; }\n\n@media (min-width: 240px) and (max-width: 768px) {\n  ion-grid {\n    background: #fff; }\n  ion-col {\n    border: none; } }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvbG9naW4vbG9naW4ucGFnZS5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQU9BO0VBQ0ksV0FBVTtFQUNWLFlBSlk7RUFLWixtQkFBbUIsRUFBQTs7QUFHdkI7RUFDSSxZQVRZLEVBQUE7O0FBWWhCO0VBQ0kseUJBQXlCO0VBQ3pCLGdCQWZjLEVBQUE7O0FBa0JsQjtFQUNJLGdCQUFnQixFQUFBOztBQUdwQjtFQUNJO0lBQ0ksZ0JBeEJVLEVBQUE7RUEwQmQ7SUFDSSxZQUFZLEVBQUEsRUFDZiIsImZpbGUiOiJzcmMvYXBwL2xvZ2luL2xvZ2luLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbImFwcC1sb2dpbiB7XG4gICAgXG59XG5cbiR3aGl0ZS1jb2xvcjogI2ZmZjtcbiRoZWlnaHQxMDA6IDEwMCU7XG5cbmlvbi1ncmlkIHtcbiAgICB3aWR0aDoxMDAlO1xuICAgIGhlaWdodDogJGhlaWdodDEwMDtcbiAgICBiYWNrZ3JvdW5kOiAjZGZkZmRmO1xufVxuXG5pb24tcm93IHtcbiAgICBoZWlnaHQ6ICRoZWlnaHQxMDA7XG59XG5cbmlvbi1jb2wge1xuICAgIGJvcmRlcjogMXB4IHNvbGlkICM0ODhhZmY7XG4gICAgYmFja2dyb3VuZDogJHdoaXRlLWNvbG9yO1xufVxuXG5pb24tYnV0dG9uIHtcbiAgICBmb250LXdlaWdodDogMzAwO1xufVxuXG5AbWVkaWEgKG1pbi13aWR0aDogMjQwcHgpIGFuZCAobWF4LXdpZHRoOiA3NjhweCkge1xuICAgIGlvbi1ncmlkIHtcbiAgICAgICAgYmFja2dyb3VuZDogJHdoaXRlLWNvbG9yO1xuICAgIH1cbiAgICBpb24tY29sIHtcbiAgICAgICAgYm9yZGVyOiBub25lO1xuICAgIH1cbn0iXX0= */"

/***/ }),

/***/ "./src/app/login/login.page.ts":
/*!*************************************!*\
  !*** ./src/app/login/login.page.ts ***!
  \*************************************/
/*! exports provided: LoginPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginPage", function() { return LoginPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_member_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/member.service */ "./src/app/services/member.service.ts");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_storage__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/storage */ "./node_modules/@ionic/storage/fesm5/ionic-storage.js");






var LoginPage = /** @class */ (function () {
    function LoginPage(memberService, alertController, router, storage) {
        this.memberService = memberService;
        this.alertController = alertController;
        this.router = router;
        this.storage = storage;
    }
    LoginPage.prototype.ngOnInit = function () {
    };
    LoginPage.prototype.login = function () {
        var _this = this;
        this.memberService.login(this.username, this.password).subscribe(function (response) {
            _this.member = response.member;
            _this.storage.set('isLogin', true);
            _this.storage.set('currentCustomer', _this.member);
            _this.router.navigateByUrl('home');
            console.log("TEST FOR LOGGED IN ");
            console.log("Member = " + _this.member);
        }, function (error) {
            _this.presentAlert(error);
        });
    };
    LoginPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: "ERROR: " + message.substring(37),
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
    LoginPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.page.html */ "./src/app/login/login.page.html"),
            styles: [__webpack_require__(/*! ./login.page.scss */ "./src/app/login/login.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_member_service__WEBPACK_IMPORTED_MODULE_2__["MemberService"], _ionic_angular__WEBPACK_IMPORTED_MODULE_3__["AlertController"],
            _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"], _ionic_storage__WEBPACK_IMPORTED_MODULE_5__["Storage"]])
    ], LoginPage);
    return LoginPage;
}());



/***/ })

}]);
//# sourceMappingURL=login-login-module.js.map