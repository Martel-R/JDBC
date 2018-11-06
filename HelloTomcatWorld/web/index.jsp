<%--
  Created by IntelliJ IDEA.
  User: martel
  Date: 30/10/2018
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>TESTE</title>
</head>
<body>

    <form method='GET' action='../src/SalvarContatoServlet.java'>
    Nome:
        <input type='text' name='nome'/>
    Endereco:
        <input type='text' name='endereco'/>
    Data Nascimento:
        <input type='text' name='data_nascimento'>

        <button type="submit">Criar Contato</button>
    </form>
</body>
</html>
