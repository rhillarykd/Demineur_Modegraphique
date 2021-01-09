/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mega_demineur_kamenidoudie_delahaye;

import java.util.Random;
//import java.util.Scanner;

//import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Image;
//import java.awt.event.MouseListener;

/**
 *
 * @author delah
 */
public class fenetre_jeu extends javax.swing.JFrame {

    Joueur Joueur;
    Grille GrilleJeu = new Grille();
    Joueur Listejoueurs[] = new Joueur[1];
    int NombreTtBomb=0;//Nombre de bombes au total
    
    ImageIcon img_broken_heart = new javax.swing.ImageIcon(getClass().getResource("/images/broken_heart.png"));
    //int nbrestantbomb;

    /**
     * Creates new form fenetre_jeu
     */
    public fenetre_jeu() {
        initComponents();
        panneau_grille.setVisible(false);
        info_joueur.setVisible(false);
        infos_partie.setVisible(false);
        
        
        for (int i = 19; i >= 0; i--) {
            for (int j = 0; j < 20; j++) {
                Case_graphique case_graph = new Case_graphique(GrilleJeu.TabCase[i][j]);
                
                //case_graph.addMouseListener(new java.awt.event.MouseListener() {
                case_graph.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        Case Case_choisie = case_graph.CaseAssociee;
                        if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                            //System.out.println("click gauche");
                            if (Joueur.utiliserKitDem() == true){
                                //if (Joueur.utiliserKitDem() == true && Case_choisie.activerKitDeminages() == true)
                                Case_choisie.Visible = true;
                                if (Case_choisie.presenceBomb() == true) {
                                    message.setText(Joueur.Nom +" a désamorcé une bombe en utilisant un kit");
                                    NombreTtBomb--;
                                    Nb_Bombes.setText(NombreTtBomb+"");
                                    if (etreGagnantePourJoueur(Joueur) == true) {
                                        panneau_grille.setVisible(false);
                                        return;
                                    }
                                }
                                if (Case_choisie.BombNumber != 1 || Case_choisie.BombNumber != 2 || Case_choisie.BombNumber != 3 || Case_choisie.BombNumber != 4 || Case_choisie.BombNumber != 5) {
                                    Case_choisie.BombNumber = 10;
                                }
                                Kit_deminage.setText(Joueur.NbreKitDeminages+"");
                                //Case_choisie.BombNumber = 10;
                                return;
                            }
                            if (Case_choisie.presenceBomb() == false && Case_choisie.presenceDrapeau() == false && Case_choisie.presenceKitDeminages()== false){
                                if (Case_choisie.BombNumber != 1 && Case_choisie.BombNumber != 2 && Case_choisie.BombNumber != 3 && Case_choisie.BombNumber != 4 && Case_choisie.BombNumber != 5) {
                                    Case_choisie.BombNumber = 10;
                                }
                                if (etreGagnantePourJoueur(Joueur) == true) {
                                    panneau_grille.setVisible(false);
                                    return;
                                }
                                Case_choisie.Visible = true;
                                return;
                            }
                            if (Case_choisie.recupererDrapeau() == true){
                                //if (Case_choisie.presenceDrapeau() == true && Case_choisie.recupererDrapeau() == true)
                                Joueur.reprendreDrapeau();
                                Nb_Drapeau.setText(Joueur.NbreDrapeau+"");
                                Case_choisie.Visible = false;
                                return;
                            }
                            if (Case_choisie.presenceBomb() == true){
                                //Bbnumber.setText(Case_choisie.Bomb+"");
                                /*if(Joueur.NbreKitDeminages > 0){
                                    Case_choisie.activerKitDeminages();
                                    Joueur.utiliserKitDem();
                                    message.setText("le joueur"+ Joueur.Nom +" a utilisé un kit");
                                    Kit_deminage.setText(Joueur.NbreKitDeminages+"");
                                } else{*/
                                if (Joueur.PerdreVie() == true) {
                                    //Case_choisie.BombNumber = 10;
                                    Vie.setText(Joueur.HP+"");
                                    Case_choisie.Visible = true;
                                    message.setText(Joueur.Nom +" a touché une bombe et perdu une vie");
                                    NombreTtBomb--;
                                    Nb_Bombes.setText(NombreTtBomb+"");
                                } else {
                                    Case_choisie.Visible = true;
                                    message.setText(Joueur.Nom +" a perdu");
                                    panneau_grille.setVisible(false);
                                    Vie.setIcon(img_broken_heart);
                                    //Vie.setIcon(getScaledIcon(img_broken_heart+"", 100, 100));
                                    //Vie.setIcon(getScaledIcon("/images/broken_heart.png", 100, 100));
                                    //Vie.setImage(img_broken_heart.getImage().getScaledInstance(1,1,Image.SCALE_FAST));
                                    return;
                                }
                                return;
                            }
                            if (Case_choisie.recupererKitDeminages() == true) {
                                //if (Case_choisie.presenceKitDeminages() == true && Case_choisie.recupererKitDeminages() == true)
                                Joueur.obtenirKitDemi();
                                Kit_deminage.setText(Joueur.NbreKitDeminages+"");
                                message.setText(Joueur.Nom +" a trouvé un kit et l'a activé \nClickez sur une case non découverte pour l'utiliser");
                                Case_choisie.Visible = true;
                                if (Case_choisie.BombNumber != 1 || Case_choisie.BombNumber != 2 || Case_choisie.BombNumber != 3 || Case_choisie.BombNumber != 4 || Case_choisie.BombNumber != 5) {
                                    Case_choisie.BombNumber = 10;
                                }
                                if (etreGagnantePourJoueur(Joueur) == true) {
                                    panneau_grille.setVisible(false);
                                    return;
                                }
                                
                            }
                        }
                        if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                            //System.out.println("click droit");
                            if(Case_choisie.placerDrapeau() == true && Joueur.utiliserDrapeau() == true) {
                                Nb_Drapeau.setText(Joueur.NbreDrapeau+"");
                                message.setText(Joueur.Nom +" a placé un drapeau");
                            }
                            Case_choisie.Visible = true;
                        }
                    }
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        // TODO add your handling code here:
                    }
                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent e) {
                        // TODO add your handling code here:
                    }
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        // TODO add your handling code here:
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        // TODO add your handling code here:
                    }
                });
                /*
                case_graph.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Case Case_choisie = case_graph.CaseAssociee;
                        
                        
                    }
                });
                */
                
                panneau_grille.add(case_graph);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panneau_création_partie = new javax.swing.JPanel();
        Nom_joueur = new javax.swing.JLabel();
        NomDuJoueur = new javax.swing.JTextField();
        Bouton_Partie = new javax.swing.JButton();
        info_joueur = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nom_du_joueur = new javax.swing.JLabel();
        Kit_deminage = new javax.swing.JLabel();
        Vie = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Nb_Drapeau = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Bbnumber = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Nb_Bombes = new javax.swing.JLabel();
        infos_partie = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        panneau_grille = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panneau_création_partie.setBackground(new java.awt.Color(204, 204, 255));

        Nom_joueur.setText("Nom du Joueur:");

        NomDuJoueur.setText(" ");
        NomDuJoueur.setToolTipText("");

        Bouton_Partie.setText("Press to start");
        Bouton_Partie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bouton_PartieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneau_création_partieLayout = new javax.swing.GroupLayout(panneau_création_partie);
        panneau_création_partie.setLayout(panneau_création_partieLayout);
        panneau_création_partieLayout.setHorizontalGroup(
            panneau_création_partieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneau_création_partieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_joueur, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NomDuJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneau_création_partieLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bouton_Partie)
                .addGap(55, 55, 55))
        );
        panneau_création_partieLayout.setVerticalGroup(
            panneau_création_partieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneau_création_partieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panneau_création_partieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nom_joueur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NomDuJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Bouton_Partie, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(panneau_création_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 210, 110));

        info_joueur.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("Infos joueur ");

        jLabel2.setText("Nom du Joueur:");

        jLabel3.setText("Vie :");

        jLabel4.setText("Nombre de Kit :");

        Nom_du_joueur.setText("Nom");

        Kit_deminage.setText("Nb_Kit ");

        Vie.setText("Nb_Vie");

        jLabel6.setText("Nombre de drapeaux :");

        Nb_Drapeau.setText("Nombre_drapeau");

        jLabel8.setText("Nombre de bombes :");

        Nb_Bombes.setText("Nombre_bomb");

        javax.swing.GroupLayout info_joueurLayout = new javax.swing.GroupLayout(info_joueur);
        info_joueur.setLayout(info_joueurLayout);
        info_joueurLayout.setHorizontalGroup(
            info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_joueurLayout.createSequentialGroup()
                .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, info_joueurLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1))
                    .addGroup(info_joueurLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(info_joueurLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info_joueurLayout.createSequentialGroup()
                        .addComponent(Nom_du_joueur, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(info_joueurLayout.createSequentialGroup()
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(info_joueurLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addGroup(info_joueurLayout.createSequentialGroup()
                                .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Vie)
                                    .addComponent(Kit_deminage))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bbnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(info_joueurLayout.createSequentialGroup()
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nb_Drapeau)
                            .addComponent(Nb_Bombes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        info_joueurLayout.setVerticalGroup(
            info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_joueurLayout.createSequentialGroup()
                .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info_joueurLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Bbnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(info_joueurLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Nom_du_joueur))
                        .addGap(18, 18, 18)
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Vie))
                        .addGap(18, 18, 18)
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Kit_deminage))
                        .addGap(18, 18, 18)
                        .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Nb_Drapeau))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(info_joueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(Nb_Bombes))
                .addGap(8, 8, 8))
        );

        getContentPane().add(info_joueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 230, 210, 210));

        infos_partie.setBackground(new java.awt.Color(255, 204, 102));

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel5.setText("Infos jeu");

        message.setColumns(20);
        message.setRows(5);
        jScrollPane1.setViewportView(message);

        javax.swing.GroupLayout infos_partieLayout = new javax.swing.GroupLayout(infos_partie);
        infos_partie.setLayout(infos_partieLayout);
        infos_partieLayout.setHorizontalGroup(
            infos_partieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infos_partieLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(infos_partieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        infos_partieLayout.setVerticalGroup(
            infos_partieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infos_partieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(infos_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 480, 210, 150));

        panneau_grille.setBackground(new java.awt.Color(204, 204, 204));
        panneau_grille.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panneau_grilleMouseReleased(evt);
            }
        });
        panneau_grille.setLayout(new java.awt.GridLayout(20, 20));
        getContentPane().add(panneau_grille, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 880, 900));

        setBounds(0, 0, 1205, 999);
    }// </editor-fold>//GEN-END:initComponents

    private void Bouton_PartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bouton_PartieActionPerformed
        // TODO add your handling code here:
        panneau_grille.setVisible(true);
        panneau_création_partie.setVisible(false);
        info_joueur.setVisible(true);
        infos_partie.setVisible(true);
        initialiserPartie();
        panneau_grille.repaint();
        Bouton_Partie.setEnabled(false);
        
    }//GEN-LAST:event_Bouton_PartieActionPerformed

    private void panneau_grilleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneau_grilleMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger() == true) {
            info_joueur.setVisible(false);
            //Ne marche pas
            /*
            if (Case_choisie.placerDrapeau() == true){
                //Pas sure, à revoir
                // if (Case_choisie.presenceDrapeau()==true)
                //Nb_Drapeau.setText(Joueur.NbreDrapeau+"");
            }
            */
        }
    }//GEN-LAST:event_panneau_grilleMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fenetre_jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fenetre_jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fenetre_jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fenetre_jeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new fenetre_jeu().setVisible(true);
            }
        });
    }
    
    boolean etreGagnantePourJoueur(Joueur un_joueur) {
        if (un_joueur.HP != 0 && un_joueur.NbreDrapeau == 0) {
            return true;
        }
        return false;
    }
    
    void initialiserPartie() {
        Random r = new Random();
        //Mise en place de la grille
        GrilleJeu.viderGrille();
        
        //Initialisation du nombre total de bombes
        NombreTtBomb = 40;
        
        //Identification du joueur
        String nomJoueur= NomDuJoueur.getText();
        Joueur = new Joueur(nomJoueur);
        Listejoueurs[0] = Joueur;
        //System.out.println(Joueur.Nom);
       
        // On donne des drapeaux au joueur
        Joueur.NbreDrapeau = NombreTtBomb;
         
        // On donne des Kits de Déminages au joueur
        //Joueur.NbreKitDeminages = NombreTtBomb%3 + 1;
        Nom_du_joueur.setText(nomJoueur);
        Vie.setText(Joueur.HP+"");
        Kit_deminage.setText(Joueur.NbreKitDeminages+"");
        Nb_Drapeau.setText(Joueur.NbreDrapeau+"");
        
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
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bbnumber;
    private javax.swing.JButton Bouton_Partie;
    private javax.swing.JLabel Kit_deminage;
    private javax.swing.JLabel Nb_Bombes;
    private javax.swing.JLabel Nb_Drapeau;
    private javax.swing.JTextField NomDuJoueur;
    private javax.swing.JLabel Nom_du_joueur;
    private javax.swing.JLabel Nom_joueur;
    private javax.swing.JLabel Vie;
    private javax.swing.JPanel info_joueur;
    private javax.swing.JPanel infos_partie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea message;
    private javax.swing.JPanel panneau_création_partie;
    private javax.swing.JPanel panneau_grille;
    // End of variables declaration//GEN-END:variables
}
