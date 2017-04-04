/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donneesAffichage;

import indicateur.MoyenneMobile;
import modele.Cotisation;

/**
 *
 * @author edson
 */
public class ValeurMMjour {

    private Cotisation cotisation;
    private MoyenneMobile mmLente;
    private MoyenneMobile mmRapide;
    private String signal;

    // Pour adapter à la page
    private double ouverture, max, min, fermeture, volFin;

    private static int declancheurMM = 0;
    private int declancheur;

    public ValeurMMjour(Cotisation cotisation, MoyenneMobile mmLente, MoyenneMobile mmRapide) {
        this.cotisation = cotisation;

        this.ouverture = cotisation.getOuverture();
        this.fermeture = cotisation.getFermeture();
        this.max = cotisation.getMax();
        this.min = cotisation.getMin();
        this.volFin = cotisation.getVolFin();
        this.mmLente = mmLente;
        this.mmRapide = mmRapide;

        if (declancheurMM == 0) {

            if (mmRapide.getValeur() > mmLente.getValeur()) {
                signal = "achat";
                declancheurMM = 1;
            } else if (mmRapide.getValeur() < mmLente.getValeur()) {
                signal = "vente";
                declancheurMM = -1;
            } else {
                signal = "neutre";
                declancheurMM = 0;
            }

        } else if (declancheurMM == 1) {
                   
            if (mmRapide.getValeur() > mmLente.getValeur()) {
                signal = "neutre";
                declancheurMM = 1;
            } else if (mmRapide.getValeur() < mmLente.getValeur()) {
                signal = "vente";
                declancheurMM = -1;
            }
            } else if (declancheurMM == -1) {
                if (mmRapide.getValeur() < mmLente.getValeur()) {
                    signal = "neutre";
                    declancheurMM = -1;
                } else if (mmRapide.getValeur() > mmLente.getValeur()) {
                    signal = "achat";
                    declancheurMM = 1;
                }
            }

            this.declancheur = declancheurMM;

        }

    public Cotisation getCotisation() {
        return cotisation;
    }

    public void setCotisation(Cotisation cotisation) {
        this.cotisation = cotisation;
    }

    public MoyenneMobile getMmLente() {
        return mmLente;
    }

    public void setMmLente(MoyenneMobile mmLente) {
        this.mmLente = mmLente;
    }

    public MoyenneMobile getMmRapide() {
        return mmRapide;
    }

    public void setMmRapide(MoyenneMobile mmRapide) {
        this.mmRapide = mmRapide;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public double getOuverture() {
        return ouverture;
    }

    public void setOuverture(double ouverture) {
        this.ouverture = ouverture;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getFermeture() {
        return fermeture;
    }

    public void setFermeture(double fermeture) {
        this.fermeture = fermeture;
    }

    public double getVolFin() {
        return volFin;
    }

    public void setVolFin(double volFin) {
        this.volFin = volFin;
    }

    public static int getDeclancheurMM() {
        return declancheurMM;
    }

    public static void setDeclancheurMM(int declancheurMM) {
        ValeurMMjour.declancheurMM = declancheurMM;
    }

    public int getDeclancheur() {
        return declancheur;
    }

    public void setDeclancheur(int declancheur) {
        this.declancheur = declancheur;
    }
    
    

    @Override
    public String toString() {
        return "ValeurMMjour{" + "cotacao=" + cotisation + ", mmLente=" + mmLente.getValeur() + ", mmRapide=" + mmRapide.getValeur() + ", signal=" + signal + ", declancheur=" + declancheur + '}';
    }

}
