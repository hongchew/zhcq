import { Member } from './member';
import { ProductEntity } from './product';
export class WishList
{
    wishListId: number;
    member: Member;
    products: ProductEntity[];

    constructor(cartId?: number, member?: Member, products?: ProductEntity[]) {
        this.wishListId = cartId;
        this.member = member;
        this.products = products;
    }
}