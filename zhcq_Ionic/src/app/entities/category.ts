import { ProductEntity } from './product';
import { WishList } from './wishlist';
import { CoordinatedOutfit } from './outfit';
import { ShoppingCart } from './cart';
import { Promotion } from './promotion';

export class Category
{
    categoryId: number; 
    categoryName: string;
    description: string; 
    picturePath: string; 
    
    productEntities: ProductEntity[];
    wishLists: WishList[];
    coordinateOutfits: CoordinatedOutfit[];
    shoppingCarts: ShoppingCart[];
    promotion: Promotion[];
    
}