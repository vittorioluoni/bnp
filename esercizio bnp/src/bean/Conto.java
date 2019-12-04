package bean;
import java.util.ArrayList;
import java.util.List;

public class Conto {
	private String numeroConto;
	
	private List<String> numeroCarta;
	
	private static double totale;
	
	private static double saldoIniziale;
	private static double saldoFinale;
	
	
	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}
	
	public String getNumeroConto() {
		return this.numeroConto;
	}
	
	public void setNumeroCarta(List<String> numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	
	public List<String> getNumeroCarta() {
		return this.numeroCarta;
	}
	
	public static void setTotale(double totale) {
		Conto.totale = totale;
	}
	
	public static double getTotale() {
		return Conto.totale;
	}
	
	public static void setSaldoIniziale(double saldoIniziale) {
		Conto.saldoIniziale = saldoIniziale;
	}
	
	public static double getSaldoIniziale() {
		return Conto.saldoIniziale;
	}

	public static void setSaldoFinale(double saldoFinale) {
		Conto.saldoFinale = saldoFinale;
	}
	
	public static double getSaldoFinale() {
		return Conto.saldoFinale;
	}
	
}
