/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import acceso.AccesosDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ChartServlet extends HttpServlet {
    private final static int ANCHO=600;
    private final static int ALTO=500;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        try {
            JFreeChart chart;
            int ancho=ANCHO;
            int alto=ALTO;
            
            if(request.getParameter("tipo")!=null && !request.getParameter("tipo").isEmpty()){
                chart=(JFreeChart)request.getSession().getAttribute(request.getParameter("tipo"));
                ancho=Integer.parseInt(request.getParameter("ancho"));
                alto=Integer.parseInt(request.getParameter("alto"));
                request.getSession().removeAttribute(request.getParameter("tipo"));
            }else{
                chart=(JFreeChart)request.getSession().getAttribute("chart");
                request.getSession().removeAttribute("chart");
            }
            
            BufferedImage img = ImageIO.read(new File("/home/neuromancer/NetBeansProjects/modelo-base/web/images/atmosphere/img02.jpg"));
//            BufferedImage img = ImageIO.read(new File("/home/wintermute/NetBeansProjects/gigStore/web/images/atmosphere/img02.jpg"));
//            BufferedImage img = ImageIO.read(new File("/var/lib/tomcat6/webapps/ejemplo-tienda/images/atmosphere/img02.jpg"));
            chart.getPlot().setBackgroundImage(img);
            chart.setBackgroundPaint(new Color(255,255,255,0));
            chart.getPlot().setForegroundAlpha(0.7f);
            chart.getTitle().setFont(new Font("sofia", Font.PLAIN, 20));
            chart.getTitle().setPaint(new Color(58, 70, 72));
            
            try{
                for(int i=0;i<chart.getCategoryPlot().getDomainAxisCount();i++) {
                    chart.getCategoryPlot().getDomainAxis(i).setLabelFont(new Font("sofia", Font.PLAIN, 18));
                    chart.getCategoryPlot().getDomainAxis(i).setLabelPaint(new Color(58, 70, 72));
                }

                for(int i=0;i<chart.getCategoryPlot().getRangeAxisCount();i++) {
                    chart.getCategoryPlot().getRangeAxis(i).setLabelFont(new Font("sofia", Font.PLAIN, 18));
                    chart.getCategoryPlot().getRangeAxis(i).setLabelPaint(new Color(58, 70, 72));
                }
            }catch(Exception ex){}
            
            ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);
        }catch(Exception ex){
            Logger.getLogger(AccesosDAO.class.getName()).log(Level.SEVERE, null, ex);    
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
