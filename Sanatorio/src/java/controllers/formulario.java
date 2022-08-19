/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import models.GestorBD;
import classes.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author E.T 36
 */
@WebServlet(urlPatterns = {"/formulario"})
public class formulario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int dni  = Integer.parseInt(request.getParameter("dni"));
            String direccion = request.getParameter("direccion");
            String obraSocial = request.getParameter("obraSocial");
            String doctor = request.getParameter("doctor");
            String estudio = request.getParameter("estudio");
            //agregamos esto
            int anio = Integer.parseInt(request.getParameter("año"));
            int mes = Integer.parseInt(request.getParameter("mes"));
            int dia = Integer.parseInt(request.getParameter("dia"));
           
            GestorBD g = new GestorBD();
            
            Paciente p = new Paciente(nombre, apellido, dni, direccion);
            ServletContext sc = this.getServletContext();
            boolean res = g.registro(p);
            if(res){
                g.registrarEnPacienteHasEstudio(dni, doctor, estudio, obraSocial, anio, mes, dia);
                System.out.println("Registro exitoso");
                request.getRequestDispatcher("/registroExitoso.html");
            }else{
                System.out.println("Registro fallido");
                request.getRequestDispatcher("/index.html");
            }
        }finally{
            out.close();
        }
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
