package connexionDB;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.URL;

/**
 * SetupDB.java
 * Initialise une connexion à une base de données Oracle
 * On utilise un fichier de propriétés externe pour éviter le hardcodage
 *@author: Hafed Benteftifa
 */

public class SetupDB {

  private SetupDB() { }

  /**
   * Obtenir une connexion à partir des infos qui sont
   * dans un fichier de propriétés.
   * Le fichier doit contenir les propriétés driver, url, identifiant, motdepasse .
   * @param nomFichierProp nom du fichier de propriétés. Si le nom
   * commence par "/", l'emplacement désigne un endroit relatif
   * au classpath, sinon il désigne un endroit relatif au
   * répertoire qui contient le fichier ConfigConnection.class.
   * @return une connexion à la BD
   */
  public static Connection getConnection(String nomFichierProp)
      throws IOException, ClassNotFoundException, SQLException {
      
  
    Properties props = new Properties();
    
//    URL urlFichierProp = SetupDB.class.getResource(nomFichierProp);
URL urlFichierProp = SetupDB.class.getResource(nomFichierProp);

    BufferedInputStream bis = null;
    
    try {
    	//lecture du fichier de propriétés

      bis = new BufferedInputStream(urlFichierProp.openStream());
      props.load(bis);
      String driver = props.getProperty("driver");
      String url = props.getProperty("url");
      String identifiant = props.getProperty("identifiant");
      String motdepasse = props.getProperty("motdepasse");
      Class.forName(driver);
      //retourner le driver approprié
      return DriverManager.getConnection(url, identifiant, motdepasse);
        
    } finally {
      if (bis != null) {
        bis.close();
      }
    
  }
  }

  /**
   * Obtenir une connexion à partir des infos qui sont  dans un fichier de propriétés, du nom d'utilisateur
   * et du mot de passe passés en paramètre.
   * Le fichier doit contenir les propriétés driver, url, le nom et le mot de passe de l'utilisateur sont passés
   * en paramètre de la méthode.
   * @param nomFichierProp nom du fichier de propriétés. Si le nom
   * commence par "/", l'emplacement désigne un endroit relatif
   * au classpath, sinon il désigne un endroit relatif au
   * répertoire qui contient le fichier ConfigConnection.class.
   * @param identifiant nom du propriétaire du schéma
   * @param motdepasse mot de passe du propriétaire du schéma.
   * @return une connexion à la base.
   */
  public static Connection getConnection(String nomFichierProp,
                                         String identifiant,
                                         String motdepasse)
      throws IOException, ClassNotFoundException, SQLException {
    Properties props = new Properties();
    URL urlFichierProp = SetupDB.class.getResource(nomFichierProp);
    BufferedInputStream bis = null;
    try {
      bis = new BufferedInputStream(urlFichierProp.openStream());
      props.load(bis);
      String driver = props.getProperty("driver");
      String url = props.getProperty("url");
      Class.forName(driver);
      return DriverManager.getConnection(url, identifiant, motdepasse);
    }
    finally {
      if (bis != null) {
        bis.close();
      }
    }
  }
}
