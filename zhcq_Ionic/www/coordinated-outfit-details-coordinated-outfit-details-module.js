(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["coordinated-outfit-details-coordinated-outfit-details-module"],{

/***/ "./src/app/coordinated-outfit-details/coordinated-outfit-details.module.ts":
/*!*********************************************************************************!*\
  !*** ./src/app/coordinated-outfit-details/coordinated-outfit-details.module.ts ***!
  \*********************************************************************************/
/*! exports provided: CoordinatedOutfitDetailsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CoordinatedOutfitDetailsPageModule", function() { return CoordinatedOutfitDetailsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _coordinated_outfit_details_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./coordinated-outfit-details.page */ "./src/app/coordinated-outfit-details/coordinated-outfit-details.page.ts");
/* harmony import */ var _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../components/shared-components.module */ "./src/app/components/shared-components.module.ts");








var routes = [
    {
        path: '',
        component: _coordinated_outfit_details_page__WEBPACK_IMPORTED_MODULE_6__["CoordinatedOutfitDetailsPage"]
    }
];
var CoordinatedOutfitDetailsPageModule = /** @class */ (function () {
    function CoordinatedOutfitDetailsPageModule() {
    }
    CoordinatedOutfitDetailsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
                _components_shared_components_module__WEBPACK_IMPORTED_MODULE_7__["SharedComponentsModule"]
            ],
            declarations: [_coordinated_outfit_details_page__WEBPACK_IMPORTED_MODULE_6__["CoordinatedOutfitDetailsPage"]]
        })
    ], CoordinatedOutfitDetailsPageModule);
    return CoordinatedOutfitDetailsPageModule;
}());



/***/ }),

/***/ "./src/app/coordinated-outfit-details/coordinated-outfit-details.page.html":
/*!*********************************************************************************!*\
  !*** ./src/app/coordinated-outfit-details/coordinated-outfit-details.page.html ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n\n<ion-content>\n    <div *ngIf=\"errorMessage\">\n        {{errorMessage}}\n    </div>\n        \n    <!-- SELECTED Outfit -->\n    <div *ngIf=\"!errorMessage\">\n\n            <ion-img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{selectedOutfit.picturePath}}\"></ion-img>\n\n        <ion-item>\n            <ion-label class=\"outfitLabel\">\n                {{selectedOutfit.outfitName}}\n            </ion-label> \n        </ion-item>\n        <ion-card>\n            <ion-list>\n                <ion-item *ngFor=\"let product of selectedOutfit.productEntities\" [routerLink]=\"['/product-details',product.productId]\">\n                    <img src=\"http://localhost:8080/ZhcqRetailSystem-war/{{product.picturePath}}\" class=\"productImg\"/>\n                    <ion-label class=\"productLabel\">\n                        {{product.productName}}\n                    </ion-label>\n                </ion-item>\n            </ion-list>\n        </ion-card>\n        \n\n\n\n    </div>\n</ion-content>\n\n<app-footer></app-footer>"

/***/ }),

/***/ "./src/app/coordinated-outfit-details/coordinated-outfit-details.page.scss":
/*!*********************************************************************************!*\
  !*** ./src/app/coordinated-outfit-details/coordinated-outfit-details.page.scss ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".outfitLabel {\n  font-weight: bold;\n  font-style: italic;\n  font-variant: small-caps;\n  text-align: center;\n  color: peachpuff;\n  font-size: 150%; }\n\n.productImg {\n  height: 95%;\n  width: 35%;\n  border-right: 10%; }\n\n.productLabel {\n  border: 10px;\n  font-weight: bold;\n  text-align: center; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy9jaGVuZ3lhbmcvRGVza3RvcC9JUzMxMDYvemhjcS1jby96aGNxX0lvbmljL3NyYy9hcHAvY29vcmRpbmF0ZWQtb3V0Zml0LWRldGFpbHMvY29vcmRpbmF0ZWQtb3V0Zml0LWRldGFpbHMucGFnZS5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0ksaUJBQWlCO0VBQ2pCLGtCQUFrQjtFQUNsQix3QkFBd0I7RUFDeEIsa0JBQWtCO0VBQ2xCLGdCQUFnQjtFQUNoQixlQUNKLEVBQUE7O0FBR0E7RUFDSSxXQUFXO0VBQ1gsVUFBVTtFQUNWLGlCQUVKLEVBQUE7O0FBRUE7RUFDSSxZQUFZO0VBQ1osaUJBQWlCO0VBQ2pCLGtCQUFrQixFQUFBIiwiZmlsZSI6InNyYy9hcHAvY29vcmRpbmF0ZWQtb3V0Zml0LWRldGFpbHMvY29vcmRpbmF0ZWQtb3V0Zml0LWRldGFpbHMucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLm91dGZpdExhYmVse1xuICAgIGZvbnQtd2VpZ2h0OiBib2xkO1xuICAgIGZvbnQtc3R5bGU6IGl0YWxpYztcbiAgICBmb250LXZhcmlhbnQ6IHNtYWxsLWNhcHM7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIGNvbG9yOiBwZWFjaHB1ZmY7XG4gICAgZm9udC1zaXplOiAxNTAlXG59XG5cblxuLnByb2R1Y3RJbWd7XG4gICAgaGVpZ2h0OiA5NSU7XG4gICAgd2lkdGg6IDM1JTtcbiAgICBib3JkZXItcmlnaHQ6IDEwJVxuICAgIFxufVxuXG4ucHJvZHVjdExhYmVse1xuICAgIGJvcmRlcjogMTBweDtcbiAgICBmb250LXdlaWdodDogYm9sZDtcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59XG5cbiJdfQ== */"

