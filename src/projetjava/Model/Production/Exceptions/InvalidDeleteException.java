/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production.Exceptions;

/**
 *
 * @author hajri
 */
public class InvalidDeleteException extends Exception {
    private final String msg;
    public InvalidDeleteException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public String getMsg(){
        return  msg;
    }
}
