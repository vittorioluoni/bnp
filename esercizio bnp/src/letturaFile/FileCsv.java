package letturaFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;



import bean.Spese;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class FileCsv {
	public static List<Spese> letturaFile(String fileName)  {
	
		File f = new File(fileName);
		
		return letturaFile(f);
	}
//	public static void main(String[] args) {
//		//System.out.println(FileCsv.letturaFile());
//	}
	
	
	public static List<Spese> letturaFile(File file)  {
		
		List<Spese> spese = new ArrayList<>();
		List<String> elementi = new ArrayList<String>();
		try {
			
			
				
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String stringa;
			//buono per dopo se devo cambiare l'ordine con cui sono inseriti nel file
			stringa=bufferedReader.readLine();
		    elementi = Arrays.asList(stringa.split(","));
	
		    
			//nel file csv c'è l'ultima data che ha il giorno espresso con un solo numero
			//non sapendo se è un errore di battitura o un'eccezione da gestire,
			//la gestisco come eccezione.
			Spese spesa = null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
			
			while (bufferedReader.ready()) {
			spesa= new Spese();

				stringa=bufferedReader.readLine();
			    List<String> riga = Arrays.asList(stringa.split(","));
			    for(int i =0; i<riga.size(); i++) {
			    	
			    	
			    	if(elementi.get(i).equals("Carta")) {
			    		spesa.setNumeroCarta(riga.get(i));
			    	}
			    	
			    	else if(elementi.get(i).equals("Data")) {
			    		try {
			    			
			    			spesa.setData(LocalDate.parse(riga.get(i),formatter));
			    			
			    			} catch( DateTimeParseException e) {
			    				
			    				spesa.setData(LocalDate.parse(riga.get(i),formatter1));
			    			
			    			}
			    	}
			    	
			    	else {
			    		spesa.setAmmontare(Double.parseDouble(riga.get(i)));
			    	}
			    }
			    //System.out.println(riga.get(1));
			    spese.add(spesa);
			    //System.out.println(spesa.getData());
			}
			bufferedReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return spese;
		}
		public static void main(String[] args) {
			System.out.println(FileCsv.letturaFile("C:\\Users\\Padawan07\\Desktop\\workspace_vittorio\\esercizio bnp\\risorse\\spese_carta.csv"));
		}
}	