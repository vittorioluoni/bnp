<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bean.MovimentiCC" %>    
<%@ page import="bean.Spese" %>
<%@ page import="bean.Conto" %>
<%@ page import="gestioneConti.GestioneMesi" %>   
<%@ page import="gestioneConti.GestioneConto" %>   
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Mostra Conto</title>
</head>
<body>
 <% 	List<List<Spese>> speseCartaPerCarta = new ArrayList<List<Spese>>();
		List<List<Spese>> speseCxC = new ArrayList<List<Spese>>();
		List<MovimentiCC> movCC = new ArrayList<MovimentiCC>();
		List<List<Integer>> periodi = new ArrayList<List<Integer>>();
		List<Double> rilevanti = new ArrayList<Double>();
		movCC= (List<MovimentiCC>)request.getSession().getAttribute("movimentiCC");
		
		//movCC = MovimentiCC.ordinaDati(movCC);
		
		List<Spese> spese= (List<Spese>)request.getSession().getAttribute("spese");
		
	 	List<Conto> conti= (List<Conto>)request.getSession().getAttribute("conti"); 
	 
	 	
	 	String numeroConto = request.getParameter("conto").trim();
	 	String periodo = request.getParameter("meseAnno").trim();
	 			
	
	  	
	 	
		
		int sep = periodo.indexOf("-");
		
		int mese = Integer.parseInt(periodo.substring(0, sep));
	 	int anno = Integer.parseInt(periodo.substring(sep + 1));
		int giorno =1;
		
		System.out.println("mese ="  + mese );
		System.out.println("anno =" + anno);
		
		
	 	spese = Spese.selezionaDatiPerConto(spese, conti);
	 	
	 	Conto conto = new Conto();
		for(int i =0; i<conti.size();i++) {
			System.out.println("ciao1"+ conti.get(i).getNumeroConto().equals(numeroConto));
			
			if (conti.get(i).getNumeroConto().equals(numeroConto)) {
				conto = conti.get(i);
				System.out.println("ciao1");
			}
		}
		for(int i=0; i<spese.size();i++){
			System.out.println(spese.get(i).getNumeroCarta());
		}
		
		for(int i =0; i<conto.getNumeroCarta().size();i++) {
			System.out.println(conto.getNumeroCarta().get(i));
		}
	
		
	 	for(int i =0; i<conto.getNumeroCarta().size();i++) {
	 		System.out.println("ciao2");
			speseCxC.add(Spese.selezionaDatiPerCarta(spese, conto.getNumeroCarta().get(i)));
		}

	 	for(int i =0; i<speseCxC.size();i++) {
	 		speseCartaPerCarta.add(Spese.selezionaDatiRichiesti(speseCxC.get(i), numeroConto, mese, anno));
	 	}
	 	
	 		System.out.println("sizespesacartapercarta" + speseCartaPerCarta.size());
	 		
	 	for(int i =0; i<speseCartaPerCarta.size();i++) {
	 		if (speseCartaPerCarta.get(i).size() == 0){
				Spese spesa = new Spese();
				spesa.setData(LocalDate.of(anno, mese, giorno));
				spesa.setAmmontare(0);
				spesa.setNumeroConto(numeroConto);
				speseCartaPerCarta.get(i).add(spesa);	
				System.out.println("Ho aggiunto un valore?" + speseCartaPerCarta.get(0).get(0).getAmmontare());
				
			}
			
	 	}
	 	//System.out.println("Ho aggiunto un valore?" + speseCartaPerCarta.get(0).get(0).getAmmontare());
		
		//lavoro su movCC per ottenere i dati che voglio
		movCC = MovimentiCC.selezionaDatiRichiesti(movCC, numeroConto, mese, anno);
		
		try {
			double ammontare = movCC.get(0).getAmmontare();
		}catch (Exception e) {
			
		 	MovimentiCC mCC = new MovimentiCC();
			mCC.setData(LocalDate.of(anno, mese, giorno));
			mCC.setAmmontare(0);
			mCC.setNumeroConto(numeroConto);
			movCC.add(mCC);
			
		}
		
		for(int i =0; i<conto.getNumeroCarta().size();i++) {
			periodi.add(GestioneMesi.calcolaPeriodo(movCC, speseCartaPerCarta.get(i), mese, anno));
		}
		System.out.println(speseCartaPerCarta.size());
		System.out.println(periodi.size());


		rilevanti = GestioneConto.calcoloRilevantiConto(movCC, speseCartaPerCarta, periodi);
		movCC = MovimentiCC.selezionaDatiPerMese(movCC,mese,anno);
		spese = Spese.selezionaDatiPerMese(spese,mese,anno);
		List<String> numeroCarta = new ArrayList<String>();
		
		%>
		
		
		
		<h2>NUMERO CONTO: <%= conto.getNumeroConto() %></h2>
		<br>
		
		
		<h2>SALDO INIZIALE MESE : <%= rilevanti.get(1) %></h2>
		<br>
		
		
		<h2>LISTA MOVIMENTI CC:<%
		%><table><%
		for(MovimentiCC mvCC: movCC) {
			%>
			  <tr>
			  <td><%
			out.println(mvCC.getAmmontare());
			  %>
			  </td>
			  </tr><%
			  
			
		} %> </table></h2>
		
		<h2>TOTALE MOVIMENTI CC: <%= rilevanti.get(3) %></h2>
		<br>
		
		<h2>SALDO FINALE MESE: <%= rilevanti.get(2) %></h2>
		<br>
		
		
		<h2>SPESE: </h2>
		<br>
		<% for( int i =0; i<conto.getNumeroCarta().size(); i++){
			
			out.println("NumeroCarta");
			%><br><% 
			out.println(conto.getNumeroCarta().get(i));
			%><br><% 
			
			for (int j=0; j<speseCartaPerCarta.get(i).size(); j++){
				out.println(speseCartaPerCarta.get(i).get(j).getAmmontare());
				%><br><%

			}
			%>
			<h3>TOTALE: </h3>
			<%
			out.println(rilevanti.get(4+i));
			%><br>
			<br>
			<br>
			<%
		}
			%>
		
		<h2>TOTALE CONTO: </h2>
		<br>
		<% out.println(rilevanti.get(0));%>

</body>
</html>