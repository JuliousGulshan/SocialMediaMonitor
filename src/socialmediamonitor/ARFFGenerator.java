/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediamonitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author afonso
 */
public class ARFFGenerator {
    
    public static boolean gerarArffClassificacaoTF_IDF(String caminhoArquivo, 
			List<Post> listaDocumentos, String[] valoresClasse, 
			String[] nomeAtributos)  {

		try{
	        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));
	        
	        writer.append("@relation dataset\n\n");
	
	        for(String s : nomeAtributos){
	            writer.append("@attribute " + s + " REAL \n");
	        }
	
	        String classesOut = "";
	        for(String c : valoresClasse){
	            classesOut += c + ",";
	        }
	        if(classesOut.equals("") == false){
	            classesOut = classesOut.substring(0, classesOut.length()-1);
	        }
	
	        writer.append("@attribute classe {"+classesOut+"}\n\n\n");
	
	        writer.append("@DATA\n");
	        for(Post d : listaDocumentos){
	        	writer.append(d.getTfIdfAsString() + "," + d.getClasse() +"\n");
	        }
	
	        writer.flush();
	        writer.close();
	        
	        return true;
		}catch(IOException ex){
			System.out.println("Erro ao gerar o arquivo Arff.");
			ex.printStackTrace();
			return false;
		}

	}
    
}
