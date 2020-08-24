/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.bancogrupo2.servelet;

/**
 *
 * @author ads
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@WebServlet("/CrearSession")
public class CreateSession extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Consumes({MediaType.APPLICATION_JSON})
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession misession= request.getSession(true);
        String idCliente = request.getParameter("idCliente");
        String documento = request.getParameter("documento");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaDeNacimiento = request.getParameter("fechaDeNacimiento");
        
        misession.setAttribute("idCliente",idCliente);
        misession.setAttribute("documento",documento);
        misession.setAttribute("password",password);
        misession.setAttribute("nombre",nombre);
        misession.setAttribute("apellido",apellido);
        misession.setAttribute("fechaDeNacimiento",fechaDeNacimiento);

    }
}

