package randori;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class ARFF_Generator {

	/**
	 * Metodo para gerar um arquivo ARFF a ser utilizado como input por um algoritmo
	 * do Weka. Devem ser informados o nome e local aonde o arquivo gerado deve ser 
	 * salvo, os vetores tf-idf para cada documento (com suas respectivas classes), 
	 * os valores de classe possiveis e os nomes dos atributos das instancias (como 
	 * utiliza-se tf-idf os atributos sao os termos do dicionario)
	 * 
	 * @param caminhoArquivo - local e nome do arquivo ARFF a ser salvo
	 * @param listaDocumentos - lista de documentos que virarao instancias 
	 * (os arrays com os valores TF*IDF para cada termo do dicionario)
	 * @param valoresClasse - Os possiveis valores que podem ser assumidos pelo atributo classe 
	 * @param nomeAtributos - os atributos que farao parte do dataset, tipicamente o vocabulario 
	 * @throws IOException
	 */
	public static boolean gerarArffClassificacaoTF_IDF(String caminhoArquivo, 
			List<MensagemSMS> listaDocumentos, String[] valoresClasse, 
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
	        for(MensagemSMS d : listaDocumentos){
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
