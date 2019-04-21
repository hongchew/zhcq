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
public class EmptyShoppingCartException extends Exception {

    /**
     * Creates a new instance of <code>EmptyShoppingCartException</code> without
     * detail message.
     */
    public EmptyShoppingCartException() {
    }

    /**
     * Constructs an instance of <code>EmptyShoppingCartException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyShoppingCartException(String msg) {
        super(msg);
    }
}
