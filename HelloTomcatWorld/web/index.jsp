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
    <link rel="stylesheet" type="text/css" href="main.css">

</head>
<body>

    <div id="quadrado">
        <h1>Agenda JDBC</h1>
        <form method='POST' id="formulario" action='salvar'>
            Nome:
            <br><input type='text' name ='nome'/><br><br>
            Contato:<br>
            <input type='text' name='contato'/><br><br>
            Endereco:<br>
            <input type='text' name='endereco'/><br><br>
            Email:<br>
            <input type='text' name='email'/><br><br>
            Data Nascimento:<br>
            <input type='date' name='data_nascimento'><br><br>

            <button type="submit">Criar Contato</button><br>
        </form>
    </div>
</body>
</html>
