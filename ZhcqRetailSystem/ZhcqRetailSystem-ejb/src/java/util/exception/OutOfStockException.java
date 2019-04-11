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
public class OutOfStockException extends Exception {

    /**
     * Creates a new instance of <code>OutOfStockException</code> without detail
     * message.
     */
    public OutOfStockException() {
    }

    /**
     * Constructs an instance of <code>OutOfStockException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OutOfStockException(String msg) {
        super(msg);
    }
}
