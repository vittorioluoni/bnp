package bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimentiCC {
	private String numeroConto;
	private LocalDate data;
	private double ammontare;
	
	
	
	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}
	
	public String getNumeroConto() {
		return this.numeroConto;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public LocalDate getData() {
		return this.data;
	}
	
	public void setAmmontare(double ammontare) {
		this.ammontare = ammontare;
	}
	
	public double getAmmontare() {
		return this.ammontare;
	}
	
	
	
	public static List<MovimentiCC> selezionaDatiRichiesti(List<MovimentiCC> movCC, String numeroConto, int mese, int anno){
		List<MovimentiCC> mCC= new ArrayList<MovimentiCC>();
		for(int i=0;i<movCC.size();i++) {
			if((movCC.get(i).getNumeroConto().equals(numeroConto)) && (movCC.get(i).getData().getYear() <= anno) &&
					(movCC.get(i).getData().getMonthValue() <= mese)) {
				mCC.add(movCC.get(i));
			}
		}
		return mCC;
	}
	
	public static List<MovimentiCC> selezionaDatiPerMese(List<MovimentiCC> movCC, int mese, int anno){
		List<MovimentiCC> movCCSelezionate = new ArrayList<MovimentiCC>();
		for(MovimentiCC mCC :movCC) {
			if (mCC.getData().getMonthValue() == mese && mCC.getData().getYear() == anno) {
				movCCSelezionate.add(mCC);
			}
		}
		return movCCSelezionate;
	}
	
	public static List<MovimentiCC> ordinaDati(List<MovimentiCC> movCC){
		for(int i=0;i<movCC.size()-1;i++) {
			for(int j=i+1; j<movCC.size();j++) {
				if(movCC.get(i).getData().isBefore(movCC.get(j).getData())) {}
				else {
					MovimentiCC x=movCC.get(i);
					MovimentiCC y=movCC.get(j);
					movCC.set(i, y);
					movCC.set(j, x);
				}	
			}
		}
	
		return movCC;
	}
}
