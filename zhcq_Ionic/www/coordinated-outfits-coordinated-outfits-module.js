(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["coordinated-outfits-coordinated-outfits-module"],{

/***/ "./src/app/coordinated-outfits/coordinated-outfits.module.ts":
/*!*******************************************************************!*\
  !*** ./src/app/coordinated-outfits/coordinated-outfits.module.ts ***!
  \*******************************************************************/
/*! exports provided: CoordinatedOutfitsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CoordinatedOutfitsPageModule", function() { return CoordinatedOutfitsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _coordinated_outfits_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./coordinated-outfits.page */ "./src/app/coordinated-outfits/coordinated-outfits.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _coordinated_outfits_page__WEBPACK_IMPORTED_MODULE_6__["CoordinatedOutfitsPage"]
    }
];
var CoordinatedOutfitsPageModule = /** @class */ (function () {
    function CoordinatedOutfitsPageModule() {
    }
    CoordinatedOutfitsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_coordinated_outfits_page__WEBPACK_IMPORTED_MODULE_6__["CoordinatedOutfitsPage"]]
        })
    ], CoordinatedOutfitsPageModule);
    return CoordinatedOutfitsPageModule;
}());



/***/ }),

/***/ "./src/app/coordinated-outfits/coordinated-outfits.page.html":
/*!*******************************************************************!*\
  !*** ./src/app/coordinated-outfits/coordinated-outfits.page.html ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n    <div *ngIf='errorMessage' class=\"errorText\">\n        {{errorMessage}}\n    </div>\n    <div *ngIf='!errorMessage'>\n        <h2 text-center style=\"font-weight: bold\">C&nbsp;&nbsp;O&nbsp;&nbsp;L&nbsp;&nbsp;L&nbsp;&nbsp;E&nbsp;&nbsp;C&nbsp;&nbsp;T&nbsp;&nbsp;I&nbsp;&nbsp;O&nbsp;&nbsp;N</h2>\n        <ion-grid> \n            <ion-row>\n                <ion-col size=12 *ngFor=\"let outfit of outfits\" [routerLink]=\"['/coordinated-outfit-details',outfit.coordinatedOutfitId]\" class=\"activated\">\n                    <ion-card>\n                        <ion-img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{outfit.picturePath}}\"></ion-img>\n                            <ion-card-content>\n                                <ion-card-title>{{outfit.outfitName}}</ion-card-title>\n                                <ion-card-subtitle>{{outfit.description}}</ion-card-subtitle>\n                            </ion-card-content>\n                    </ion-card>\n                </ion-col>\n            </ion-row>\n        </ion-grid>\n    </div>\n\n</ion-content>\n\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/coordinated-outfits/coordinated-outfits.page.scss":
/*!*******************************************************************!*\
  !*** ./src/app/coordinated-outfits/coordinated-outfits.page.scss ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Nvb3JkaW5hdGVkLW91dGZpdHMvY29vcmRpbmF0ZWQtb3V0Zml0cy5wYWdlLnNjc3MifQ== */"

/***/ }),

/***/ "./src/app/coordinated-outfits/coordinated-outfits.page.ts":
/*!*****************************************************************!*\
  !*** ./src/app/coordinated-outfits/coordinated-outfits.page.ts ***!
  \*****************************************************************/
/*! exports provided: CoordinatedOutfitsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CoordinatedOutfitsPage", function() { return CoordinatedOutfitsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_outfit_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/outfit.service */ "./src/app/services/outfit.service.ts");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");





var CoordinatedOutfitsPage = /** @class */ (function () {
    function CoordinatedOutfitsPage(outfitService, activatedRoute, alertController) {
        this.outfitService = outfitService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.errorMessage = '';
    }
    CoordinatedOutfitsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.outfitService.retrieveAllOutfits().subscribe(function (response) {
            console.log(response);
            _this.outfits = response.outfits;
        }, function (error) {
            _this.errorMessage = error;
            _this.presentAlert(_this.errorMessage.substring(37));
        });
    };
    CoordinatedOutfitsPage.prototype.presentAlert = function (message) {
        return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](this, void 0, void 0, function () {
            var alert;
            return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, this.alertController.create({
                            header: 'Error: ' + message,
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
    CoordinatedOutfitsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-coordinated-outfits',
            template: __webpack_require__(/*! ./coordinated-outfits.page.html */ "./src/app/coordinated-outfits/coordinated-outfits.page.html"),
            styles: [__webpack_require__(/*! ./coordinated-outfits.page.scss */ "./src/app/coordinated-outfits/coordinated-outfits.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_outfit_service__WEBPACK_IMPORTED_MODULE_3__["OutfitService"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["ActivatedRoute"], _ionic_angular__WEBPACK_IMPORTED_MODULE_4__["AlertController"]])
    ], CoordinatedOutfitsPage);
    return CoordinatedOutfitsPage;
}());



/***/ })

}]);
//# sourceMappingURL=coordinated-outfits-coordinated-outfits-module.js.map