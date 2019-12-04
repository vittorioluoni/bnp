package web;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CORBA.portable.InputStream;

import bean.Conto;
import bean.MovimentiCC;
import bean.Spese;
import letturaFile.FileCsv;
import letturaFile.FileMovimentiCC;
import letturaFile.FileXml;
 
/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
@WebServlet("/upload")
@MultipartConfig
public class CaricaFileServlet extends HttpServlet {
 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       System.out.println("ciao");
        //process only if its multipart content
       List<Conto> conti = new ArrayList<Conto>();
       List<Spese> spese = new ArrayList<Spese>();
       List<MovimentiCC> movCC = new ArrayList<MovimentiCC>();
       String[][] istruzioni =new String[4][2];
        if(ServletFileUpload.isMultipartContent(request)){
        	
    		
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                System.out.println("multiparts size : " + multiparts.size());
                
                for(FileItem item : multiparts){
                	System.out.println("item " + item.getName());
                	
                    if(!item.isFormField()){//entra solo se FILE
                    	
                    	String fileName = item.getName();
                    	//System.out.println("request.getServletContext() : " + request.getServletContext());
                    	File file = new File(request.getServletContext() + fileName);
                    	item.write(file);
                    	
                    	//System.out.println(fileName);
                    	if(fileName.endsWith("spec.txt")) {
            	        	istruzioni = FileMovimentiCC.letturaIstruzioni(file);
            	        	//System.out.println(istruzioni[0][0]);
                    	}
            	        else if(fileName.endsWith(".txt")) {
            	        	
            	        	movCC= FileMovimentiCC.letturaFile(istruzioni,file);
            	        	movCC = MovimentiCC.ordinaDati(movCC);
            	        	//System.out.println("ciao");
            	        	System.out.println(movCC.get(0).getAmmontare());
            	        }
            	        else if(fileName.endsWith(".csv")) {
            	        	spese = FileCsv.letturaFile(file);
            	        	spese = Spese.ordinaDati(spese);
            	        	System.out.println("spese.size() dopo caricamento file : " + spese.size());
            	        	System.out.println(spese.get(0).getAmmontare());
            	        }
            	        
            	        else {
            	        	conti =FileXml.letturaFile(file);
            	        	System.out.println(conti.get(0).getNumeroConto());
            	        }
                    }
                }
                
            	    request.getSession().setAttribute("movimentiCC", movCC);
            	   
            	    request.getSession().setAttribute("spese", spese);
            	   
            	    request.getSession().setAttribute("conti", conti);
            	    response.sendRedirect("selezionacontoemese.jsp");

               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            
              
            }catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
          
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }      
    }
   
}