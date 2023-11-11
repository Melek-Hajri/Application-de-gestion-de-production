/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

import projetjava.Model.Production.Exceptions.InvalidDeleteException;
import projetjava.Model.Production.Exceptions.NoLignesDisponiblesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
            .orElseThrow(() -> new NoLignesDisponiblesException("No available production lines found."));

    return index;
    }
    public List<LigneDeProduction> getLignesDisponibles() throws NoLignesDisponiblesException{
    List<LigneDeProduction> lignesDisponibles = lignes.stream()
            .filter(LigneDeProduction::getDisponible)
            .collect(Collectors.toCollection(ArrayList::new));

    if (lignesDisponibles.isEmpty()) {
        throw new NoLignesDisponiblesException("Pas de lignes disponibles trouvÃ©es!");
    }

    return lignesDisponibles;
}
    @Override
    public void planifierProduction(OrdreDeProduction ordre) throws NoLignesDisponiblesException {
        int posLigneDispo = trouverLigneDisponible();
        ordre.setLigne(this.lignes.get(posLigneDispo));
        this.lignes.get(posLigneDispo).setDisponible(false);
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
