/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ShoppingCart;

/**
 *
 * @author Hong Chew
 */
public class RetrieveShoppingCartRsp {
    private ShoppingCart userShoppingCart;

    public RetrieveShoppingCartRsp() {
    }
    
    public RetrieveShoppingCartRsp(ShoppingCart userShoppingCart) {
        this.userShoppingCart = userShoppingCart;
    }

    /**
     * @return the userShoppingCart
     */
    public ShoppingCart getUserShoppingCart() {
        return userShoppingCart;
    }

    /**
     * @param userShoppingCart the userShoppingCart to set
     */
    public void setUserShoppingCart(ShoppingCart userShoppingCart) {
        this.userShoppingCart = userShoppingCart;
    }
    
    
}
