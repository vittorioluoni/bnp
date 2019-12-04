package gestioneConti;

import java.util.ArrayList;
import java.util.List;

import bean.MovimentiCC;

public class GestioneMovimentiCC {
	public static List<Double> calcoloTotaleMovimentiCC(List<MovimentiCC> movCC, List<Integer> periodi){
		List<Double> totaleMovimentiCC = new ArrayList<Double>();
		for(int n=0; n<periodi.get(0);n++) {
			totaleMovimentiCC.add(0.0);
		}
			double tot=0;
		if (movCC.size()==1) {
			totaleMovimentiCC.add(movCC.get(0).getAmmontare());
		}
		else {
			int i = 0;
				for(; i < movCC.size()-1; i++) {
					if(movCC.get(i).getData().getMonthValue() == movCC.get(i+1).getData().getMonthValue()) {
						tot+=movCC.get(i).getAmmontare();
					}
					else {
						tot+=movCC.get(i).getAmmontare();
						totaleMovimentiCC.add(tot);
						tot=0;
						if((movCC.get(i+1).getData().getMonthValue()-movCC.get(i).getData().getMonthValue())>1 ) {
							for(int j=0; j<(movCC.get(i+1).getData().getMonthValue()-movCC.get(i).getData().getMonthValue());j++) {
								totaleMovimentiCC.add(0.0);
							}
						}
					}
				}
		
				if(movCC.get(i-1).getData().getMonthValue() == movCC.get(i).getData().getMonthValue()) {
					totaleMovimentiCC.add( tot+ movCC.get(i).getAmmontare());
				}	
				else{
					if((movCC.get(i-1).getData().getMonthValue()-movCC.get(i).getData().getMonthValue())>1 ) {
						for(int j=0; j<(movCC.get(i).getData().getMonthValue()-movCC.get(i-1).getData().getMonthValue());j++) {
							totaleMovimentiCC.add(0.0);
						}
					}
				totaleMovimentiCC.add(movCC.get(i).getAmmontare());
				}
			}
			for(int n=0; n<periodi.get(1);n++) {
				totaleMovimentiCC.add(0.0);
			}
		return totaleMovimentiCC;
	}
}
