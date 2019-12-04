<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carica File</title>
</head>
<body>
<style>
input, select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  
  input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
}
</style>
	<form action="upload" method="post" enctype="multipart/form-data">
	
    MOVIMENTI CC SPEC: <input type="file" name="movimenti_cc_spec" /><br>
    MOVIMENTI CC: <input type="file" name="movimenti_cc" /><br>
    SPESE CARTA: <input type="file" name="spese_carta" /><br>
    CARTE CONTI: <input type="file" name="carte_conti" /><br>
    <input type="submit" value="Invia"/>
</form>
</body>
</html>