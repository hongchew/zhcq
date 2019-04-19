import { ColourEnum } from './colour-enum.enum';
import { ProductTag } from './tag';
import { WishList } from './wishlist';
import { CoordinatedOutfit } from './outfit';
import { ShoppingCart } from './cart';
import { Promotion } from './promotion';
import { SizeEnum } from './size-enum.enum';
import { Category } from './category';

export class ProductEntity 
{
    productId: number;
    productName: string;
    description: string; 
    unitPrice: any;
    dateAdded: Date;
    quantityOnHand: number;
    sizeEnum: SizeEnum;
    colourEnum: ColourEnum;
    picturePath: string;

    productCategory: Category;
    productTags: ProductTag[];
    wishLists: WishList[];
    coordinateOutfits: CoordinatedOutfit[];
    shoppingCarts: ShoppingCart[];
    promotion: Promotion;

    constructor(productId?: number, productName?: string, description?: string, unitPrice?: any,
                dateAdded?: Date, quantityOnHand?: number, sizeEnum?: SizeEnum, colourEnum?: ColourEnum,
                picturePath?: string)
    {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.dateAdded = dateAdded;
        this.quantityOnHand = quantityOnHand;
        this.sizeEnum = sizeEnum;
        this.colourEnum = colourEnum;
        this.picturePath = picturePath;
    }
}