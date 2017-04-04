/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.connexionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import src.modele.Cotisation;
import src.modele.ListeCotisations;

/**
 *
 * @author edson
 */
public class DaoCotisation {

            // Charge le driver
//        Class.forName("oracle.jdbc.OracleDriver");
    private Connection connexion = null;
    private Statement stmt = null;

    private Cotisation cotisation;
    private ListeCotisations liste;

    public DaoCotisation() {
    }

    public DaoCotisation(Cotisation cotisation, ListeCotisations lista) {
        this.cotisation = cotisation;
        this.liste = lista;
    }
    
        public DaoCotisation(ListeCotisations lista) {
        this.liste = lista;
    }

   

    

    

    public void insertionBD() throws SQLException, ClassNotFoundException, java.io.IOException {

        try {
//            connexion = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@192.168.56.2:1521:ORCL", "hr", "hr");

            connexion = SetupDB.getConnection("infoconnexion.prp");
            stmt = connexion.createStatement();

            for (Cotisation c : liste) {

//                            ResultSet rset = stmt.executeQuery(
                int rows = stmt.executeUpdate(
                        "INSERT INTO `Acao_v1`.`PCAR4` (`data`, `abertura`, `max`, `min`, `fechamento`, `volFin`, `volQte`) VALUES ('" + c.getOuverture() + "', '" + c.getMax() + "', '" + c.getMin() + "', '" + c.getFermeture() + "', '" + c.getVolFin() + "');"


                );

            }


        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connexion != null) {
                connexion.close();
            }

        }
    }
    
    
        public void lireBD(String action) throws SQLException, ClassNotFoundException, java.io.IOException {
            
            

        try {
//            connexion = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@192.168.56.2:1521:ORCL", "hr", "hr");

            connexion = SetupDB.getConnection("infoconnexion.prp");
            stmt = connexion.createStatement();
            ResultSet rset = stmt.executeQuery(
                    "SELECT "
                            + "`" + action + "`.`data`, `" + action + "`.`abertura`, `" + action + "`.`max`, `" + action + "`.`min`, `" + action + "`.`fechamento`, `" + action + "`.`volFin`, `" + action + "`.`volQte` "//,  job_id "
                    + "FROM `acoes`.`" + action + "` "
//                    + "WHERE e.job_id = j.job_id "
//                    + "WHERE department_id=60 "
//                    + "AND job_id = 'IT_PROG' "
                    );
                    

            while (rset.next()) {
                
                Cotisation cotisation = new Cotisation();

                
                
                System.out.println(
                        "Nom de l'employé: " + rset.getDate(1)
//                        + " fonction: " + rset.getString(2) //+ " employés"
                );
                
                Date date = rset.getDate(1);
                Calendar cal=new GregorianCalendar();               
                cal.setTime(date);               
                //cotisation.setDate(cal);
                
                cotisation.setOuverture(rset.getDouble(2));
                cotisation.setMax(rset.getDouble(3));
                cotisation.setMin(rset.getDouble(4));
                cotisation.setFermeture(rset.getDouble(5));
                cotisation.setVolFin(rset.getDouble(6));
                //cotisation.setVolQte(rset.getDouble(7));
                
                
                // ATENCAO AS DATAS. O MES COMECA DO 0 (ZERO)=> JANEIRO = 0.
                
                int mois = cal.get(Calendar.MONTH) + 1;
                System.out.println(mois);
                liste.ajouterCotisation(cotisation);
                
            }


        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connexion != null) {
                connexion.close();
            }

        }
    }

}
