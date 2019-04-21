import { Member } from './member';
import { ProductEntity } from './product';
export class WishList
{
    wishListId: number;
    member: Member;
    productEntities: ProductEntity[];

    constructor(cartId?: number, member?: Member, productEntities?: ProductEntity[]) {
        this.wishListId = cartId;
        this.member = member;
        this.productEntities = productEntities;
    }
}