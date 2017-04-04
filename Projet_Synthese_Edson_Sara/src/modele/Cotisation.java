/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modele;

import java.util.Calendar;
import java.util.Date;
//import java.sql.Date;

/**
 *
 * @author edson
 */
public class Cotisation {
    
//    private String codeAction = "PCAR4"; 
//            private Calendar date;
//    private double ouverture, max, min, fermeture, volFin, volQte;
    private double ouverture, max, min, fermeture, volFin;

    public Cotisation() {
    }

    public Cotisation(Calendar date, double abertura, double max, double min, double fechamento, double volFin, double volQte) {
//        this.codeAction = codeAction;
//        this.date = date;
        this.ouverture = abertura;
        this.fermeture = fechamento;
        this.max = max;
        this.min = min;
        this.volFin = volFin;
//        this.volQte = volQte;
    }

    public Cotisation(double ouverture, double max, double min, double fermeture, double volFin) {
        this.ouverture = ouverture;
        this.max = max;
        this.min = min;
        this.fermeture = fermeture;
        this.volFin = volFin;
    }
    
    

//    public String getCodeAction() {
//        return codeAction;
//    }
//
//    public void setCodeAction(String codeAction) {
//        this.codeAction = codeAction;
//    }
//
//    public Calendar getDate() {
//        return date;
//    }
//
//    public void setDate(Calendar date) {
//        this.date = date;
//    }

    public double getOuverture() {
        return ouverture;
    }

    public void setOuverture(double ouverture) {
        this.ouverture = ouverture;
    }

    public double getFermeture() {
        return fermeture;
    }

    public void setFermeture(double fermeture) {
        this.fermeture = fermeture;
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

    public double getVolFin() {
        return volFin;
    }

    public void setVolFin(double volFin) {
        this.volFin = volFin;
    }

//    public double getVolQte() {
//        return volQte;
//    }
//
//    public void setVolQte(double volQte) {
////        this.volQte = volQte;
//    }

//    @Override
//    public String toString() {
//        return "Acao{" + "codeAction=" + codeAction + ", date = (" + date.get(Calendar.DAY_OF_MONTH)+"-"+(date.get(Calendar.MONTH)+1) +"-"+date.get(Calendar.YEAR) +") , abertura=" + ouverture + ", fechamento=" + fermeture + ", max=" + max + ", min=" + min + ", volFin=" + volFin + ", volQte=" + volQte + '}';
//    }
    
    
    
}
