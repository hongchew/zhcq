/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.CoordinatedOutfit;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveOutfitByIdRsp {
    
    private CoordinatedOutfit outfit;

    public RetrieveOutfitByIdRsp() {
    }

    public RetrieveOutfitByIdRsp(CoordinatedOutfit outfit) {
        this.outfit = outfit;
    }

   
    public CoordinatedOutfit getOutfit() {
        return outfit;
    }

   
    public void setOutfit(CoordinatedOutfit outfit) {
        this.outfit = outfit;
    }
    
    
    
}
