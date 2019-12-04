package gestioneConti;

import java.util.ArrayList;
import java.util.List;

import bean.Conto;
import bean.MovimentiCC;
import bean.Spese;

public class GestioneConto {
	
	public static List<Double> calcoloRilevantiConto(List<MovimentiCC> movCC, List<List<Spese>> speseCartaPerCarta, List<List<Integer>> periodi){
		List<Double> rilevantiConto = new ArrayList<Double>();
		
		List<List<Double>> totaleSpese = new ArrayList<List<Double>>();
//		System.out.println(speseCartaPerCarta.size());
		for(int i=0; i<speseCartaPerCarta.size();i++) {
			totaleSpese.add(GestioneSpese.calcoloTotaleSpese(speseCartaPerCarta.get(i), periodi.get(i)));
		}
		//System.out.println(totaleSpese.size());
		List<Double> totaleMovimentiCC = GestioneMovimentiCC.calcoloTotaleMovimentiCC(movCC, periodi.get(0));

			
		Conto.setTotale(0);
		Conto.setSaldoFinale(0);
		
		
		Spese.setTotale(totaleSpese);
//		System.out.println(Spese.getTotale().size());
		
		for(int i =0; i<periodi.get(0).get(4); i++) {
			Conto.setSaldoIniziale(Conto.getTotale());
			Conto.setSaldoFinale(Conto.getSaldoIniziale()+totaleMovimentiCC.get(i));
			
			Conto.setTotale(Conto.getSaldoFinale());
		
			System.out.println("Spese.getTotale().size() : " + Spese.getTotale().size());
			System.out.println("i : " + i);
			
			if(Spese.getTotale().size()>i) {
				for (int n=0; n < Spese.getTotale().get(i).size(); n++) {
					System.out.println("n : " + n);
					Conto.setTotale(Conto.getTotale()-Spese.getTotale().get(i).get(n));
				}
			}
			
		} 
		
		rilevantiConto.add(Conto.getTotale());
		rilevantiConto.add(Conto.getSaldoIniziale());
		rilevantiConto.add(Conto.getSaldoFinale());
		rilevantiConto.add(totaleMovimentiCC.get(totaleMovimentiCC.size()-1));
		for(Double d : totaleMovimentiCC) {
			System.out.println(d);
		}
		System.out.println("Spese.getTotale().size() : " + Spese.getTotale().size());
		for (int l=0; l < Spese.getTotale().size(); l++) {
			System.out.println(l);
			double x = Spese.getTotale().get(l).get(Spese.getTotale().get(l).size()-1);
			rilevantiConto.add(x);
		}
		return rilevantiConto;
	}
	
}