/***/ }),

/***/ "./src/app/coordinated-outfit-details/coordinated-outfit-details.page.ts":
/*!*******************************************************************************!*\
  !*** ./src/app/coordinated-outfit-details/coordinated-outfit-details.page.ts ***!
  \*******************************************************************************/
/*! exports provided: CoordinatedOutfitDetailsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CoordinatedOutfitDetailsPage", function() { return CoordinatedOutfitDetailsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_outfit_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/outfit.service */ "./src/app/services/outfit.service.ts");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _entities_outfit__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../entities/outfit */ "./src/app/entities/outfit.ts");






var CoordinatedOutfitDetailsPage = /** @class */ (function () {
    function CoordinatedOutfitDetailsPage(outfitService, activatedRoute, alertController) {
        this.outfitService = outfitService;
        this.activatedRoute = activatedRoute;
        this.alertController = alertController;
        this.errorMessage = '';
        this.selectedOutfit = new _entities_outfit__WEBPACK_IMPORTED_MODULE_5__["CoordinatedOutfit"]();
    }
    CoordinatedOutfitDetailsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.outfitId = parseInt(this.activatedRoute.snapshot.paramMap.get('outfitId'));
        console.log("Outfit ID: " + this.outfitId);
        this.outfitService.retrieveOutfit(this.outfitId).subscribe(function (response) {
            _this.selectedOutfit = response.outfit;
        }, function (error) {
            _this.errorMessage = error;
            _this.presentAlert(error.substring(37));
        });
    };
    CoordinatedOutfitDetailsPage.prototype.presentAlert = function (message) {
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
    CoordinatedOutfitDetailsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-coordinated-outfit-details',
            template: __webpack_require__(/*! ./coordinated-outfit-details.page.html */ "./src/app/coordinated-outfit-details/coordinated-outfit-details.page.html"),
            styles: [__webpack_require__(/*! ./coordinated-outfit-details.page.scss */ "./src/app/coordinated-outfit-details/coordinated-outfit-details.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_outfit_service__WEBPACK_IMPORTED_MODULE_2__["OutfitService"], _angular_router__WEBPACK_IMPORTED_MODULE_4__["ActivatedRoute"], _ionic_angular__WEBPACK_IMPORTED_MODULE_3__["AlertController"]])
    ], CoordinatedOutfitDetailsPage);
    return CoordinatedOutfitDetailsPage;
}());



/***/ }),

/***/ "./src/app/entities/outfit.ts":
/*!************************************!*\
  !*** ./src/app/entities/outfit.ts ***!
  \************************************/
/*! exports provided: CoordinatedOutfit */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CoordinatedOutfit", function() { return CoordinatedOutfit; });
var CoordinatedOutfit = /** @class */ (function () {
    function CoordinatedOutfit(coordinatedOutfitId, outfitName, dateCreated, description, picturePath) {
        this.coordinatedOutfitId = coordinatedOutfitId;
        this.dateCreated = dateCreated;
        this.outfitName = outfitName;
        this.description = description;
        this.picturePath = picturePath;
    }
    return CoordinatedOutfit;
}());



/***/ })

}]);
//# sourceMappingURL=coordinated-outfit-details-coordinated-outfit-details-module.js.map