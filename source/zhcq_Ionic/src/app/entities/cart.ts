import { Member } from './member';
import { ProductEntity } from './product';
export class ShoppingCart
{
    cartId: number;
    member: Member;
    products: ProductEntity[];
    quantity: number[];

    constructor(cartId?: number, member?: Member, products?: ProductEntity[], quantity?: number[]) {
        this.cartId = cartId;
        this.member = member;
        this.products = products;
        this.quantity = quantity;
    }
}