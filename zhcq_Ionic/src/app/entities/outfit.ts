import { ProductEntity } from './product';

export class CoordinatedOutfit {
    coordinatedOutfitId: number;
    outfitName: string;
    dateCreated: Date;
    description: string;
    picturePath: string;

    productEntities: ProductEntity[];

    constructor(coordinatedOutfitId?: number, outfitName?: string, dateCreated?: Date,
                 description?: string,picturePath?: string) {
        this.coordinatedOutfitId = coordinatedOutfitId;
        this.dateCreated = dateCreated;
        this.outfitName = outfitName;
        this.description = description;
        this.picturePath = picturePath;
    }
}
