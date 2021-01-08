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
import java.util.Scanner;

public class Grille {

    Case TabCase[][] = new Case[20][20];
    //int NombreTtBomb;

    Grille() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                TabCase[i][j] = new Case();
            }
        }
    }

    Scanner sc;
    
    /*
    Case Choisir_Case() {
        sc = new Scanner(System.in);
        System.out.println("Veuillez saisir une ligne :");
        int i = sc.nextInt();
        while (i > 19 || i < 0) {
            System.out.println("Choix invalide, recommencez : ");
            i = sc.nextInt();
        }
        System.out.println("Veuillez saisir une colonne :");
        int j = sc.nextInt();
        while (j > 19 || j < 0) {
            System.out.println("Choix invalide, recommencez : ");
            j = sc.nextInt();
        }
        return TabCase[i][j];
    }
    */

    boolean etreGagnantePourJoueur(Joueur un_joueur) {
        if (un_joueur.HP != 0 && un_joueur.NbreDrapeau == 0) {
            return true;
        }
        return false;
    }

    void viderGrille() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                TabCase[i][j].Bomb = false;
                TabCase[i][j].KitDeminages = false;
                TabCase[i][j].Drapeau = false;
                TabCase[i][j].BombNumber = 0;
            }
        }
    }

    boolean activer_Bomb(int ligne, int colonne) { // boolean
        /*
        int i = 20;
        int j = 20;
        while (TabCase[ligne][colonne].activerBomb()== false) {
        
            // perdre vie aussi
            i--;
            j--;
            if (i == 0 && j == 0) {
             break;  
            }
            
            
        }
        if (i >= 0 && i < 20) {
            TabCase[ligne][colonne].activerBomb();
        }
        return true;// je ne sais pas
        */
        if (TabCase[ligne][colonne].activerBomb() != true) {
            return false;
        }
        return true;
    }

    Boolean placer_Drapeau(int ligne, int colonne) {
        if (!TabCase[ligne][colonne].Drapeau) {
            TabCase[ligne][colonne].Drapeau = true;
            return true;
        }
        return false;
        //return TabCase[ligne][colonne].placerDrapeau();
    }
    
    Boolean placer_KitDeminages(int ligne, int colonne) {
        if (!TabCase[ligne][colonne].KitDeminages) {
            TabCase[ligne][colonne].KitDeminages = true;
            return true;
        }
        return false;
        //return TabCase[ligne][colonne].placerKitDeminages();
    }
    
    Boolean placer_Bomb(int ligne, int colonne) {
        if (!TabCase[ligne][colonne].Bomb) {
            TabCase[ligne][colonne].Bomb = true;
            return true;
        }
        return false;
        //return TabCase[ligne][colonne].placerBomb();
    }
    
    boolean presence_KitDeminages(int i, int j) {
        if (TabCase[i][j].presenceKitDeminages() == false) {
            return false;
        }
        return true;
    }
    
    boolean recupere_Kit(int ligne, int colonne){
        if (presence_KitDeminages(ligne, colonne) == false) {
            return false;  
        }
        TabCase[ligne][colonne].KitDeminages = false;
        return true;
    }
    
    boolean presence_Drapeau(int i, int j) {
        if (TabCase[i][j].presenceDrapeau() == false) {
            return false;
        }
        return true;
    }
    
    boolean recupere_Drapeau(int ligne, int colonne) {
        if (presence_Drapeau(ligne, colonne) == false){
            return false;  
        }
        TabCase[ligne][colonne].Drapeau = false;
        return true;
    }
    
    /*
    int NbreBombAutour(){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                //TabCase[i][j].NbreBombAutour()++;
              if (TabCase[i][j].presenceBomb()==true){
                  int BombNumber=0;
                  System.out.println(BombNumber++)   ;//A revoir car bombnumber pas sure
              }  
            }
        }
    }*/
    
    int NbreBombAutour(int ligne, int colonne) {
        return TabCase[ligne][colonne].NbreBombAutour();
    }
    
    void IncrementeBombNumberCase(int i, int j) {
        if (i == 0) {
            if (j == 0) {
                if (TabCase[i][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            } else if (j == 19) {
                if (TabCase[i][j-1].presenceBomb()==true) {
                        TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            } else {
                if (TabCase[i][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j-1].presenceBomb()==true) {
                        TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            }
            if (TabCase[i+1][j].presenceBomb()==true) {
                TabCase[i][j].BombNumber++;
            }
        } else if (i == 19) {
            if (j == 0) {
                if (TabCase[i][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i-1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            } else if (j == 19) {
                if (TabCase[i-1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            } else {
                if (TabCase[i][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i-1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i-1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            }
            if (TabCase[i-1][j].presenceBomb()==true) {
                TabCase[i][j].BombNumber++;
            }
        } else {
            if (j==0) {
                if (TabCase[i-1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            } else if (j==19) {
                if (TabCase[i-1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            } else {
                if (TabCase[i-1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j+1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i-1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                } if (TabCase[i+1][j-1].presenceBomb()==true) {
                    TabCase[i][j].BombNumber++;
                }
            }
            if (TabCase[i-1][j].presenceBomb()==true) {
                TabCase[i][j].BombNumber++;
            } if (TabCase[i+1][j].presenceBomb()==true) {
                TabCase[i][j].BombNumber++;
            }
        }
    }
    
    void IncrementeBombNumberGrille() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                IncrementeBombNumberCase(i,j);
            }
        }
        
        /*
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (TabCase[i][j] != null && Joueur.HP > 0;
                 && TabCase[i][j + 1] != null && Joueur.HP > 0;
                 && TabCase[i][j + 2] != null && Case[i][j + 2].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && TabCase[i][j + 3] != null && Case[i][j + 3].lireCouleurDuJeton().equals(un_joueur.Couleur)
                
                    ) {
                    return true;
                }

            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (Case[i][j] != null && Case[i][j].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i + 1][j] != null && Case[i + 1][j].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i + 2][j] != null && Case[i + 2][j].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i + 3][j] != null && Case[i + 3][j].lireCouleurDuJeton().equals(un_joueur.Couleur)) {
                    return true;
                }

            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (Case[i][j] != null && Case[i][j].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i + 1][j + 1] != null && Case[i + 1][j + 1].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i + 2][j + 2] != null && Case[i + 2][j + 2].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i + 3][j + 3] != null && Case[i + 3][j + 3].lireCouleurDuJeton().equals(un_joueur.Couleur)) {
                    return true;
                }

            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (Case[i][j] != null && Case[i][j].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i - 1][j + 1] != null && Case[i - 1][j + 1].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i - 2][j + 2] != null && Case[i - 2][j + 2].lireCouleurDuJeton().equals(un_joueur.Couleur)
                        && Case[i - 3][j + 3] != null && Case[i - 3][j + 3].lireCouleurDuJeton().equals(un_joueur.Couleur)) {
                    return true;
                }

            }
        }
        return false;
        */
    }
    
    void afficherGrilleSurConsole() {
        // boucle inversée : on affiche d'abord la ligne du haut
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (TabCase[i][j].Drapeau == true) {
                    System.out.print("D  ");
                } else {
                    if (TabCase[i][j].Bomb == true) {
                        System.out.print("B  ");
                    } else if (TabCase[i][j].KitDeminages == true) {
                        System.out.print("K  ");
                    } else {
                        System.out.print(TabCase[i][j].BombNumber+"  ");
                    }
                }
            }
            System.out.println("  " + i);
        }
        System.out.print("\n");
        for (int j = 0; j < 20; j++) {
            System.out.print(j + "  ");
        }
        //System.out.println();
    }
    
}
