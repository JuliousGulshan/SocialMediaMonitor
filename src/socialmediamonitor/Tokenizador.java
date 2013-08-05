package socialmediamonitor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

public class Tokenizador {

	/**
	 * Metodo para tokenizar um texto. Este metodo nao esta considerando a tokenizacao
	 * por sentencas. A lista de tokens retornada corresponde a todas as palavras
	 * e simbolos do texto, independente se o texto possui mais de uma sentenca.
	 * 
	 * @param texto - o texto que devera ser tokenizado por palavras
	 * @return o texto tokenizado
	 */
	public static List<String> tokenizarPorPalavras(String texto){
		DocumentPreprocessor dp = new DocumentPreprocessor(new StringReader(texto));
		
		List<String> tokens = new ArrayList<String>();
		for (List<HasWord> sentenceTokenized : dp) {
			for(HasWord w : sentenceTokenized){
				tokens.add(w.toString());
			}
		}
		
		return tokens;
	}

}
