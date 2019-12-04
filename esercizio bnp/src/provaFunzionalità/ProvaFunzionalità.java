package provaFunzionalità;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Conto;
import bean.MovimentiCC;
import bean.Spese;
import gestioneConti.GestioneConto;
import gestioneConti.GestioneMesi;
import letturaFile.FileCsv;
import letturaFile.FileMovimentiCC;
import letturaFile.FileXml;

public class ProvaFunzionalità {
	
	public static void main(String[] args) {
		List<Conto> conti = new ArrayList<Conto>();
		List<Spese> spese = new ArrayList<Spese>();
		String[][] istruzioni = new String[4][2];
		List<List<Spese>> speseCartaPerCarta = new ArrayList<List<Spese>>();
		List<List<Spese>> speseCxC = new ArrayList<List<Spese>>();
		List<MovimentiCC> movCC = new ArrayList<MovimentiCC>();
		List<List<Integer>> periodi = new ArrayList<List<Integer>>();
		List<Double> rilevanti = new ArrayList<Double>();
		
 		conti = FileXml.letturaFile("C:\\Users\\Padawan07\\Desktop\\workspace_vittorio\\esercizio bnp\\risorse\\carte_conti.xml");
		spese = FileCsv.letturaFile("C:\\Users\\Padawan07\\Desktop\\workspace_vittorio\\esercizio bnp\\risorse\\spese_carta.csv");
		istruzioni = FileMovimentiCC.letturaIstruzioni("C:\\Users\\Padawan07\\Desktop\\workspace_vittorio\\esercizio bnp\\risorse\\movimenti_cc_spec.txt");
		movCC = FileMovimentiCC.letturaFile(istruzioni,"C:\\Users\\Padawan07\\Desktop\\workspace_vittorio\\esercizio bnp\\risorse\\movimenti_cc.txt");
		
		spese =Spese.ordinaDati(spese);
		movCC = MovimentiCC.ordinaDati(movCC);
		
		//lavoro su spese per ottenere la lista coi dati che voglio
		spese = Spese.selezionaDatiPerConto(spese,conti);
		spese = Spese.selezionaDatiRichiesti(spese, conti.get(0).getNumeroConto(), 9, 2017);
		movCC = MovimentiCC.selezionaDatiRichiesti(movCC, conti.get(0).getNumeroConto(), 9, 2017);
		
		MovimentiCC mCC = new MovimentiCC();
		try {
			double ammontare = movCC.get(0).getAmmontare();
		}catch (Exception e) {
			
			mCC.setData(LocalDate.of(2017, 9, 1));
			System.out.println("data = " + mCC.getData());
			mCC.setAmmontare(0);
			mCC.setNumeroConto(conti.get(0).getNumeroConto());
			
		}
		movCC.add(mCC);
		
		Spese spesa = new Spese();
		
		try {
			double ammontare = movCC.get(0).getAmmontare();
		}catch (Exception e) {
			
			spesa.setData(LocalDate.of(2017, 9, 1));
			System.out.println("data = " + spesa.getData());
			spesa.setAmmontare(0);
			spesa.setNumeroConto(conti.get(0).getNumeroConto());
			
		}
		
		spese.add(spesa);
		
		for(int i =0; i<conti.get(0).getNumeroCarta().size();i++) {
//			System.out.println("ciao");
			speseCartaPerCarta.add(Spese.selezionaDatiPerCarta(spese, conti.get(0).getNumeroCarta().get(i)));
		}

			
		
		for(int i =0; i<conti.get(0).getNumeroCarta().size();i++) {
			periodi.add(GestioneMesi.calcolaPeriodo(movCC, speseCartaPerCarta.get(i), 11, 2017));
		}


		rilevanti = GestioneConto.calcoloRilevantiConto(movCC, speseCartaPerCarta, periodi);
		for(int i=0; i<rilevanti.size();i++) {
			System.out.println("rilevanti = " + rilevanti.get(i));
			}
		
	}

}
