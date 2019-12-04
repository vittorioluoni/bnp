package letturaFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import bean.Conto;
import bean.Spese;
import gestioneConti.GestioneSpese;

public class FileXml {
	
	public static List<Conto> letturaFile(String fileName) {
		List<Conto> conto = new ArrayList<Conto>();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();

	        // Load the input XML document, parse it and return an instance of the
	        // Document class.
	        Document document = builder.parse(fileName);
	        Element element = document.getDocumentElement();	           
	        	        
			NodeList nodeList = element.getElementsByTagName("conto");
			
			Element e = null;
	        for (int i = 0; i < nodeList.getLength(); i++) {
	        	//System.out.println(nodeList.getLength());
	        	e = (Element)nodeList.item(i);
	        	//System.out.println(e);

	        	Conto c = new Conto();
	        	String numero = e.getAttribute("Numero");
	        	c.setNumeroConto(numero);
	        	
	        	NodeList NumeroCarta = e.getElementsByTagName("carta");
	        	//System.out.println(NumeroCarta.getLength());
	        	Element n = null;
	        	List<String> cartaNumero = new ArrayList<String>();

	        	for(int j=0; j < NumeroCarta.getLength(); j++) {
	        		n = (Element)NumeroCarta.item(j);
	        		cartaNumero.add(n.getAttribute("Numero"));
	        	}
	        	c.setNumeroCarta(cartaNumero);
	        	conto.add(c);
	        	//System.out.println(c.getNumeroCarta().get(0));
        }              
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(conto.get(0).getNumeroCarta().get(0));
		return conto;
	}
	
	public static List<Conto> letturaFile(File file) {
		List<Conto> conto = new ArrayList<Conto>();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();

	        // Load the input XML document, parse it and return an instance of the
	        // Document class.
	        Document document = builder.parse(file);
	        Element element = document.getDocumentElement();	           
	        	        
			NodeList nodeList = element.getElementsByTagName("conto");
			
			Element e = null;
	        for (int i = 0; i < nodeList.getLength(); i++) {
	        	//System.out.println(nodeList.getLength());
	        	e = (Element)nodeList.item(i);
	        	//System.out.println(e);

	        	Conto c = new Conto();
	        	String numero = e.getAttribute("Numero");
	        	c.setNumeroConto(numero);
	        	
	        	NodeList NumeroCarta = e.getElementsByTagName("carta");
	        	//System.out.println(NumeroCarta.getLength());
	        	Element n = null;
	        	List<String> cartaNumero = new ArrayList<String>();

	        	for(int j=0; j < NumeroCarta.getLength(); j++) {
	        		n = (Element)NumeroCarta.item(j);
	        		cartaNumero.add(n.getAttribute("Numero"));
	        	}
	        	c.setNumeroCarta(cartaNumero);
	        	conto.add(c);
	        	//System.out.println(c.getNumeroCarta().get(0));
        }              
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(conto.get(0).getNumeroCarta().get(0));
		return conto;
	}
	
//	public static void main(String[] args) {
//		List<Conto> conti = new ArrayList<Conto>();
//		List<Spese> spese = new ArrayList<Spese>();
//// 		conti = FileXml.letturaFile();
////		spese = FileCsv.letturaFile();
//		
//		spese = Spese.selezionaDatiPerConto(spese,conti);
//		spese = Spese.selezionaDatiRichiesti(spese, "123", 11, 2017);
//		spese = Spese.selezionaDatiPerCarta(spese, "XYZ456");
//		spese = Spese.ordinaDati(spese);
//		for(int i =0; i<spese.size(); i++) {
//			System.out.println(spese.get(i).getNumeroConto() + " " + spese.get(i).getNumeroCarta() +" "
//			+	spese.get(i).getData() + " " + spese.get(i).getAmmontare() + " ");
//		}
//		System.out.println(spese.size());
////		List <Double> totaleMesexMese = GestioneSpese.calcoloTotaleSpese(spese);
////		
////		for(double tot :totaleMesexMese) {
////			System.out.println("Totale :" + tot);
////		} 
//	}
}
