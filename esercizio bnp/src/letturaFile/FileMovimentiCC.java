package letturaFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import bean.MovimentiCC;



public class FileMovimentiCC {
	public static String[][] letturaIstruzioni(String fileName1){
		String[][] istruzioni = new String[4][2];
		
		File f = new File("String fileName1");
		
		return letturaIstruzioni(f);
		
	}

	public static String[][] letturaIstruzioni(File file){
		String[][] istruzioni = new String[4][2];
		
		try {
		//leggo le istruzioni	
		
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//uso direttamente il valore numerico perchè non posso avere più di 4 istruzioni
		for(int i = 0; i<4 ; i++) {
			String s=bufferedReader.readLine();
		    istruzioni[i]  = s.split(",");
		}
		bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return istruzioni;
	}

	public static List<MovimentiCC> letturaFile(String[][] istruzioni, String fileName){
		List<MovimentiCC> movimentiCC = new ArrayList<MovimentiCC>();
		try {
		//leggo il file dei movimenti CC usando le istruzioni
		File f1 = new File(fileName);
		FileReader fileReader1 = new FileReader(f1);
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		//scorro il file movimenti_cc riga per riga
		while (bufferedReader1.ready()) {
			
			MovimentiCC movCC= new MovimentiCC();
			String s=bufferedReader1.readLine();
			int i=0;
			int j=0;
			
			//salvo temporaneamente in delle stringhe i dati provenienti dal txt
			String numeroContoLungo=s.substring(i,Integer.parseInt(istruzioni[0][0]));
			i += Integer.parseInt(istruzioni[0][0]);
			
			
			String dataStringa=s.substring(i, Integer.parseInt(istruzioni[1][0].substring(1))+i);
			i+= Integer.parseInt(istruzioni[1][0].substring(1));
			
			j =i;
			
			
			String ammontareStringa="";
			for(;i<j+Integer.parseInt(istruzioni[2][0]);i++) {
				ammontareStringa += s.charAt(i);
				//aggiungo il . dopo 6 caratteri per tenere conto dei decimali
				if(i-j==5) {
					ammontareStringa += ".";
				}
			}
			
			
			String crOdr = s.substring(i, Integer.parseInt(istruzioni[3][0].substring(1))+i);
			
			//del numero di conto corrente mi interessano solo le cifre significative 
			//dopo lo 0 per far combaciare il numero di conto
			//con quello ottenuto dal file xml
			
			String l= "";
		
			int n=0;
			OUTER: for(;n<numeroContoLungo.length();n++) {
				
				if(numeroContoLungo.charAt(n)!=('C') && numeroContoLungo.charAt(n)!='0') {
					l += numeroContoLungo.charAt(n);
					break OUTER;
				}	
			}
			l = l.concat(numeroContoLungo.substring(n+1));
			//System.out.println(l);
			movCC.setNumeroConto(l);
			
			movCC.setData(LocalDate.parse(dataStringa,formatter));
			
			if(crOdr.equals("CR")) {
				movCC.setAmmontare(Double.parseDouble(ammontareStringa));
			}
			else {
				movCC.setAmmontare(-1*Double.parseDouble(ammontareStringa));
			}
			movimentiCC.add(movCC);	
		}
		bufferedReader1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for(int i=0;i<movimentiCC.size(); i++) {
//			System.out.println(movimentiCC.get(i).getNumeroConto());
//		}
		return movimentiCC;

	
	}
	
	public static List<MovimentiCC> letturaFile(String[][] istruzioni, File file){
		List<MovimentiCC> movimentiCC = new ArrayList<MovimentiCC>();
		try {
		//leggo il file dei movimenti CC usando le istruzioni
		
		FileReader fileReader1 = new FileReader(file);
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		//scorro il file movimenti_cc riga per riga
		while (bufferedReader1.ready()) {
			
			MovimentiCC movCC= new MovimentiCC();
			String s=bufferedReader1.readLine();
			int i=0;
			int j=0;
			
			//salvo temporaneamente in delle stringhe i dati provenienti dal txt
			String numeroContoLungo=s.substring(i,Integer.parseInt(istruzioni[0][0]));
			i += Integer.parseInt(istruzioni[0][0]);
			
			
			String dataStringa=s.substring(i, Integer.parseInt(istruzioni[1][0].substring(1))+i);
			i+= Integer.parseInt(istruzioni[1][0].substring(1));
			
			j =i;
			
			
			String ammontareStringa="";
			for(;i<j+Integer.parseInt(istruzioni[2][0]);i++) {
				ammontareStringa += s.charAt(i);
				//aggiungo il . dopo 6 caratteri per tenere conto dei decimali
				if(i-j==5) {
					ammontareStringa += ".";
				}
			}
			
			
			String crOdr = s.substring(i, Integer.parseInt(istruzioni[3][0].substring(1))+i);
			
			//del numero di conto corrente mi interessano solo le cifre significative 
			//dopo lo 0 per far combaciare il numero di conto
			//con quello ottenuto dal file xml
			
			String l= "";
		
			int n=0;
			OUTER: for(;n<numeroContoLungo.length();n++) {
				
				if(numeroContoLungo.charAt(n)!=('C') && numeroContoLungo.charAt(n)!='0') {
					l += numeroContoLungo.charAt(n);
					break OUTER;
				}	
			}
			l = l.concat(numeroContoLungo.substring(n+1));
			//System.out.println(l);
			movCC.setNumeroConto(l);
			
			movCC.setData(LocalDate.parse(dataStringa,formatter));
			
			if(crOdr.equals("CR")) {
				movCC.setAmmontare(Double.parseDouble(ammontareStringa));
			}
			else {
				movCC.setAmmontare(-1*Double.parseDouble(ammontareStringa));
			}
			movimentiCC.add(movCC);	
		}
		bufferedReader1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return movimentiCC;

	
	}	
	
}
