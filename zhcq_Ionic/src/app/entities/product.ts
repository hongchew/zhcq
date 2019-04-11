import { ColourEnum } from './colour-enum.enum';
import { ProductTag } from './tag';
import { WishList } from './wishlist';
import { CoordinatedOutfit } from './outfit';
import { ShoppingCart } from './cart';
import { Promotion } from './promotion';

export class ProductEntity 
{
    productId: number;
    productName: string;
    description: string; 
    unitPrice: bigint;
    dateAdded: Date;
    quantityOnHand: number;
    colourEnum: ColourEnum;
    picturePath: string; 

    productTags: ProductTag[];
    wishLists: WishList[];
    coordinateOutfits: CoordinatedOutfit[];
    shoppingCarts: ShoppingCart[];
    promotion: Promotion;

    constructor(productId?: number, productName?: string, description?: string, unitPrice?: bigint, dateAdded?: Date, quantityOnHand?: number, colourEnum?: ColourEnum, picturePath?: string)
    {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.dateAdded = dateAdded;
        this.quantityOnHand = quantityOnHand;
        this.colourEnum = colourEnum;
        this.picturePath = picturePath;
    }
        
}