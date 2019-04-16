import { Member } from './member';
import { SaleTransactionLineItem } from './lineitem';

export class SaleTransaction 
{
    saleTransactionId: number;
    member: Member;
    saleTransactionLineItems: SaleTransactionLineItem[];
    totalPrice: number;

    constructor(saleTransactionId?: number, member?: Member, saleTransactionLineItems?: SaleTransactionLineItem[], totalPrice?: number) {
        this.saleTransactionId = saleTransactionId;
        this.member = member;
        this.saleTransactionLineItems = saleTransactionLineItems;
        this.totalPrice = totalPrice;
    }
}