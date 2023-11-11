/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projetjava.Model.Production;

import projetjava.Model.Production.Exceptions.NoLignesDisponiblesException;

/**
 *
 * @author hajri
 */
@FunctionalInterface
public interface Planifiable {
    void planifierProduction(OrdreDeProduction ordre) throws NoLignesDisponiblesException ;
}
