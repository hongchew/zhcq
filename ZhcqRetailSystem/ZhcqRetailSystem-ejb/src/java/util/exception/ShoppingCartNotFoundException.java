/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author qianyi
 */

public class ShoppingCartNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ShoppingCartNotFoundException</code> without
     * detail message.
     */
    public ShoppingCartNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ShoppingCartNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ShoppingCartNotFoundException(String msg) {
        super(msg);
    }
}