package socialmediamonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ContadorFrequencia {
    private static Map<String, Integer> mapa;

    // Cria Mapa baseado na entrada de usuario
    public static String[] criarMapa(String _str) {
        
            mapa = new HashMap<String, Integer>(); // Cria o HashMap

            // frase do usuario
            String input = _str;

            // Cria StringTokenizer para a frase de entrada
            StringTokenizer quebraFrase = new StringTokenizer(input);

            // Processamento de texto de entrada
            while (quebraFrase.hasMoreTokens())// Enquanto houver mais entradas
            {
                String palavra = quebraFrase.nextToken().toLowerCase(); 

                // Se o mapa tiver a palavra
                if (mapa.containsKey(palavra)) // Palavras esta no mapa
                {
                    int contador = mapa.get(palavra); // Obtem contagem atual
                    mapa.put(palavra, contador + 1); // Incrementa a contagem
                } else {
                    mapa.put(palavra, 1); // Adiciona uma nova palavra com
                }							// contagem de 1 ao mapa
            }

            return criarVocabulario();
    }

    // Exibe conteudo do mapa
    private static String[] criarVocabulario() {
        
        ArrayList<String> arrayVocabulario = new ArrayList<String>();
        
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String string = entry.getKey();
            Integer integer = entry.getValue();
            
            if (integer >= 8 && string.length() > 2){
                if (string.compareTo("has") != 0 && string.compareTo("had") != 0
                        && string.compareTo("told") != 0 && string.compareTo("much") != 0
                        && string.compareTo("there") != 0 && string.compareTo("!!!") != 0
                        && string.compareTo("will") != 0 && string.compareTo("getting") != 0
                        && string.compareTo("here") != 0 && string.compareTo("your") != 0
                        && string.compareTo("into") != 0 && string.compareTo("its") != 0
                        && string.compareTo("got") != 0 && string.compareTo("can") != 0
                        && string.compareTo("you") != 0 && string.compareTo("your") != 0
                        && string.compareTo("let") != 0 && string.compareTo("n't") != 0
                        && string.compareTo("but") != 0 && string.compareTo("because") != 0
                        && string.compareTo("another") != 0 && string.compareTo("'re") != 0
                        && string.compareTo("let") != 0 && string.compareTo("n't") != 0
                        && string.compareTo("...") != 0 && string.compareTo("their") != 0
                        && string.compareTo("would") != 0 && string.compareTo("paying") != 0
                        && string.compareTo("get") != 0 && string.compareTo("and") != 0
                        && string.compareTo("they") != 0 && string.compareTo("then") != 0
                        && string.compareTo("being") != 0 && string.compareTo("looking") != 0
                        && string.compareTo("when") != 0 && string.compareTo("this") != 0
                        && string.compareTo("from") != 0 && string.compareTo("was") != 0
                        && string.compareTo("that") != 0 && string.compareTo("about") != 0
                        && string.compareTo("for") != 0 && string.compareTo("are") != 0
                        && string.compareTo("trying") != 0 && string.compareTo("!!!!") != 0
                        && string.compareTo("have") != 0 && string.compareTo("even") != 0
                        && string.compareTo("with") != 0 && string.compareTo("the") != 0
                        
                        )
                arrayVocabulario.add(string);
            }
        }
        
        String[] ret = new String[arrayVocabulario.size()];
        
        for (int i = 0; i < arrayVocabulario.size(); i++) {
            System.out.println(arrayVocabulario.get(i));
            ret[i] = arrayVocabulario.get(i);
        }
        
        return ret;
    }
}