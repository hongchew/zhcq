/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Promotion;
import java.util.List;

/**
 *
 * @author Hong Chew
 */
public class RetrieveAllPromotionRsp {
    private List<Promotion> promotions;

    public RetrieveAllPromotionRsp() {
    }

    public RetrieveAllPromotionRsp(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    /**
     * @return the promotions
     */
    public List<Promotion> getPromotions() {
        return promotions;
    }

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    
    
}
