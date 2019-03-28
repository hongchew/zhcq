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
public class CreateNewOutfitException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewOutfitException</code> without
     * detail message.
     */
    public CreateNewOutfitException() {
    }

    /**
     * Constructs an instance of <code>CreateNewOutfitException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewOutfitException(String msg) {
        super(msg);
    }
}
