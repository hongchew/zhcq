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
public class DeleteWishListException extends Exception {

    /**
     * Creates a new instance of <code>DeleteWishListException</code> without
     * detail message.
     */
    public DeleteWishListException() {
    }

    /**
     * Constructs an instance of <code>DeleteWishListException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteWishListException(String msg) {
        super(msg);
    }
}
