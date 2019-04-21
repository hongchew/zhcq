import { ProductEntity } from './product';

export class ProductTag{
    productTagId: number;
    productTagName: string;

    productEntities: ProductEntity[];

    constructor(productTagId?:number,productTagName?:string){
        this.productTagId = productTagId;
        this.productTagName = productTagName;
    }
}