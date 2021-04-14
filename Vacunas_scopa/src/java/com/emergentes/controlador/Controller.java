package com.emergentes.controlador;

import com.emergentes.modelo.GestorVacunas;
import com.emergentes.modelo.Vacuna;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacuna objvac = new Vacuna();
        int id;
        int pos;
        String op = request.getParameter("op");

        if (op.equals("nuevo")) {
            HttpSession ses = request.getSession();
            GestorVacunas vacuna = (GestorVacunas) ses.getAttribute("vacuna");
            objvac.setId(vacuna.obtenerId());
            request.setAttribute("op", op);
            request.setAttribute("mivacuna", objvac);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }

        if (op.equals("modificar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacunas vacuna = (GestorVacunas) ses.getAttribute("vacuna");
            pos = vacuna.ubicarRegistro(id);
            objvac = vacuna.getLista().get(pos);
            request.setAttribute("op", op);
            request.setAttribute("mivacuna", objvac);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacunas vacuna = (GestorVacunas) ses.getAttribute("vacuna");
            pos = vacuna.ubicarRegistro(id);
            vacuna.eliminarRegistro(pos);
            ses.setAttribute("vacuna", vacuna);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacuna objvac = new Vacuna();
        int pos;
        String op = request.getParameter("op");
        if (op.equals("grabar")) {
            objvac.setId(Integer.parseInt(request.getParameter("id")));
            objvac.setNombre((request.getParameter("nombre")));
            objvac.setPeso(Double.parseDouble(request.getParameter("peso")));
            objvac.setTalla(Double.parseDouble(request.getParameter("talla")));
            objvac.setVacuna((request.getParameter("vacuna")));

            HttpSession ses = request.getSession();
            GestorVacunas vacuna = (GestorVacunas) ses.getAttribute("vacuna");

            String opg = request.getParameter("opg");
            if (opg.equals("nuevo")) {
                vacuna.insertarRegistro(objvac);
            } else {
                pos = vacuna.ubicarRegistro(objvac.getId());
                vacuna.modificarRegistro(pos, objvac);

            }
            ses.setAttribute("vacuna", vacuna);
            response.sendRedirect("index.jsp");
        }

    }

}
