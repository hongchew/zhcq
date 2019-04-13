/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Hong Chew
 */
public class AlreadyInWishlistException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyInWishlistException</code> without
     * detail message.
     */
    public AlreadyInWishlistException() {
    }

    /**
     * Constructs an instance of <code>AlreadyInWishlistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AlreadyInWishlistException(String msg) {
        super(msg);
    }
}
