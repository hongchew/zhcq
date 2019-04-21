import { ProductEntity } from './product';

export class Category
{
    categoryId: number; 
    categoryName: string;
    description: string; 
    picturePath: string;

    productEntities: ProductEntity[];
    
    constructor(categoryId?: number, categoryName?: string, description?: string, picturePath?: string)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName; 
        this.description = description;
        this.picturePath = picturePath;
    }
        
	
}