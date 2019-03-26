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
public class CreateNewWishListException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewWishListException</code> without
     * detail message.
     */
    public CreateNewWishListException() {
    }

    /**
     * Constructs an instance of <code>CreateNewWishListException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewWishListException(String msg) {
        super(msg);
    }
}
