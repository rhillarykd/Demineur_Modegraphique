/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mega_demineur_kamenidoudie_delahaye;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author delah
 */
public class Case_graphique extends JButton {

    Case CaseAssociee;
    ImageIcon img_case = new javax.swing.ImageIcon(getClass().getResource("/images/case.png"));
    ImageIcon img_case_vide = new javax.swing.ImageIcon(getClass().getResource("/images/case_vide.png"));
    ImageIcon img_case_1 = new javax.swing.ImageIcon(getClass().getResource("/images/case_1.png"));
    ImageIcon img_case_2 = new javax.swing.ImageIcon(getClass().getResource("/images/case_2.png"));
    ImageIcon img_case_3 = new javax.swing.ImageIcon(getClass().getResource("/images/case_3.png"));
    ImageIcon img_case_4 = new javax.swing.ImageIcon(getClass().getResource("/images/case_4.png"));
    ImageIcon img_case_5 = new javax.swing.ImageIcon(getClass().getResource("/images/case_5.png"));
    ImageIcon img_kit = new javax.swing.ImageIcon(getClass().getResource("/images/Star_Platinum.png"));
    ImageIcon img_bombe = new javax.swing.ImageIcon(getClass().getResource("/images/Killer_Queen_Bomb.png"));
    ImageIcon img_heart = new javax.swing.ImageIcon(getClass().getResource("/images/Vie.png"));
    //ImageIcon img_broken_heart = new javax.swing.ImageIcon(getClass().getResource("/images/broken_heart.png"));
    ImageIcon img_drapeau = new javax.swing.ImageIcon(getClass().getResource("/images/one_piece_mugiwara.jpg"));

    public Case_graphique(Case uneCase) {
        CaseAssociee = uneCase;
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        
        if (CaseAssociee.CaseDecouverte() != true) {
            setIcon(img_case);
        } else {
            // on attribut l'image par defaut par l'image case
            if (CaseAssociee.presenceDrapeau() == true) {
                setIcon(img_drapeau);
                //setIcon(img_case);// on attribut l'image du drapeau s il y a la presence de ce dernier
            } else if (CaseAssociee.Bomb == true) {
                setIcon(img_bombe);
                //setIcon(img_case);
            } else if (CaseAssociee.presenceKitDeminages() == true) {
                setIcon(img_kit);
                //setIcon(img_case);
            } else if (CaseAssociee.NbreBombAutour() == 1) {
                setIcon(img_case_1);
                //setIcon(img_case);
            } else if (CaseAssociee.NbreBombAutour() == 2) {
                setIcon(img_case_2);
                //setIcon(img_case);
            } else if (CaseAssociee.NbreBombAutour() == 3) {
                setIcon(img_case_3);
                // setIcon(img_case);
            } else if (CaseAssociee.NbreBombAutour() == 4) {
                setIcon(img_case_4);
                //setIcon(img_case);
            } else if (CaseAssociee.NbreBombAutour() == 5) {
                setIcon(img_case_5);
                //setIcon(img_case);
            } else if (CaseAssociee.NbreBombAutour() == 10 && CaseAssociee.presenceBomb() == false && CaseAssociee.presenceKitDeminages() == false && CaseAssociee.presenceDrapeau() == false) {
                setIcon(img_case_vide);
            }
        }
    }
}
