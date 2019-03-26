/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author zhimingkoh
 */
public class WishListNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>WishListNotFoundException</code> without
     * detail message.
     */
    public WishListNotFoundException() {
    }

    /**
     * Constructs an instance of <code>WishListNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WishListNotFoundException(String msg) {
        super(msg);
    }
}
