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
public class UpdateMemberException extends Exception {

    /**
     * Creates a new instance of <code>UpdateMemberException</code> without
     * detail message.
     */
    public UpdateMemberException() {
    }

    /**
     * Constructs an instance of <code>UpdateMemberException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UpdateMemberException(String msg) {
        super(msg);
    }
}
