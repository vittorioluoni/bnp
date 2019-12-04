package bean;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
public class Spese {
	
	private String numeroConto;
	private String numeroCarta;
	private LocalDate data;
	private double ammontare;
	private static List<List<Double>> totale;
	
	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}
	
	public String getNumeroConto() {
		return this.numeroConto;
	}
	
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	
	public String getNumeroCarta() {
		return this.numeroCarta;
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
	
	public static void setTotale(List<List<Double>> totale) {
		Spese.totale = totale;
	}
	
	public static List<List<Double>> getTotale() {
		return Spese.totale;
	}
	
	public static List<Spese> selezionaDatiPerConto(List<Spese> spese, List<Conto> conto){
		for(int i = 0; i< conto.size(); i++) {
			for (int j=0 ; j< conto.get(i).getNumeroCarta().size(); j++ ) {
				for (int n =0; n< spese.size(); n++) {
					if (conto.get(i).getNumeroCarta().get(j).equalsIgnoreCase(spese.get(n).getNumeroCarta())) {
				
						spese.get(n).setNumeroConto(conto.get(i).getNumeroConto());
					}
				}
			}
		}
		
		System.out.println("spese size : " + spese.size());
		return spese;
	} 
	
	public static List<Spese> selezionaDatiRichiesti(List<Spese> spese, String numeroConto, int mese, int anno){
		List<Spese> s= new ArrayList<Spese>();
		for(int i=0;i<spese.size();i++) {
			if((spese.get(i).getNumeroConto().equals(numeroConto)) && (spese.get(i).getData().getYear() <= anno) &&
				(spese.get(i).getData().getMonthValue() <= mese)) {
				s.add(spese.get(i));
			}
		}
		return s;
	}
	
	public static List<Spese> selezionaDatiPerCarta(List<Spese> spese, String numeroCarta){
		List<Spese> s= new ArrayList<Spese>();
		for(int i=0;i<spese.size();i++) {
			if(spese.get(i).getNumeroCarta().equalsIgnoreCase(numeroCarta)){
				s.add(spese.get(i));
			}
		}
		return s;
	}
	
	public static List<Spese> selezionaDatiPerMese(List<Spese> spese, int mese, int anno){
		List<Spese> speseSelezionate = new ArrayList<Spese>();
		for(Spese spesa :spese) {
			if (spesa.getData().getMonthValue() == mese && spesa.getData().getYear() == anno) {
				speseSelezionate.add(spesa);
			}
		}
		return speseSelezionate;
	}
	
	public static List<Spese> ordinaDati(List<Spese> spese){
		for(int i=0;i<spese.size()-1;i++) {
			for(int j=i+1; j<spese.size();j++) {
				if(spese.get(i).getData().isBefore(spese.get(j).getData())) {}
				else {
					Spese x=spese.get(i);
					Spese y=spese.get(j);
					spese.set(i, y);
					spese.set(j, x);
				}	
			}
		}
	
		return spese;
	}
}
