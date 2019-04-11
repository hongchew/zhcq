import { ColourEnum } from './colour-enum.enum';
import { ProductTag } from './tag';

export class ProductEntity 
{
    productId: number;
    productName: string;
    description: string; 
    unitPrice: bigint;
    dateAdded: Date;
    quantityOnHand: number;
    colourEnum: ColourEnum;

    productTags: ProductTag;
}