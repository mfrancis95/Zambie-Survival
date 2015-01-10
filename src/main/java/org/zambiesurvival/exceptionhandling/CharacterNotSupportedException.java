/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.org.zambiesurvival.exceptionhandling;

/**
 *
 * @author Nelnel33
 */
public class CharacterNotSupportedException extends RuntimeException {

    /**
     * Creates a new instance of <code>CharacterNotSupportedException</code>
     * without detail message.
     */
    public CharacterNotSupportedException() {
    }

    /**
     * Constructs an instance of <code>CharacterNotSupportedException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CharacterNotSupportedException(String msg) {
        super(msg);
    }
}
