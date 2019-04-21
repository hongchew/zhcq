/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.SaleTransaction;
import entity.ShoppingCart;
import javax.ejb.Local;
import util.exception.EmptyShoppingCartException;
import util.exception.MemberNotFoundException;
import util.exception.OutOfStockException;
import util.exception.ProductNotFoundException;
import util.exception.SaleTransactionNotFoundException;
import util.exception.ShoppingCartNotFoundException;

/**
 *
 * @author chengyang
 */
@Local
public interface CheckoutControllerLocal {
    
    public ShoppingCart retrieveShoppingCartById(Long cartId) throws ShoppingCartNotFoundException;
    public SaleTransaction retrieveSaleTransactionById(Long transactionId) throws SaleTransactionNotFoundException;
    public void addToCart(Long cartId, Long productId, Integer quantity) throws ShoppingCartNotFoundException, ProductNotFoundException;
    public ShoppingCart retrieveShoppingCartByUserId(Long userId) throws ShoppingCartNotFoundException;
    public void removeFromCart(Long cartId, Long productId) throws ShoppingCartNotFoundException, ProductNotFoundException;
    public void updateCart(Long cartId, Long productId, Integer quantity) throws ShoppingCartNotFoundException, ProductNotFoundException;

    public SaleTransaction checkOutWithPoints(Long cartId) throws ShoppingCartNotFoundException, EmptyShoppingCartException, OutOfStockException, MemberNotFoundException;

    public SaleTransaction checkOut(Long cartId) throws ShoppingCartNotFoundException, EmptyShoppingCartException, OutOfStockException, MemberNotFoundException;
}
