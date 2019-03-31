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
public class OutfitNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>OutfitNotFoundException</code> without
     * detail message.
     */
    public OutfitNotFoundException() {
    }

    /**
     * Constructs an instance of <code>OutfitNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OutfitNotFoundException(String msg) {
        super(msg);
    }
}
