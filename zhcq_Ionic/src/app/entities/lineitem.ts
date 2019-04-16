import { ProductEntity } from './product';
import { Promotion } from './promotion';

export class SaleTransactionLineItem
{
    productEntity : ProductEntity;
    promotionApplied : Promotion;
    quantity : number;
    saleTransactionLineItemId : number;
    subTotal : number;

    constructor(productEntity ?: ProductEntity, promotionApplied ?: Promotion, quantity ?: number, saleTransactionLineItemId ?: number, subTotal ?: number){
        this.productEntity = productEntity;
        this.promotionApplied = promotionApplied;
        this.quantity = quantity;
        this.saleTransactionLineItemId = saleTransactionLineItemId;
        this.subTotal = subTotal;
    }

}