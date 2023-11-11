/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class connecterDB {
    public static void  main (String[]arg){
        Connection cnx=connecterDB();
        
    }
    public static Connection connecterDB (){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok ");
            String url="jdbc:mysql://localhost:3306/bd_projetjava";
            String user="root";
            String password="";
            Connection cnx=DriverManager.getConnection(url, user, password);
            System.out.println("connexion bien établiée");
            return cnx;
            
        }
        catch(Exception e){
            System.out.println("probleme de cnx");
            return null;
        }
    }
    
}
