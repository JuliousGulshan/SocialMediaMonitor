package socialmediamonitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class Loader {
	
	private Loader(){}
	
	/**
	 * classe para carregar multiplos documentos TXT em um diretorio. Cada documento
	 * sera convertido em um objeto do tipo MensagemSMS. A classe da mensagem sera
	 * definida de acordo com o parametro "classe" informado pelo usuario.
	 * 
	 * @param diretorioSMS - caminho para o diretorio aonde estao os documentos
	 * @param classe - classe a ser utilizada para os documentos sendo carregados
	 * @return Lista de objetos MensagemSMS, com o texto da mensagem e a classe
	 * @throws IOException
	 */
	public static List<Message> carregarDocumentos(String diretorio, String classe) throws IOException {
		
		List<Message> listaRetorno = new ArrayList<Message>();
		
		//Recuperar os documentos em cada diretorio
		File[] documentos = new File(diretorio).listFiles();
		
		for(int i=1; i<documentos.length; i++){
                    File f = documentos[i];
			
                    //Pular arquivo ".svn" inserido pelo versionamento.
                    if(f.getName().contains(".svn") || f.getName().contains("._.DS_Store")){
                            continue;
                    }
			
                    Message msg = new Message();
			
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                    msg.setMensagem(extractText(bufferedReader).toLowerCase());
                    msg.setClasse(classe);

                    listaRetorno.add(msg);
		}
		
		return listaRetorno;
	}
	
	
	
	/**
	 * Metodo para abrir o arquivo com as palavras que compoe o vocabulario
	 * e carregar todas em um objeto String[]. cada posicao do vetor eh
	 * ocupada por um termo do dicionario. O arquivo informado deve ter um
	 * termo do vocabulario por linha e nada alem de termos.
	 * 
	 * @param caminhoArquivo - Caminho completo para o arquivo com os termos do vocabulario
	 * @return array com todos os termos.
	 * @throws IOException
	 */
	public static String[] carregarVocabulario(String caminhoArquivo) throws IOException{
		StringBuilder textoPuro = new StringBuilder();
		File arquivo = new File(caminhoArquivo);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));
		
		String line;
	    while ( (line=bufferedReader.readLine()) != null) {
	    	textoPuro.append(line.trim());
	    	textoPuro.append(",");
	    }
	    
	    bufferedReader.close();
	    
	    String[] retorno = {};
	    if(textoPuro.equals("") == false){
	    	retorno = textoPuro.toString().substring(0, textoPuro.length()-1).split(",");
	    }
	    return retorno;
		
	}
	
	
	
	
	/**
	 * Metodo para extrair o texto util a partir de uma pagina HTML.
	 * O metodo espera como argumento um objeto Reader, que poder ser 
	 * informado a partir da abertura de um arquivo por um classe como
	 * a "FileReader".
	 * 
	 * OBS: nao foi verificado se um FilerReader utilizando um InputStreamReadr
	 * para ler um objeto URL funciona.
	 * 
	 * @param inputReader - o objeto para ler o conteudo
	 * @return textOnly - o conteudo do HTML limpo
	 * @throws IOException 
	 */
	private static String extractText(Reader inputReader) throws IOException {
		StringBuilder textoPuro = new StringBuilder();
	    BufferedReader buffReader = new BufferedReader(inputReader);
	    String line;
	    
	    while ( (line=buffReader.readLine()) != null) {
	      textoPuro.append(line);
	    }
	    
	    return textoPuro.toString();
	}
}
