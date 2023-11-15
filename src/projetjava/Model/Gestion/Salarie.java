/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package projetjava.Model.Gestion;

/**
 *
 * @author admin
 */
public class Salarie implements calculsalaire {
    String nom;
    String prenom; // Ajout de l'attribut prénom
  String id; // Ajout de l'attribut ID
   String adresseEmail; // Ajout de l'attribut adresse email
     String numeroTelephone;
     double age;    

     public Salarie(String nom, String prenom, String id, String adresseEmail, String numeroTelephone,double age) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.adresseEmail = adresseEmail;
        this.numeroTelephone = numeroTelephone;
        this.age=age;
    }

    public String getNom() {
        return nom;
    }
    public double getAge(){
        return age;
    }
    public void setAge(double age){
        this.age=age;
    }

   
    

public void setNom(String nom) {
    this.nom = nom;
}
public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
    @Override
    public double calculSalaire() {
        calculsalaire salaireCalcul = () -> {return 0.0; 
                };
        return salaireCalcul.calculSalaire();
                }

    @Override
    public String toString() {
        return "Salarie{" + "nom=" + nom + ", prenom=" + prenom + ", id=" + id + ", adresseEmail=" + adresseEmail + ", numeroTelephone=" + numeroTelephone + ", age=" + age + '}';
    }
    

}




    
