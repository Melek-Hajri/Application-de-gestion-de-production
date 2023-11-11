/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projetjava.Model.Production;

import projetjava.Model.Production.Exceptions.InvalidNegatifException;
import projetjava.Model.Production.Matiere_premiere;

/**
 *
 * @author hajri
 */
@FunctionalInterface
public interface AjoutMatierePremiere {
   void ajouterMatierePremiere(Matiere_premiere mp, int quantite) throws InvalidNegatifException; 
}