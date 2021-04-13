package com.emergentes.controlador;

import com.emergentes.modelo.GestorVacunas;
import com.emergentes.modelo.Vacunas;
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
        Vacunas objVacuna = new Vacunas();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        if (op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorVacunas vac = (GestorVacunas) ses.getAttribute("vac");
            objVacuna.setId(vac.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miVacuna", objVacuna);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        
        if (op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacunas vac = (GestorVacunas) ses.getAttribute("vac");
            pos = vac.ubicarVacuna(id);
            objVacuna = vac.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miVacuna", objVacuna);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        
        if (op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacunas vac = (GestorVacunas) ses.getAttribute("vac");
            pos = vac.ubicarVacuna(id);
            vac.eliminarVacuna(pos);
            ses.setAttribute("vac", vac);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacunas objVacuna = new Vacunas();
        int pos;
        String op = request.getParameter("op");
        
        if (op.equals("grabar")){
            //recuperar valores del formulario
            //verifica si es nuevo o es una modificacion
            objVacuna.setId(Integer.parseInt(request.getParameter("id")));
            objVacuna.setNombre(request.getParameter("nombre"));
            objVacuna.setPeso(Integer.parseInt(request.getParameter("peso")));
            objVacuna.setTalla(request.getParameter("talla"));
            objVacuna.setVacuna(request.getParameter("vacuna"));
            
            HttpSession ses = request.getSession();
            GestorVacunas vac = (GestorVacunas) ses.getAttribute("vac");
            
            String opg = request.getParameter("opg");
            if (opg.equals("nuevo")){
                vac.insertarVacuna(objVacuna);
            }else{
                pos = vac.ubicarVacuna(objVacuna.getId());
                vac.modificarVacuna(pos, objVacuna);
            }
            ses.setAttribute("vac", vac);
            response.sendRedirect("index.jsp");
        }
    }
}
