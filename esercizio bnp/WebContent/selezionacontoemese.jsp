<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="bean.MovimentiCC" %>    
<%@ page import="bean.Spese" %>
<%@ page import="bean.Conto" %>
<%@ page import="gestioneConti.GestioneMesi" %>   
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seleziona Conto e Mese</title>
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
  width: 50%;
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
</style>

 <%
 List<MovimentiCC> movCC= (List<MovimentiCC>)request.getSession().getAttribute("movimentiCC");
	
 List<Spese> spese= (List<Spese>)request.getSession().getAttribute("spese");
 	
 List<Conto> conti= (List<Conto>)request.getSession().getAttribute("conti"); 
 
 List<LocalDate> listaMesi = new ArrayList<LocalDate>();
 
 listaMesi = GestioneMesi.listaMesi(spese, movCC);%>
<%-- 
 request.setAttribute("listaMesi", listaMesi); 
 
 List<String> numeroConto= new ArrayList<String>();
 
 for(int i =0; i< conti.size(); i++){
	numeroConto.add(conti.get(i).getNumeroConto());
 }
		request.setAttribute("numeroConto", numeroConto); 
		--%>


 <h1> Benvenuto in Big Bank</h1>
 
	<form action="mostracontopermese.jsp" >
	
  SELEZIONA NUMERO CONTO:
		<select name="conto">
			<option value=""> Scegli il conto  </option>
		<%--	<c:forEach items="${numeroConto}" var="numeroConto">
    			<option value="${numeroConto}">
        		${numeroConto}
   				 </option>
  			</c:forEach> --%>
  	<%for(int i =0; i< conti.size(); i++){
	 String numeroConto = conti.get(i).getNumeroConto();
  		%>
  	<option value="<%= numeroConto %> "><%= numeroConto %></option>
  	 <%}%>		
		</select>
	
<br>
  SELEZIONA MESE :
		<select name="meseAnno">
			<option value=""> Scegli il mese  </option>
<%  for(int i =0; i< listaMesi.size(); i++){
	StringBuilder periodo = new StringBuilder();
	String meseEanno = periodo.append(listaMesi.get(i).getMonthValue()).append("-").append(listaMesi.get(i).getYear()).toString();
		
	%>
			<option value="<%= meseEanno %> "><%= meseEanno %></option>
<%}%>
<%--
			<c:forEach items="${listaMesi}" var="listaMesi">
    			<option value="${listaMesi}">
        		${listaMesi}
   				 </option>
  			</c:forEach>
--%>
		</select>
<br>
		<input type="submit" value="Accedi">
	</form> 

</body>
</html>