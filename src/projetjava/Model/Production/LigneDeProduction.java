/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

import java.util.ArrayList;
import projetjava.Model.Gestion.Operateur;
import projetjava.Model.Production.Exceptions.InvalidDeleteException;

/**
 *
 * @author hajri
 */
public class LigneDeProduction {
    private int id;
    private String nom;
    private ArrayList<Machine> machines;
    private ArrayList<Operateur> operateurs;
    private boolean disponible;
    public LigneDeProduction(String nom) {
        this.nom = nom;
        this.machines = new ArrayList<>();
        this.operateurs = new ArrayList<>();
        this.disponible = true;
    }
    public LigneDeProduction(int id, String nom, boolean disponible) {
        this.id = id;
        this.nom = nom;
        this.machines = new ArrayList<>();
        this.operateurs = new ArrayList<>();
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void ajouterMachine(Machine machine){
        this.machines.add(machine);
    }
    public void retirerMachine(Machine machine) throws InvalidDeleteException{
        if (!this.machines.contains(machine)) 
            throw new InvalidDeleteException("Cette machine n'existe pas dans cette ligne!");
        this.machines.remove(machine);
    }
    public void ajouterOperateur(Operateur operateur){
        this.operateurs.add(operateur);
    }
    public void retirerOperateur(Operateur operateur) throws InvalidDeleteException{
        if (!this.operateurs.contains(operateur)) 
            throw new InvalidDeleteException("Cet operateur n'est pas dans cette ligne!");
        this.operateurs.remove(operateur);
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNom(){
        return nom;
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    public boolean getDisponible(){
        return disponible;
    }
    public ArrayList<Machine> getMachines(){
        return machines;
    }
    public void setMachines(ArrayList<Machine> machines){
        this.machines = machines;
    }
    public ArrayList<Operateur> getOperateurs(){
        return operateurs;
    }
    public void setOperateurs(ArrayList<Operateur> operateurs){
        this.operateurs = operateurs;
    }
    
    public void verifierDisponible(){
        for(Machine m : machines){
            if(m.getEnMaintenance()){
                this.disponible = false;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "LigneDeProduction{" + "id=" + id + ", nom=" + nom + ", machines=" + machines + ", operateurs=" + operateurs + ", disponible=" + disponible + '}';
    }
    
}
