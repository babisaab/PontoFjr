<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Usuarios</title>
    </head>
    <body>
        <h1>Pesquisa de Usuarios</h1>
        <table border="1">
            <tr>
                <th>Login</th>
                <th>Funcionário</th>
                <th colspan="2">Ações</th>
            </tr>
            <c:forEach items="${usuarios}" var="usuario">
                <tr>
                    <td><c:out value="${usuario.login}" /></td>
                    <td><c:forEach items="${funcionarios}" var="funcionario"><c:if test="${usuario.funcionario.id == funcionario.id}"><c:out value="${funcionario.nomeCompleto}" /></c:if></c:forEach></td>
                    <td><a href=
                           "ManterUsuarioController?acao=prepararOperacao&operacao=Editar&id=${usuario.id}">Editar</a></td>
                    <td><a href=
                           "ManterUsuarioController?acao=prepararOperacao&operacao=Excluir&id=${usuario.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ManterUsuarioController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>
