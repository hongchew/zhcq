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
public class UpdateWishListException extends Exception {

    /**
     * Creates a new instance of <code>UpdateWishListException</code> without
     * detail message.
     */
    public UpdateWishListException() {
    }

    /**
     * Constructs an instance of <code>UpdateWishListException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UpdateWishListException(String msg) {
        super(msg);
    }
}
