package gestioneConti;
import java.util.ArrayList;
import java.util.List;

import bean.Spese;
public class GestioneSpese {
	public static List<Double> calcoloTotaleSpese(List<Spese> spese, List<Integer> periodi){
		List<Double> totaleSpese = new ArrayList<Double>();
			double tot=0;
		for(int n=0; n<periodi.get(2);n++) {
			totaleSpese.add(0.0);
		}
		
		if (spese.size()==1) {
			totaleSpese.add(spese.get(0).getAmmontare());
		}
		else {
			int i = 0;
				for(; i < spese.size()-1; i++) {
					if(spese.get(i).getData().getMonthValue() == spese.get(i+1).getData().getMonthValue()) {
						tot+=spese.get(i).getAmmontare();
					}
			
					else {
						tot+=spese.get(i).getAmmontare();
						totaleSpese.add(tot);
						tot=0;
						if((spese.get(i+1).getData().getMonthValue()-spese.get(i).getData().getMonthValue())>1 ) {
							for(int j=0; j<(spese.get(i+1).getData().getMonthValue()-spese.get(i).getData().getMonthValue());j++) {
								totaleSpese.add(0.0);
							}
						}
					}
				}
		
				if(spese.get(i-1).getData().getMonthValue() == spese.get(i).getData().getMonthValue()) {
					totaleSpese.add( tot+ spese.get(i).getAmmontare());
				}	
				else{
					if((spese.get(i).getData().getMonthValue()-spese.get(i-1).getData().getMonthValue())>1 ) {
						for(int j=0; j<(spese.get(i).getData().getMonthValue()-spese.get(i-1).getData().getMonthValue());j++) {
							totaleSpese.add(0.0);
						}
					}
					totaleSpese.add(spese.get(i).getAmmontare());	
				}
			}
		
			for(int n=0; n<periodi.get(3);n++) {
				totaleSpese.add(0.0);
			}
		return totaleSpese;
	}
}
