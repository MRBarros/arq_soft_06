<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listar Fila</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header">Fila(s)</h3>
        <br>
         <a href="novaFila" class="btn btn-success"> Novo Fila</a>
        <br>
        <br>        
        <c:if test="${not empty filas}">
            <div class="table-responsive col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Fila Id</th>
                            <th>Nome Fila</th>
                            <th>Ações</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="fila" items="${filas}">
                            <tr>
                                <td>${fila.id}</td>
                                <td>${fila.nome }</td>
                                <td><p data-placement="top" data-toggle="tooltip"
								title="Delete">
								<a class="btn btn-danger btn-sm" href="excluir_fila?id=${fila.id}">
									<span class="glyphicon glyphicon-trash"></span>
								</a>
								</p></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty filas}">
            <div class="alert alert-info" role="alert">N&atilde;o h&aacute; fila nesta lista.</div>
        </c:if>
        <a href="listar_filas_exibir" class="btn btn-default">Voltar</a>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>