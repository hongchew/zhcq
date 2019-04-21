/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Category;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveCategoyByIdRsp {
    private Category category;

    public RetrieveCategoyByIdRsp() {
    }

    public RetrieveCategoyByIdRsp(Category category) {
        this.category = category;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
