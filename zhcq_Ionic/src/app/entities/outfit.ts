import { ProductEntity } from './product';

export class CoordinatedOutfit
{
    coordinatedOutfitId: number;
    outfitName: string;
    dateCreated: Date;
    
    productEntities: ProductEntity[];

    constructor(coordinatedOutfitId?: number, outfitName?: string,dateCreated?: Date) {
        this.coordinatedOutfitId = coordinatedOutfitId;
        this.dateCreated = dateCreated;
        this.outfitName = outfitName;
    }
}