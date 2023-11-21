///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package projetjava.Model.Production;
//
//import projetjava.Model.Production.Exceptions.InvalidDeleteException;
//import projetjava.Model.Production.Exceptions.InvalidNegatifException;
//import projetjava.Model.Production.Exceptions.InvalidPrixException;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author hajri
// */
//public class miniprojet1 {
//    public static void main(String[] args) {
//        try {
//            Produit p = new Produit("p1","d1",20);
//            System.out.println(p.getNom()+p.getDescription()+p.getPrix());
//            Matiere_premiere mp1 = new Matiere_premiere("mp1", 5);
//            Matiere_premiere mp2 = new Matiere_premiere("mp2", 6);
//            System.out.println(mp1.toString()+" "+mp2.toString());
//            p.ajouterMatierePremiere(mp1, 2);
//            p.ajouterMatierePremiere(mp2, 3);
//            Map<Matiere_premiere, Integer> composants = p.getComposants();
//            for (Map.Entry<Matiere_premiere, Integer> entry : composants.entrySet()) {
//                System.out.println(entry.getKey()+": "+entry.getValue());
//            }
//            p.retirerMatierePremiere(mp2);
//        } catch (InvalidPrixException ex) {
//            System.out.println(ex.getMsg());
//        } catch (InvalidNegatifException ex) {
//            System.out.println(ex.getMsg());
//        } catch (InvalidDeleteException ex) {
//            System.out.println(ex.getMsg());
//        }
//    }
//}
