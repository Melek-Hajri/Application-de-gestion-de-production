/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import projetjava.Model.Production.PlanDeProduction;

/**
 *
 * @author hajri
 */
public class PlanDAO {
    
    public static PlanDeProduction getPlan(){
        try {
            PlanDeProduction plan = new PlanDeProduction(11);
            plan.setLignes(LigneDAO.getAllLignes());
            plan.setOrdres(OrdreDAO.getAllOrdres());
            return plan;
        } catch (SQLException | InvalidPrixException | DateDebFinException ex) {
            Logger.getLogger(PlanDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
