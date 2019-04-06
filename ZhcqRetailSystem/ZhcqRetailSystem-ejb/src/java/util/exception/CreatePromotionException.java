/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author chengyang
 */
public class CreatePromotionException extends Exception {

    /**
     * Creates a new instance of <code>CreatePromotionException</code> without
     * detail message.
     */
    public CreatePromotionException() {
    }

    /**
     * Constructs an instance of <code>CreatePromotionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreatePromotionException(String msg) {
        super(msg);
    }
}
