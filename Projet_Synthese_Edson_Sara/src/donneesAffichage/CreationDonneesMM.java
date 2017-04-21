/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donneesAffichage;

import indicateur.MoyenneMobile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import modele.ListeCotisations;

/**
 *
 * @author edsonjl
 */
public class CreationDonneesMM {

    private static MoyenneMobile mmRapide;
    private static MoyenneMobile mmLente;
    private static ValeurMMjour mmJour;

    public static void genererMM(ListeCotisations listeCotisations, int periodeRapide, int periodeLent, DonneesAffichageMM donneesMM) {
        /*
        Je dois limiter la fin de la bascule parce que après listeCotisations.size()-N_PERIODES
        il n'aura plus d'éléments pour calculer les moyennes
        
         */
        for (int i = periodeLent; i < listeCotisations.size(); i++) {

            //System.out.println("Donnéees de la date: " + listeCotisations.get(i).getDate().get(Calendar.DAY_OF_MONTH) + "-" + (listeCotisations.get(i).getDate().get(Calendar.MONTH) + 1) + "-" + listeCotisations.get(i).getDate().get(Calendar.YEAR));
            // Calcul moyenneLente rapide
            double sommeFermeturesR = 0;
            for (int j = i; j > i - periodeRapide; j--) {
                sommeFermeturesR += listeCotisations.get(j).getFermeture();
            }
            double moyenneRapide = ((int) ((sommeFermeturesR / periodeRapide) * 100)) / 100.0;

            // Calcul moyenneLente lente
            double sommeFermeturesL = 0;
            for (int k = i; k > i - periodeLent; k--) {
                sommeFermeturesL += listeCotisations.get(k).getFermeture();
            }

            double moyenneLente = ((int) ((sommeFermeturesL / periodeLent) * 100)) / 100.0;
            System.out.println(listeCotisations.get(i));
            System.out.println("moyenne rapide: " + moyenneRapide);
            System.out.println("moyenne lente: " + moyenneLente + "\n");

//            mmRapide = new MoyenneMobile(listeCotisations.get(i).getDate(),moyenneRapide);
//            mmLente = new MoyenneMobile(listeCotisations.get(i).getDate(),moyenneLente);
            mmRapide = new MoyenneMobile(moyenneRapide);
            mmLente = new MoyenneMobile(moyenneLente);

            mmJour = new ValeurMMjour(listeCotisations.get(i), mmLente, mmRapide);
            donneesMM.add(mmJour);
        }
    }
}
//    private ListeCotisations listeCotisations;

//    private MoyenneMobile mmLente;
//    private MoyenneMobile mmRapide;
//    
//    private ValeurMMjour mmJour;
//    private DonneesAffichageMM donneesMM;
//
//    SimpleDateFormat sdf2 = new SimpleDateFormat("yy-mm-dd");
// Un test
//    public CreationDonneesMM(ListeCotisations listeCotisations) {
//        this.listeCotisations = listeCotisations;
//    }
//
//    public CreationDonneesMM(ListeCotisations listeCotisations, DonneesAffichageMM donneesMM) {
//        this.listeCotisations = listeCotisations;
//        this.donneesMM = donneesMM;
//    }

