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
public class UpdateProductTagException extends Exception {

    /**
     * Creates a new instance of <code>UpdateTagException</code> without detail
     * message.
     */
    public UpdateProductTagException() {
    }

    /**
     * Constructs an instance of <code>UpdateTagException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UpdateProductTagException(String msg) {
        super(msg);
    }
}
