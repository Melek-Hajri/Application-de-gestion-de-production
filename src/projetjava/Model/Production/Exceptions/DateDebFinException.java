/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production.Exceptions;

/**
 *
 * @author hajri
 */
public class DateDebFinException extends Exception {
    private String msg;
    public DateDebFinException(String dateDeb, String dateFin){
        this.msg = "La date debut: " + dateDeb + " est superieure a la date fin: " + dateFin;
    }
    public String getMsg(){
        return  msg;
    }
}
