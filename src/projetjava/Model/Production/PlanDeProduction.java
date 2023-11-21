/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

import java.sql.SQLException;
import projetjava.Model.Production.Exceptions.NoLignesDisponiblesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import projetjava.Controller.OrdreDAO;
import projetjava.Model.Production.Exceptions.InvalidDeleteException;

/**
 *
 * @author hajri
 */
public class PlanDeProduction implements Planifiable{
    private int id;
    private List<LigneDeProduction> lignes;
    private List<OrdreDeProduction> ordres;

    public PlanDeProduction(int id) {
        this.id = id;
        lignes = new ArrayList<>();
        ordres = new ArrayList<>();
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public List<LigneDeProduction> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneDeProduction> lignes) {
        this.lignes = lignes;
    }

    public List<OrdreDeProduction> getOrdres() {
        return ordres;
    }

    public void setOrdres(List<OrdreDeProduction> ordres) {
        this.ordres = ordres;
    }
    
    public void ajouterLigneDeProduction(LigneDeProduction ligne) {
        lignes.add(ligne);
    }
    public void retirerLigneDeProduction(LigneDeProduction ligne) throws InvalidDeleteException{
        if (!this.lignes.contains(ligne)) 
            throw new InvalidDeleteException("Cette ligne n'existe pas!");
        lignes.remove(ligne);
    }
    public void ajouterOrdreDeProduction(OrdreDeProduction ordre) {
        ordres.add(ordre);
    }
    public void retirerOrdreDeProduction(OrdreDeProduction ordre) throws InvalidDeleteException{
        if (!this.ordres.contains(ordre)) 
            throw new InvalidDeleteException("Cet ordre n'existe pas!");
        ordres.remove(ordre);
    }
    public int trouverLigneDisponible() throws NoLignesDisponiblesException {
    int index = IntStream.range(0, lignes.size())
            .filter(i -> lignes.get(i).getDisponible())
            .findFirst()
            .orElseThrow(() -> new NoLignesDisponiblesException("Pas de lignes disponibles trouvées."));

    return index;
    }
    public List<LigneDeProduction> getLignesDisponibles() throws NoLignesDisponiblesException{
        List<LigneDeProduction> lignesDisponibles = lignes.stream()
                .filter(LigneDeProduction::getDisponible)
                .collect(Collectors.toCollection(ArrayList::new));

        if (lignesDisponibles.isEmpty()) {
            throw new NoLignesDisponiblesException("Pas de lignes disponibles trouvees!");
        }

        return lignesDisponibles;
    }
    public List<OrdreDeProduction> getOrdresWithNullLigne() throws NoLignesDisponiblesException {
        List<OrdreDeProduction> ordreList = ordres.stream()
                .filter(ordre -> ordre.getLigne() == null)
                .collect(Collectors.toList());
        
        if (ordreList.isEmpty()) {
            throw new NoLignesDisponiblesException("Pas d'ordres non planifies trouves!");
        }
        return ordreList;
    }
    @Override
    public void planifierProduction(OrdreDeProduction ordre) throws NoLignesDisponiblesException {
        int posLigneDispo = trouverLigneDisponible();
        ordre.setLigne(this.lignes.get(posLigneDispo));
        LigneDeProduction ligne = this.lignes.get(posLigneDispo);
        ligne.setDisponible(false);
        
        try {
            OrdreDAO.setLigneToOrdre(ligne.getId(), ordre.getNumero());
        } catch (SQLException ex) {
            Logger.getLogger(PlanDeProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void finirProduction(OrdreDeProduction ordre, StockMatierePremiere stockM, StockProduitsFinis stockF){
        Map<Matiere_premiere, Integer> composants = ordre.getProduit().getComposants();
        for (Map.Entry<Matiere_premiere, Integer> entry : composants.entrySet()) {
            stockM.retirerMatierePremiere(entry.getKey(), entry.getValue());
        }
        stockF.ajouterProduit(ordre.getProduit(), ordre.getQuantite());
        LigneDeProduction ligne = ordre.getLigne();
        ligne.setDisponible(true);
        ordres.remove(ordre);
        
    }

}
