/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Promotion;

/**
 *
 * @author Hong Chew
 */
public class RetrievePromotionByIdRsp {
    
    private Promotion promotion;

    /**
     * @return the promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }

    public RetrievePromotionByIdRsp(Promotion promotion) {
        this.promotion = promotion;
    }

    public RetrievePromotionByIdRsp() {
    }

    /**
     * @param promotion the promotion to set
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    
    
    
}
