package controller;

import dao.CartaoDAO;
import dao.FuncionarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cartao;
import model.Funcionario;

public class ManterCartaoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCartaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterCartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCartaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterCartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Cartao cartao = CartaoDAO.getInstance().getCartao(id);
                request.setAttribute("cartao", cartao);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterCartao.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
        String operacao = request.getParameter("operacao");

        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdCartao"));

                CartaoDAO.getInstance().excluir(CartaoDAO.getInstance().getCartao(id));
            } else {
                String tipo = request.getParameter("optTipoCartao");
                String cor = request.getParameter("optCorCartao");
                String motivo = request.getParameter("txtMotivoCartao");
                String data = request.getParameter("txtDataCartao");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = FuncionarioDAO.getInstance().getFuncionario(idFuncionario);
                Cartao cartao = new Cartao(tipo, cor, motivo, data, funcionario);
                if (operacao.equals("Incluir")) {
                    CartaoDAO.getInstance().salvar(cartao);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdCartao"));

                    cartao.setId(id);
                    CartaoDAO.getInstance().salvar(cartao);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaCartaoController");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}