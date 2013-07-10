/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dominio.TextosTienda;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class BannerServlet extends HttpServlet {
    public enum TipoBanner
    {
        HOME, FICHA, LATERAL1, LATERAL2; 
    }
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream salida = response.getOutputStream();   
        TextosTienda txt = (TextosTienda) request.getSession().getAttribute("txt");
        
        try {
            String tipo=request.getParameter("tipo");
            byte[] imagen=null;
            
            switch(TipoBanner.valueOf(tipo.toUpperCase())){
                case HOME:
                    imagen = txt.getBanners().getBannerHome();
                    response.setContentType(txt.getBanners().getBannerHomeTipo());
                    break;
                case FICHA:
                    imagen = txt.getBanners().getBannerFicha();
                    response.setContentType(txt.getBanners().getBannerFichaTipo());
                    break;
                case LATERAL1:
                    imagen = txt.getBanners().getBannerLateral1();
                    response.setContentType(txt.getBanners().getBannerLateral1Tipo());
                    break;
                case LATERAL2:
                    imagen = txt.getBanners().getBannerLateral2();
                    response.setContentType(txt.getBanners().getBannerLateral2Tipo());
                    break;
            }
            if(imagen!=null)
                salida.write(imagen);
        } catch(Exception ex){
            /*No hacer nada*/
        } finally {       
            salida.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
