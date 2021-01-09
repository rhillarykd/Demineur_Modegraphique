/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mega_demineur_kamenidoudie_delahaye;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author delah
 */
public class Partie {
    Joueur Joueur;
    Grille GrilleJeu= new Grille();
    Joueur Listejoueurs[] = new Joueur[1];
    int NombreTtBomb;//Nombre de bombes au total
    
    
    void initialiserPartie() {
        Random r = new Random();
        //Mise en place de la grille
        GrilleJeu.viderGrille();
        
        //Initialisation du nombre total de bombes
        NombreTtBomb = 40;
        
        //Identification du joueur
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre pseudo : ");
        Joueur = new Joueur(sc.nextLine());
        Listejoueurs[0] = Joueur;
        //System.out.println(Joueur.Nom);
        
        // On donne des drapeaux au joueur
        Joueur.NbreDrapeau = NombreTtBomb;
        
        // On donne des Kits de Déminages au joueur
        //Joueur.NbreKitDeminages = NombreTtBomb%3 + 1;
        
        // Génération aléatoire de bombes et de kit de déminages
        // On place les bombes
        Random nAlea = new Random();//r pour random
        for (int nBomb = 1; nBomb <= 40; nBomb++) {
            int iAlea = nAlea.nextInt(20);
            int jAlea = nAlea.nextInt(20);
            if (GrilleJeu.placer_Bomb(iAlea,jAlea) != true) {
                nBomb--;
            }
        }
        // On place les Kits de Déminages
        for (int nKitDeminages = 1; nKitDeminages <= NombreTtBomb%3 + 1; nKitDeminages++) {
            int iAlea = nAlea.nextInt(20);
            int jAlea = nAlea.nextInt(20);
            if (GrilleJeu.TabCase[iAlea][jAlea].presenceBomb() == true || (GrilleJeu.TabCase[iAlea][jAlea].presenceBomb() != true && GrilleJeu.placer_KitDeminages(iAlea,jAlea) != true)) {
                nKitDeminages--;
            }
        }
        GrilleJeu.IncrementeBombNumberGrille();
        GrilleJeu.afficherGrilleSurConsole();
    }
    
    int menu_joueur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Faites votre choix !");
        System.out.println("1) Découvrir une case \n2) Placer un drapeau \n3) Récuperer un drapeau \n4) Utiliser un kit de déminages \n ");
        int choix = sc.nextInt();
        while (choix > 4 || choix < 1) {
            System.out.println("Choix invalide, recommencez");
            choix = sc.nextInt();
        }
        return choix;
    }
    
    int Choix_ligne() {
        Scanner s = new Scanner(System.in);
        System.out.println("Veuillez saisir une ligne :");
        int i = s.nextInt();
        while (i > 19 || i < 0) {
            System.out.println("Choix invalide, recommencez : ");
            i = s.nextInt();
        }
        return i;
    }
    
    int Choix_colonne() {
        Scanner s = new Scanner(System.in);
        System.out.println("Veuillez saisir une colonne :");
        int j = s.nextInt();
        while (j > 19 || j < 0) {
            System.out.println("Choix invalide, recommencez : ");
            j = s.nextInt();
        }
        return j;
    }
    
    boolean decouvreCase(int i, int j) {
        //GrilleJeu.Choisir_Case();
        //int i = Choix_ligne();
        //int j = Choix_colonne();
        
        if (GrilleJeu.activer_Bomb(i, j) == true) {
            GrilleJeu.IncrementeBombNumberCase(i, j);
            if (Joueur.PerdreVie() != true) {
                return false;//Le joueur n'a plus de vie et perd la partie
            } else {
                return true;
            }
        }
        if (GrilleJeu.recupere_Kit(i, j) == true) {
            Joueur.obtenirKitDemi();
        }
        return true;
    }
    
    boolean utiliseDrapeau(int i, int j) {//même que placer_drapeau()
        if (Joueur.NbreDrapeau == 0) {
            return false;
        } else {
            if (Joueur.utiliserDrapeau() == true && GrilleJeu.placer_Drapeau(i, j) == true) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    boolean recupereDrapeau(int i, int j) {
        if (Joueur.NbreDrapeau == 40) {
            return false;
        } else {
            if (GrilleJeu.recupere_Drapeau(i, j) == true) {
                Joueur.reprendreDrapeau();
                return true;
            } else {
                return false;
            }
        }
    }
    
    boolean utiliseKit(int i, int j) {
        if (Joueur.NbreKitDeminages == 0) {
            return false;
        } else {
            if (GrilleJeu.TabCase[i][j].Bomb == true) {
                Joueur.utiliserKitDem();
                GrilleJeu.TabCase[i][j].Bomb = false;
                return true;
            } else {
                if (GrilleJeu.recupere_Kit(i, j) == true) {
                    Joueur.obtenirKitDemi();
                    return true;
                }
                return false;
            }
        }
    }
    
    
    void debuterPartie() {
        initialiserPartie();
        boolean resultatC1 = true;
        boolean resultatAction = false;
        do {
            System.out.println("\n " + Joueur.Nom + ", jouez ");
            System.out.println(" - Il vous reste " + Joueur.NbreDrapeau + " Drapeaux");
            System.out.println(" - Il vous reste " + Joueur.NbreKitDeminages + " Kits de Déminages");
            System.out.println(" - Il vous reste " + Joueur.HP + " points de vie");
            
            while (resultatAction == false) {
                int choix = menu_joueur();
                int i = Choix_ligne();
                int j = Choix_colonne();
                switch (choix) {
                    case 1:
                        if (decouvreCase(i,j) == false) {//Le joueur a perdu, il n'a plus de points de vie
                            resultatC1 = false;
                        } else {
                            resultatC1 = true;
                        }
                        resultatAction = true;
                        break;
                    case 2:
                        if (utiliseDrapeau(i,j) == false) {
                            System.out.println("\nVous avez choisi une case découverte ou bien vous n'avez pas de drapeaux");
                            resultatAction = false;
                        //return resultat;
                        } else {
                            resultatAction = true;
                        }
                        break;
                    case 3:
                        if (recupereDrapeau(i,j) == false) {
                            System.out.println("\nVous avez choisi une case sans drapeau");
                            resultatAction = false;
                        //return resultat;
                        } else {
                            resultatAction = true;
                        }
                        break;
                    case 4:
                        if (utiliseKit(i,j) == false) {
                            System.out.println("\nVous avez choisi une case ayant été découverte ou ayant un drapeau ou bien n'avez pas de kit de déminages");
                            resultatAction = false;
                        //return resultat;
                        } else {
                            resultatAction = true;
                        }
                        break;
                }
            }
            if (resultatC1 == false) {
                break;
            }
            if (Joueur.HP == 0) {
                break;
            }
            resultatAction = false;
            GrilleJeu.afficherGrilleSurConsole();
        } while (GrilleJeu.etreGagnantePourJoueur(Listejoueurs[0]) != true);

        if (GrilleJeu.etreGagnantePourJoueur(Listejoueurs[0]) == true) {
            System.out.println(" Félicitations ! ");
        } else {
            System.out.println("Vous ferez mieux la prochaine fois :| ");
        }
    }
}
