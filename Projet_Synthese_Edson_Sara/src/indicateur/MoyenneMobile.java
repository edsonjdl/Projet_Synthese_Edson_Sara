/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicateur;

import java.util.Calendar;

/**
 *
 * @author edson
 */
public class MoyenneMobile {
    
//    private Calendar date;
    private double valeur;

    public MoyenneMobile() {
    }

    public MoyenneMobile(double valeur) {
        this.valeur = valeur;
    }
    
    

//    public MoyenneMobile(Calendar date, double valeur) {
//        this.date = date;
//        this.valeur = valeur;
//    }
    
    

//    public Calendar getDate() {
//        return date;
//    }
//
//    public void setDate(Calendar date) {
//        this.date = date;
//    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }


    @Override
    public String toString() {
        return "MoyenneMobile{" + ", valeur=" + valeur + '}';
    }


    
    
    
}
