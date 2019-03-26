/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.WishList;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewWishListException;
import util.exception.DeleteWishListException;
import util.exception.InputDataValidationException;
import util.exception.MemberNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.UpdateWishListException;
import util.exception.WishListNotFoundException;

/**
 *
 * @author zhimingkoh
 */
@Local
public interface WishListControllerLocal {

    public List<WishList> retrieveAllWishList();

    public WishList retrieveWishListByMemberId(Long memberId) throws WishListNotFoundException, MemberNotFoundException;

    public void deleteWishList(Long memberId) throws WishListNotFoundException, MemberNotFoundException, DeleteWishListException;

    public void updateWishList(WishList wishlist, List<Long> productEntityIds) throws InputDataValidationException, WishListNotFoundException, UpdateWishListException, ProductNotFoundException;

    public WishList retrieveWishListByWishListId(Long wishlistId) throws WishListNotFoundException;

    public void addProductToWishlist(Long memberId, Long pdtId) throws MemberNotFoundException, ProductNotFoundException;

    public void removeProductFromWishlist(Long memberId, Long pdtId) throws MemberNotFoundException, ProductNotFoundException;
    
}
