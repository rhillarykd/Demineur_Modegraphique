/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mega_demineur_kamenidoudie_delahaye;

/**
 *
 * @author delah
 */
public class Joueur {
    String Nom;
    int HP;
    int NbreKitDeminages;
    int NbreDrapeau;
            
    Joueur(String nom_joueur){
        Nom = nom_joueur;
        NbreKitDeminages = 0;
        NbreDrapeau = 0;
        HP = 3;
    }
     
    void obtenirKitDemi(){
        NbreKitDeminages += 1;
    }
    
    boolean utiliserKitDem(){
        if(NbreKitDeminages==0){
            return false;
        }
        NbreKitDeminages--;
        return true;
    }
    
    boolean PerdreVie (){
        if(HP==0){
            return false;
        }
        HP--;
        return true;
    }
    
    boolean utiliserDrapeau(){
        if(NbreDrapeau==0){
            return false;
        }
        NbreDrapeau--;
        return true;
    }
    
    void reprendreDrapeau() {
        NbreDrapeau++;
    }
}

