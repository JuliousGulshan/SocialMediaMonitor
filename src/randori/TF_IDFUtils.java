package randori;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe comportanto metodos referentes ao algoritmo TF-IDF
 * Os metodos executam funcoes como calcular o TF de um certo 
 * termo em um documento.
 * 
 * @author Felipe Leao
 * @version 1.0
 *
 */
public class TF_IDFUtils {
	
	
	
	
	/**
	 * Metodo para calcular a frequencia de um termo do vocabulario
	 * em um determinado documento. 
	 * 
	 * OBS: O objeto e alterado por referencia
	 * 
	 * @param documento - o objeto que sofrera co calculo de Frquencia de Termos
	 * @param vocabulario - o vocabulario indicando os Termos a serem buscados
	 * 
	 */
	public static void calcularTF(MensagemSMS documento, String[] vocabulario){
		
		int totalTermosVocOnDoc = 0;	//numero de termos do vocabulario que aparecem no documento
		int qtdVezesUmTermoVocOnDoc;	//quantidade de vezes que um termo do vocabulario aparece no documento
		
		/*
		 * - Verifica a presenca de cada termo do vocabulario no documento
		 * - Se o termo do Vocabulario se encontra no Documento, adiciona "1" a 
		 *   contagem de termos relevantes.
		 * - considera-se ainda termos do vocabulario que aparecem repetidamente
		 */
		for (String termo : vocabulario) {
            for(String termoDoc : documento.getMensagemTokenizada()){
            	if(termoDoc.equalsIgnoreCase(termo)){
            		totalTermosVocOnDoc++;
            	}
            }
        }
		
		
		/*
		 * - Para cada termo do vocabulario, "posVoc" armazena a posicao do termo.
		 * - Recupera cada termo do documento e o compara ao termo do vocabulario 
		 *   (se for igual, adiciona 1 ao contador)
		 * - Atualiza a lista do documento que descreve a quantidade de vezes que
		 *   cada termo do vocabulario aparece no documento
		 * - Calcula o TF de cada termo do vocabulario para aquele documento
		 *   (se o termo nao aparecer no documento TF = 0)
		 */
		for (int posVoc = 0; posVoc < vocabulario.length; posVoc++) {
			
			qtdVezesUmTermoVocOnDoc = 0;			//Zerar Contador
            String termoDic = vocabulario[posVoc];	//Recuperar novo termo do vocabulario
            
            for (String termo : documento.getMensagemTokenizada()) {
                if (termoDic.equalsIgnoreCase(termo)) {
                	qtdVezesUmTermoVocOnDoc++;
                }
            }
            
            //Atualiza a quantidade de vezes que o termo do vocabulario aparece no documento
            documento.getTotalVezesTermoVocOnDoc().add(posVoc, qtdVezesUmTermoVocOnDoc);
            
            //Calcula a frequencia do termo no documento
            if (totalTermosVocOnDoc <= 0) {	//Evita divisao por zero 
                documento.getTf().add(posVoc, 0.0);
            } else {                       
                documento.getTf().add(posVoc, (double) qtdVezesUmTermoVocOnDoc / totalTermosVocOnDoc);
            }
        }
	}
	
	
	
	/**
	 * Metodo para calcular o IDF de cada termo do vocabulario. o IDF eh calculado 
	 * de acordo com a quantidade documentos nos quais um termo vocabulario aparece.
	 * 
	 * @param docsProcessados - lista de documentos com o TF ja calculados
	 * @param tamanhoVocabulario - quantidade de termos no vocabulario
	 * @return idfList - lista com o IDF para cada termo do vocabulario
	 */
	public static ArrayList<Double> calcularIDF(List<MensagemSMS> docsProcessados, int tamanhoVocabulario){
		ArrayList<Double> idfList = new ArrayList<Double>();
		
		/*
		 * - Conta-se a quantidade de documentos nos quais um termo do vocabulario aparece
		 * - Caso a quantidade seja 0, o IDF = 0, caso contrario calcula-se
		 * - O IDF eh armazenado na mesma posicao utilizda pelo termo do vocabulario
		 *   no proprio vocabulario.
		 */
        for (int idxTermoVoc = 0; idxTermoVoc < tamanhoVocabulario; idxTermoVoc++) {
            
        	int qtdDocsComTermoVocabulario = 0;	//Quantidade de documentos onde certo termo aparece
        	
        	//Quantidade de documentos onde o termo aparece
            for (MensagemSMS documento : docsProcessados) {
                if (documento.getTotalVezesTermoVocOnDoc().get(idxTermoVoc) > 0) {
                    qtdDocsComTermoVocabulario++;
                }
            }
            
            //Calculo do IDF
            if (qtdDocsComTermoVocabulario == 0) {
            	idfList.add(idxTermoVoc, 0.0);
            } else {
            	idfList.add(idxTermoVoc, Math.log(docsProcessados.size() / qtdDocsComTermoVocabulario));
            }
        }
        
        return idfList;
	}
	
	
	
	/**
	 * Metodo para calcular o TF*IDF para cada documento. Cada Documento recebera 
	 * um valor de TF*IDF para cada termo do vocabulario, todos os valores sao armazenados
	 * dentro de uma lista no proprio documento.
	 * 
	 * OBS: O objeto eh alterado por referencia
	 * 
	 * @param docsProcessados - lista de documentos com TF jah calculado
	 * @param idfList - lista com o IDF de cada termo do vocabulario
	 */
	public static void calcularTF_IDF(List<MensagemSMS> docsProcessados, List<Double> idfList){
		for (MensagemSMS documento : docsProcessados) {
			List<Double> listaTfIdf = new ArrayList<Double>();
            for (int idxTermoVoc = 0; idxTermoVoc < idfList.size(); idxTermoVoc++) {
            	double tf = documento.getTf().get(idxTermoVoc);
            	double idf = idfList.get(idxTermoVoc);
                double tfIdf = tf * idf;
                listaTfIdf.add(idxTermoVoc, tfIdf);
            }
            documento.setTfIdf(listaTfIdf);
        }
	}
	
}
