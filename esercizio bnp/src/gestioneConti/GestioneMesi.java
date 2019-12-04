package gestioneConti;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.MovimentiCC;
import bean.Spese;

public class GestioneMesi {
	public static List<Integer> calcolaPeriodo(List<MovimentiCC> movCC, List<Spese> spese, int mese, int anno) {
		List<Integer> periodi = new ArrayList<Integer>();
		int periodoMesiDaAggiungereMovCCPrima =0;
		int periodoMesiDaAggiungereSpesePrima =0;
		int periodoMesiDaAggiungereMovCCDopo =0;
		int periodoMesiDaAggiungereSpeseDopo =0;
		int periodo=0;
		
		
		
		int[] primoMovimentoCC= {movCC.get(0).getData().getMonthValue(), movCC.get(0).getData().getYear()};
		int[] primaSpesa= {spese.get(0).getData().getMonthValue(), spese.get(0).getData().getYear()};
		

		if(primoMovimentoCC[1]<= primaSpesa[1] && primoMovimentoCC[0]<= primaSpesa[0]) {
			periodo = (anno-primoMovimentoCC[1])*12+(mese-primoMovimentoCC[0]);
			periodoMesiDaAggiungereSpesePrima = (primaSpesa[1] - primoMovimentoCC[1])*12 + (primaSpesa[0] - primoMovimentoCC[0]);	
		}
		
		else {
			periodo = (anno-primaSpesa[1])*12+(mese-primaSpesa[0]);
			periodoMesiDaAggiungereMovCCPrima = (primoMovimentoCC[1] - primaSpesa[1])*12 + (primoMovimentoCC[0] - primaSpesa[0]);
		}
		
		int[] ultimoMovimentoCC= {movCC.get(movCC.size()-1).getData().getMonthValue(), movCC.get(movCC.size()-1).getData().getYear()};
		int[] ultimaSpesa= {spese.get(spese.size()-1).getData().getMonthValue(), spese.get(spese.size()-1).getData().getYear()};
		

		periodoMesiDaAggiungereMovCCDopo = (anno - ultimoMovimentoCC[1])*12 + (mese - ultimoMovimentoCC[0]);
		periodoMesiDaAggiungereSpeseDopo = (anno - ultimaSpesa[1])*12 + (mese - ultimaSpesa[0]);
		
		periodi.add(periodoMesiDaAggiungereMovCCPrima);
		periodi.add(periodoMesiDaAggiungereMovCCDopo);
		periodi.add(periodoMesiDaAggiungereSpesePrima);
		periodi.add(periodoMesiDaAggiungereSpeseDopo);
		periodi.add(periodo);

		return periodi;
	}
	
	public static List<LocalDate> listaMesi(List<Spese> spese, List<MovimentiCC> movCC){
		List<LocalDate> listaMesi = new ArrayList<LocalDate>(); 
		Integer[] primoMovimentoCC= {Integer.valueOf(movCC.get(0).getData().getMonthValue()), Integer.valueOf(movCC.get(0).getData().getYear())};
		Integer[] primaSpesa= {spese.get(0).getData().getMonthValue(), spese.get(0).getData().getYear()};
		
		
		
		LocalDate dataInizio;
		LocalDate dataFine;
		if(primoMovimentoCC[1]<= primaSpesa[1] && primoMovimentoCC[0]<= primaSpesa[0]) {
			dataInizio = LocalDate.of(primoMovimentoCC[1], primoMovimentoCC[0], 1);
		}
		else {
			dataInizio = LocalDate.of(primaSpesa[1], primaSpesa[0], 1);
		}
		Integer[] ultimoMovimentoCC= {movCC.get(movCC.size()-1).getData().getMonthValue(), movCC.get(movCC.size()-1).getData().getYear()};
		Integer[] ultimaSpesa= {spese.get(spese.size()-1).getData().getMonthValue(), spese.get(spese.size()-1).getData().getYear()};
		if (ultimoMovimentoCC[1]>= ultimaSpesa[1] && ultimoMovimentoCC[0]>= ultimaSpesa[0]) {	
			dataFine = LocalDate.of(ultimoMovimentoCC[1], ultimoMovimentoCC[0], 28);
		}
		else {
			dataFine = LocalDate.of(ultimaSpesa[1],ultimaSpesa[0], 28);
		}
		
		while(dataInizio.isBefore(dataFine)) {
			listaMesi.add(dataInizio);
			dataInizio = dataInizio.plusMonths(1);
		}
		return listaMesi;
		}
}

