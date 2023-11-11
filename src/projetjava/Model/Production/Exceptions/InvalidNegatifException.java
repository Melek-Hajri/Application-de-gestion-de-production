/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production.Exceptions;

/**
 *
 * @author hajri
 */
public class InvalidNegatifException extends Exception {
    private String msg;
    public InvalidNegatifException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public String getMsg(){
        return  msg;
    }
}
