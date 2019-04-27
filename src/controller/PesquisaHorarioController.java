package controller;

import dao.HorarioDAO;
import dao.FuncionarioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PesquisaHorarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String acao = request.getParameter("acao");
        Long id = (long)0;
        if(request.getParameter("id") != null){
            id = Long.parseLong(request.getParameter("id"));
        }
        if(acao.equals("Only")){
            request.setAttribute("acao", acao);
            request.setAttribute("horarios", HorarioDAO.getInstance().getAllHorarios());
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        }else{
            request.setAttribute("horarios", HorarioDAO.getInstance().getAllHorarios());
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        }
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaHorario.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
