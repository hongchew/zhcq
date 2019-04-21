import { ProductEntity } from './product';

export class Promotion{
    promotionId: number;
    promotionName: string;
    discountRate: any;
    startDate: Date;
    endDate: Date;

    promotionalProducts: ProductEntity[];

    constructor(promotionId?: number,promotionName?: string, discountRate?: any,startDate?: Date,endDate?: Date)
    {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}