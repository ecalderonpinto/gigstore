/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaServlet extends HttpServlet {
    private int WIDTH = 150;
    private int HEIGHT = 50;
//    private int NUM = 8;
    // Primer color de degradado:
    private int[] rgb1 = {146, 189, 243};
    // Segundo color de degradado:
    private int[] rgb2 = {220, 235, 253};
    
    private void draw (HttpServletRequest request, HttpServletResponse response) {
        try {
            // Fuente y Colores:
            Font font = new Font("Times", Font.BOLD, 16);
            Color c1 = new Color(rgb1[0], rgb1[1], rgb1[2]);
            Color c2 = new Color(rgb2[0], rgb2[1], rgb2[2]);
            // Creamos la imagen:
            BufferedImage bimage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bimage.createGraphics();
            // Establecemos la gradiente del fondo:
            GradientPaint gp = new GradientPaint(0, 0, c1, 0, HEIGHT, c2);
            g.setPaint(gp);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            // Ponemos la fuente y el color del texto:
            g.setFont(font);
            g.setColor(c2);
            // Escribimos el texto, variando la altura de los caracteres:
            String texto=request.getSession().getAttribute("texto").toString();
            char[] text = texto.toCharArray();
            int num=Integer.parseInt(request.getParameter("num").toString());
            Random rand;
            int y;
            for (int i = 0; i < num; i++) {
                rand = new Random(System.currentTimeMillis());
                y = rand.nextInt(20);
                g.drawChars(text, i, 1, 15*(i+1), 20+y);
            }
            // Mostramos la imagen:
            g.dispose();
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bimage, "png", outputStream);
            outputStream.close();
        } catch (Exception e) {
            System.out.println ("Exception: " + e.getMessage());
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
        draw(request,response);
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
        draw(request,response);
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
