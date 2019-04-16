import { ProductEntity } from './product';
import { SaleTransactionLineItem } from './lineitem';

export class SaleTransaction 
{
    saleTransactionId : number;
    saleTransactionLineItems : SaleTransactionLineItem[];  
    totalPrice : number;

    constructor(saleTransactionId ?: number, saleTransactionLineItems ?: SaleTransactionLineItem[], totalPrice ?: number){
        this.saleTransactionId = saleTransactionId;
        this.saleTransactionLineItems = saleTransactionLineItems;
        this.totalPrice = totalPrice;
    }
}