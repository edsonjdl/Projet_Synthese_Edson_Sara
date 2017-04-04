/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.donneesAffichage;

import java.util.ArrayList;

/**
 *
 * @author edson
 */
public class DonneesAffichageMM extends ArrayList<ValeurMMjour>{
    
        public void ajouterDonneesMM(ValeurMMjour dMM) {
        this.add(dMM);
    }

    public void afficherDonneesMM() {
        for (ValeurMMjour dMM : this) {
            System.out.println(dMM);
        }

    }
    
}
