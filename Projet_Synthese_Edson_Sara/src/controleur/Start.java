package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donneesAffichage.CreationDonneesMM;
import connexionDB.DaoCotisation;
import donneesAffichage.DonneesAffichageMM;
import modele.ListeCotisations;
import modele.Cotisation;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Cotisation c;
    ListeCotisations listeC;

    DonneesAffichageMM donneesMM;

    public static final int PERIODE_RAPIDE = 7;
    public static final int PERIODE_LENT = 21;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1 - Creation des objets Cotisation et une liste de cotisation
        c = new Cotisation();
        listeC = new ListeCotisations();

        // 2 - Charger la liste de cotisations dans la liste
        DaoCotisation daoCotisation = new DaoCotisation(c, listeC);
        String action = request.getParameter("action");
        

        
        try {
                    if(action!=null){
                daoCotisation.lireBD(action);
            }else{
                     daoCotisation.lireBD("pcar4");   
                    }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }

        listeC.afficherCotisation();

        donneesMM = new DonneesAffichageMM();
        CreationDonneesMM.genererMM(listeC, PERIODE_RAPIDE, PERIODE_LENT, donneesMM);

        String choixPage = request.getParameter("choixPage");
        

        HttpSession session = request.getSession();
        String dest = "/samples/TestJavaScript2.jsp";

        if (choixPage.equals("test2")) {
            dest = "/samples/TestJavaScript2.jsp";
        } else if (choixPage.equals("test3")) {
            dest = "/samples/TestJavaScript3.jsp";
        } else if (choixPage.equals("test4")) {
            dest = "/samples/stockEvents_4.jsp";
        } else if (choixPage.equals("test5")) {
            dest = "/samples/stockEvents_5.jsp";
        } else if (choixPage.equals("test6")) {
            dest = "/samples/stockMultipleDataSets_3.jsp";
            
        }

        //session.setAttribute("maListe", listeC);
        session.setAttribute("maListe", donneesMM);

        RequestDispatcher disp = getServletContext().getRequestDispatcher(dest);
        disp.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
