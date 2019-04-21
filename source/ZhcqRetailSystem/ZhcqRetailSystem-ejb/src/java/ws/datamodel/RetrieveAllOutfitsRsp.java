/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.CoordinatedOutfit;
import java.util.List;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveAllOutfitsRsp {
    private List<CoordinatedOutfit> outfits;

    public RetrieveAllOutfitsRsp() {
    }

    public RetrieveAllOutfitsRsp(List<CoordinatedOutfit> outfits) {
        this.outfits = outfits;
    }

    
    public List<CoordinatedOutfit> getOutfits() {
        return outfits;
    }

   
    public void setOutfits(List<CoordinatedOutfit> outfits) {
        this.outfits = outfits;
    }
    
    
    
}
