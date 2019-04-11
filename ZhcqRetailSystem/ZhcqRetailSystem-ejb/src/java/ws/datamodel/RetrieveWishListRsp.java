/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.WishList;

/**
 *
 * @author Hong Chew
 */
public class RetrieveWishListRsp {
    private WishList wishlist;

    public RetrieveWishListRsp() {
    }

    public RetrieveWishListRsp(WishList wishlist) {
        this.wishlist = wishlist;
    }
    
    /**
     * @return the wishlist
     */
    public WishList getWishlist() {
        return wishlist;
    }

    /**
     * @param wishlist the wishlist to set
     */
    public void setWishlist(WishList wishlist) {
        this.wishlist = wishlist;
    }
    
    
    
}
